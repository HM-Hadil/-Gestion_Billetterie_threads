package isimg.gestion_billets_threads;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;
@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    // Créer un nouveau ticket
    @Transactional
    public Ticket createTicket(TicketCreationDTO ticketDTO) {
        Ticket ticket = new Ticket(
                ticketDTO.getSpectacleName(),
                ticketDTO.getShowDate(),
                ticketDTO.getPrice(),
                ticketDTO.getSeatNumber()
        );
        return ticketRepository.save(ticket);
    }

    public void reserveTicketInThread(Long ticketId, String reserverName) {
        TicketReservationThread thread = new TicketReservationThread(this, ticketId, reserverName);
        thread.start(); // Lancer un nouveau thread pour la réservation
    }
    // Annuler une réservation (thread)
    public void cancelTicketInThread(Long ticketId) {
        TicketCancellationThread thread = new TicketCancellationThread(this, ticketId);
        thread.start(); // Lancer un nouveau thread pour annuler la réservation
    }



    // Réserver un ticket
    @Transactional
    public Ticket reserveTicket(Long ticketId, String reserverName) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);

        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            if (ticket.reserve(reserverName)) {
                return ticketRepository.save(ticket);
            } else {
                throw new IllegalStateException("Ticket déjà réservé");
            }
        }

        throw new IllegalArgumentException("Ticket non trouvé");
    }


    // Annuler une réservation
    @Transactional
    public Ticket cancelReservation(Long ticketId) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);

        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            ticket.cancelReservation();
            return ticketRepository.save(ticket);
        }

        throw new IllegalArgumentException("Ticket non trouvé");
    }

    // Obtenir tous les tickets pour un spectacle
    public List<Ticket> getTicketsBySpectacle(String spectacleName) {
        return ticketRepository.findBySpectacleName(spectacleName);
    }

    // Obtenir les tickets disponibles pour un spectacle
    public List<Ticket> getAvailableTickets(String spectacleName) {
        return ticketRepository.findBySpectacleNameAndReservedFalse(spectacleName);
    }

    // Obtenir les tickets réservés par un utilisateur
    public List<Ticket> getReservedTickets(String reserverName) {
        return ticketRepository.findByReservedByAndReservedTrue(reserverName);
    }

    public List<Ticket> getAllAvailableTickets() {
        return ticketRepository.findByReservedFalse();
    }

}