package isimg.gestion_billets_threads;
public class UtilisateurThread implements Runnable {
    private Long utilisateurId;
    private Long billetId;
    private TicketService billetService;

    public UtilisateurThread(Long utilisateurId, Long billetId, TicketService billetService) {
        this.utilisateurId = utilisateurId;
        this.billetId = billetId;
        this.billetService = billetService;
    }

    @Override
    public void run() {
        boolean réservé = billetService.réserverBillet(billetId, utilisateurId);
        if (réservé) {
            System.out.println("Utilisateur " + utilisateurId + " a réservé le billet " + billetId);
        } else {
            System.out.println("Utilisateur " + utilisateurId + " n'a pas pu réserver le billet " + billetId);
        }
    }
}
