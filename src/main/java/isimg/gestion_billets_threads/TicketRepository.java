package isimg.gestion_billets_threads;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findBySpectacleName(String spectacleName);

    // Rechercher des tickets disponibles pour un spectacle
    List<Ticket> findBySpectacleNameAndReservedFalse(String spectacleName);
    List<Ticket> findByReservedFalse();

    // Rechercher les tickets réservés par un utilisateur
    List<Ticket> findByReservedByAndReservedTrue(String reserverName);

}