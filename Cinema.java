/**
 * Cinema.java
 * Manages the movies on show and all booked tickets.
 * Demonstrates: arrays of objects, searching, iteration.
 */
public class Cinema {
    private String name;
    private Movie[] movies;
    private int movieCount;
    private Ticket[] tickets;
    private int ticketCount;

    public Cinema(String name, int maxMovies, int maxTickets) {
        this.name = name;
        this.movies = new Movie[maxMovies];
        this.movieCount = 0;
        this.tickets = new Ticket[maxTickets];
        this.ticketCount = 0;
    }

    public String getName() {
        return name;
    }

    /**
     * Adds a movie to the cinema's lineup. Returns false if the array is full.
     */
    public boolean addMovie(Movie movie) {
        if (movieCount >= movies.length) {
            return false;
        }
        movies[movieCount] = movie;
        movieCount++;
        return true;
    }

    /**
     * Stores a booked ticket. Returns false if the array is full.
     */
    public boolean addTicket(Ticket ticket) {
        if (ticketCount >= tickets.length) {
            return false;
        }
        tickets[ticketCount] = ticket;
        ticketCount++;
        return true;
    }

    /**
     * Linear search: finds a movie by its title, or returns null.
     */
    public Movie findMovieByTitle(String title) {
        for (int i = 0; i < movieCount; i++) {
            if (movies[i].getTitle().equalsIgnoreCase(title)) {
                return movies[i];
            }
        }
        return null;
    }

    public Movie[] getMovies() {
        return movies;
    }

    public int getMovieCount() {
        return movieCount;
    }

    public Ticket[] getTickets() {
        return tickets;
    }

    public int getTicketCount() {
        return ticketCount;
    }

    /**
     * Adds up the cost of every ticket sold so far.
     */
    public double getTotalRevenue() {
        double total = 0;
        for (int i = 0; i < ticketCount; i++) {
            total += tickets[i].getTotalCost();
        }
        return total;
    }
}
