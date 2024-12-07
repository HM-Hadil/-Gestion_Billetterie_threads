package isimg.gestion_billets_threads;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/reserver") public ResponseEntity<?> reserverBillet(@RequestBody RéservationRequest request) {
        boolean success = ticketService.réserverBillet(request.getBilletId(), request.getUtilisateurId());
        if (success) {
            return ResponseEntity.ok().build();
          }
        else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); }
    }

    @PostMapping("/annuler") public ResponseEntity<?> annulerRéservation(@RequestBody RéservationRequest request) {
        ticketService.annulerRéservation(request.getBilletId()); return ResponseEntity.ok().build(); }
}
