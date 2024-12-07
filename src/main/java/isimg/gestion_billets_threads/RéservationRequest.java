package isimg.gestion_billets_threads;

import lombok.Data;

@Data
public class RéservationRequest {
    private Long billetId;
    private Long utilisateurId;
}
