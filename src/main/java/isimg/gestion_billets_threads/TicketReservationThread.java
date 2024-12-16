package isimg.gestion_billets_threads;

public class TicketReservationThread extends Thread {
    private final TicketService ticketService;
    private final Long ticketId;
    private final String reserverName;

    public TicketReservationThread(TicketService ticketService, Long ticketId, String reserverName) {
        this.ticketService = ticketService;
        this.ticketId = ticketId;
        this.reserverName = reserverName;
    }

    @Override
    public void run() {
        try {
            Ticket reservedTicket = ticketService.reserveTicket(ticketId, reserverName);
            System.out.println("Réservation réussie pour " + reservedTicket.getReservedBy() +
                    " sur le ticket " + reservedTicket.getId());
        } catch (Exception e) {
            System.err.println("Erreur lors de la réservation : " + e.getMessage());
        }
    }
}
