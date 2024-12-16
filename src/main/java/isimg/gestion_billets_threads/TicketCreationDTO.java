package isimg.gestion_billets_threads;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class TicketCreationDTO {
    @NotBlank

    private String spectacleName;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @NotNull

    private LocalDateTime showDate;

    @Min(1)
    private double price;
    private int seatNumber;


    // Constructeurs
    public TicketCreationDTO() {}

    public TicketCreationDTO(String spectacleName, LocalDateTime showDate, double price, int seatNumber) {
        this.spectacleName = spectacleName;
        this.showDate = showDate;
        this.price = price;
        this.seatNumber = seatNumber;
    }

    // Getters et setters
    public String getSpectacleName() { return spectacleName; }
    public void setSpectacleName(String spectacleName) { this.spectacleName = spectacleName; }

    public LocalDateTime getShowDate() { return showDate; }
    public void setShowDate(LocalDateTime showDate) { this.showDate = showDate; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getSeatNumber() { return seatNumber; }
    public void setSeatNumber(int seatNumber) { this.seatNumber = seatNumber; }
}