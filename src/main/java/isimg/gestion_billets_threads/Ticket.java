package isimg.gestion_billets_threads;

import jakarta.persistence.*;


import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Semaphore;


@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String spectacleName;

    @Column(nullable = false)
    private LocalDateTime showDate;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int seatNumber;

    @Column(nullable = false)
    private boolean reserved = false;

    @Column
    private String reservedBy;

    @Column
    private LocalDateTime reservationTime;

    // Constructeurs
    public Ticket() {}

    public Ticket(String spectacleName, LocalDateTime showDate, double price, int seatNumber) {
        this.spectacleName = spectacleName;
        this.showDate = showDate;
        this.price = price;
        this.seatNumber = seatNumber;
    }

    // Méthode pour réserver le ticket
    public synchronized boolean reserve(String reserverName) {
        if (!this.reserved) {
            this.reserved = true;
            this.reservedBy = reserverName;
            this.reservationTime = LocalDateTime.now();
            return true;
        }
        return false;
    }

    // Méthode pour annuler la réservation
    public synchronized void cancelReservation() {
        this.reserved = false;
        this.reservedBy = null;
        this.reservationTime = null;
    }

    // Getters et setters (ajoutez ceux pour les nouveaux champs)
    // ... (précédents getters et setters)
    public Long getId() {
        return id;
    }
    public LocalDateTime getShowDate() { return showDate; }
    public void setShowDate(LocalDateTime showDate) { this.showDate = showDate; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getSeatNumber() { return seatNumber; }
    public void setSeatNumber(int seatNumber) { this.seatNumber = seatNumber; }

    public String getReservedBy() { return reservedBy; }
    public void setReservedBy(String reservedBy) { this.reservedBy = reservedBy; }

    public LocalDateTime getReservationTime() { return reservationTime; }
    public void setReservationTime(LocalDateTime reservationTime) { this.reservationTime = reservationTime; }
}
