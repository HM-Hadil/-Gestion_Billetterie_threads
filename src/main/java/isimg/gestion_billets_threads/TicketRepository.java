package isimg.gestion_billets_threads;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Optional<Ticket> findBySeatNumber(String seatNumber);  // Rechercher un billet par son numéro de siège
}