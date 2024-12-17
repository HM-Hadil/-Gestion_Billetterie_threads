package isimg.gestion_billets_threads;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/api/tickets")
    @CrossOrigin("http://localhost:4200/")
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

        @PostMapping("/cancelThread/{ticketId}")
        public String cancelTickethread(@PathVariable Long ticketId) {
            // Lancer la réservation dans un thread distinct
            ticketService.cancelTicketInThread(ticketId);
            return "Réservation  ticket annulée" ;
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

        // Endpoint pour récupérer tous les tickets disponibles
        @GetMapping("/available")
        public ResponseEntity<List<Ticket>> getAllAvailableTickets() {
            List<Ticket> availableTickets = ticketService.getAllAvailableTickets();
            return ResponseEntity.ok(availableTickets);
        }
}