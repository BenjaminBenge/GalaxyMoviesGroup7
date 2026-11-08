/**
 * Movie.java
 * Represents a movie showing at the cinema.
 * Demonstrates: encapsulation, constructors, getters/setters, access modifiers.
 */
public class Movie {
    private String title;
    private String genre;
    private String releaseDate;
    private double ticketPrice;
    private String posterFile;
    private int totalSeats;
    private int seatsAvailable;

    public Movie(String title, String genre, String releaseDate,
                 double ticketPrice, String posterFile, int totalSeats) {
        this.title = title;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.ticketPrice = ticketPrice;
        this.posterFile = posterFile;
        this.totalSeats = totalSeats;
        this.seatsAvailable = totalSeats;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public String getPosterFile() {
        return posterFile;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public boolean isSoldOut() {
        return seatsAvailable == 0;
    }

    /**
     * Attempts to reserve the given number of seats.
     * Returns true if the booking succeeded, false otherwise.
     */
    public boolean bookSeats(int numberOfSeats) {
        if (numberOfSeats <= 0 || numberOfSeats > seatsAvailable) {
            return false;
        }
        seatsAvailable -= numberOfSeats;
        return true;
    }
}
