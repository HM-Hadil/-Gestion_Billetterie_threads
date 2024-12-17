package isimg.gestion_billets_threads;
public  class TicketCancellationThread extends Thread {
    private final TicketService ticketService;
    private final Long ticketId;

    public TicketCancellationThread(TicketService ticketService, Long ticketId) {
        this.ticketService = ticketService;
        this.ticketId = ticketId;
    }

    @Override
    public void run() {
        try {
            Ticket canceledTicket = ticketService.cancelReservation(ticketId);
            System.out.println("Réservation annulée pour le ticket " + canceledTicket.getId());
        } catch (Exception e) {
            System.err.println("Erreur lors de l'annulation : " + e.getMessage());
        }
    }
}