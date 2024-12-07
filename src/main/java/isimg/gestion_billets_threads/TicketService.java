package isimg.gestion_billets_threads;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private RéservationRepository réservationRepository;


    // Réservation d'un billet
    public synchronized boolean réserverBillet(Long billetId, Long utilisateurId) {
        Optional<Ticket> billetOpt = ticketRepository.findById(billetId);
        if (billetOpt.isPresent() && !billetOpt.get().isReserved()) {
            Ticket billet = billetOpt.get();
            billet.setReserved(true);
            ticketRepository.save(billet);
            Réservation réservation = new Réservation();
            réservation.setBilletId(billetId);
            réservation.setUtilisateurId(utilisateurId);
            réservation.setDateRéservation(LocalDateTime.now());
            réservationRepository.save(réservation);
            return true;
        }
        return false; }

    public synchronized void annulerRéservation(Long billetId) {
        Optional<Ticket> billetOpt = ticketRepository.findById(billetId);
        if (billetOpt.isPresent() && billetOpt.get().isReserved()) {
            Ticket billet = billetOpt.get();
            billet.setReserved(false);
            ticketRepository.save(billet);
            List<Réservation> réservations = réservationRepository.findByBilletId(billetId);
            for (Réservation réservation : réservations) {
                réservationRepository.delete(réservation);
            } } }


        }
