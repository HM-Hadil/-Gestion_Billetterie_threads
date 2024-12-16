package isimg.gestion_billets_threads;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/api/tickets")
    public class TicketController {
        @Autowired
        private TicketService ticketService;

        // Créer un nouveau ticket
        @PostMapping("/create")
        public ResponseEntity<Ticket> createTicket(@RequestBody TicketCreationDTO ticketDTO) {
            Ticket newTicket = ticketService.createTicket(ticketDTO);
            return ResponseEntity.ok(newTicket);
        }
        @GetMapping("/test")
        public String testEndpoint() {
            return "Connexion réussie !";
        }


        // Endpoint pour réserver un ticket
        @PostMapping("/reserve/{ticketId}/{reserverName}")
        public String reserveTickethread(@PathVariable Long ticketId, @PathVariable String reserverName) {
            // Lancer la réservation dans un thread distinct
            ticketService.reserveTicketInThread(ticketId, reserverName);
            return "Réservation en cours pour le ticket " + ticketId + " par " + reserverName;
        }


        // Réserver un ticket
        @PostMapping("/reserve/{ticketId}")
        public ResponseEntity<Ticket> reserveTicket(
                @PathVariable Long ticketId,
                @RequestParam String reserverName
        ) {
            try {
                Ticket reservedTicket = ticketService.reserveTicket(ticketId, reserverName);
                return ResponseEntity.ok(reservedTicket);
            } catch (IllegalStateException | IllegalArgumentException e) {
                return ResponseEntity.badRequest().body(null);
            }
        }

        // Annuler une réservation
        @PostMapping("/cancel/{ticketId}")
        public ResponseEntity<Ticket> cancelReservation(@PathVariable Long ticketId) {
            try {
                Ticket canceledTicket = ticketService.cancelReservation(ticketId);
                return ResponseEntity.ok(canceledTicket);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.notFound().build();
            }
        }

        // Obtenir tous les tickets d'un spectacle
        @GetMapping("/spectacle/{spectacleName}")
        public ResponseEntity<List<Ticket>> getTicketsBySpectacle(@PathVariable String spectacleName) {
            List<Ticket> tickets = ticketService.getTicketsBySpectacle(spectacleName);
            return ResponseEntity.ok(tickets);
        }

        // Obtenir les tickets disponibles pour un spectacle
        @GetMapping("/available/{spectacleName}")
        public ResponseEntity<List<Ticket>> getAvailableTickets(@PathVariable String spectacleName) {
            List<Ticket> availableTickets = ticketService.getAvailableTickets(spectacleName);
            return ResponseEntity.ok(availableTickets);
        }

        // Obtenir les tickets réservés par un utilisateur
        @GetMapping("/reserved/{reserverName}")
        public ResponseEntity<List<Ticket>> getReservedTickets(@PathVariable String reserverName) {
            List<Ticket> reservedTickets = ticketService.getReservedTickets(reserverName);
            return ResponseEntity.ok(reservedTickets);
        }
}