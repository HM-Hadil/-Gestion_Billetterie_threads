package isimg.gestion_billets_threads;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RéservationRepository extends JpaRepository<Réservation, Long> {
    List<Réservation> findByBilletId(Long billetId);
}