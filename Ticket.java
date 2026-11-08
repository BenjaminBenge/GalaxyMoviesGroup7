/**
 * Ticket.java
 * Represents a ticket booked by a customer for a particular movie.
 * Demonstrates: composition (a Ticket HAS-A Movie), static fields,
 * constructors, getters.
 */
public class Ticket {
    private static int nextTicketId = 1; // shared by all Ticket objects

    private int ticketId;
    private String customerName;
    private Movie movie;
    private int numberOfSeats;
    private String paymentMethod;
    private double totalCost;

    public Ticket(String customerName, Movie movie, int numberOfSeats,
                  String paymentMethod, double totalCost) {
        this.ticketId = nextTicketId++;
        this.customerName = customerName;
        this.movie = movie;
        this.numberOfSeats = numberOfSeats;
        this.paymentMethod = paymentMethod;
        this.totalCost = totalCost;
    }

    public int getTicketId() {
        return ticketId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public double getTotalCost() {
        return totalCost;
    }

    /**
     * Returns a printable summary of the ticket.
     */
    public String getSummary() {
        return "Ticket #" + ticketId
                + " | " + customerName
                + " | " + movie.getTitle()
                + " | " + numberOfSeats + " seat(s)"
                + " | UGX " + String.format("%,.0f", totalCost)
                + " | " + paymentMethod;
    }
}
