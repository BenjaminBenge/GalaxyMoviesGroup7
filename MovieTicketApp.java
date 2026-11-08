import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * MovieTicketApp.java
 * CSC 1102 Practical Exam Project - Movie Ticket Booking System
 *
 *   Group name: Group 7
 *   Members:
 *     1. Benjamin Benge       26/U/31392/EVE
 *     2. Kirungi Jonah        26/U/03535/PSA
 *     3. Ssekide Isma         26/U/03639/PSA
 *     4. Apio Immaculate      26/U/03488/PSA
 *     5. Mirembe Patricia     26/U/03568/PSA
 *   GitHub repository: https://github.com/BenjaminBenge/GalaxyMoviesGroup7
 *
 * A simple Swing application that lets a customer browse upcoming
 * blockbuster movies and book tickets for them.
 * Demonstrates: OOP design (Movie, Ticket, Cinema), arrays of objects,
 * if/else, switch, for, while and do-while constructs.
 */
public class MovieTicketApp {
    private Cinema cinema;
    private JFrame frame;
    private JScrollPane scrollPane;
    private JPanel moviesPanel;
    private JLabel[] seatsLabels;   // one label per movie card
    private JButton[] bookButtons;  // one button per movie card
    private JLabel revenueLabel;
    private JButton themeButton;

    private boolean darkMode = true; // start in dark mode

    // Theme colors - switched between by applyTheme()
    private Color background;
    private Color cardBackground;
    private Color textColor;
    private Color subtleText;
    private Color accent;

    private static final int POSTER_W = 200;
    private static final int POSTER_H = 280;

    public MovieTicketApp() {
        cinema = new Cinema("Galaxy Cinemas", 10, 200);
        loadMovies();
        buildGui();
    }

    /**
     * Fills the movies array with this season's upcoming blockbusters.
     * Poster images were taken from the movies' official websites.
     */
    private void loadMovies() {
        cinema.addMovie(new Movie("Avengers: Doomsday", "Action / Sci-Fi",
                "18 Dec 2026", 15000, "posters/avengers_doomsday.png", 60));
        cinema.addMovie(new Movie("The Hunger Games: Sunrise on the Reaping",
                "Action / Adventure", "20 Nov 2026", 12000,
                "posters/hunger_games.jpg", 50));
        cinema.addMovie(new Movie("Dune: Part Three", "Sci-Fi / Drama",
                "18 Dec 2026", 15000, "posters/dune_part_three.jpg", 60));
        cinema.addMovie(new Movie("Ice Age: Boiling Point", "Animation / Family",
                "5 Feb 2027", 10000, "posters/ice_age.jpg", 80));
        cinema.addMovie(new Movie("The Batman: Part II", "Action / Crime",
                "1 Oct 2027", 14000, "posters/batman_part_ii.jpg", 50));
        cinema.addMovie(new Movie("Frozen 3", "Animation / Family",
                "24 Nov 2027", 10000, "posters/frozen_3.jpg", 80));
    }

    /**
     * Sets the palette for the current mode.
     */
    private void applyTheme() {
        if (darkMode) {
            background = new Color(18, 20, 30);
            cardBackground = new Color(34, 37, 52);
            textColor = new Color(235, 235, 240);
            subtleText = new Color(160, 164, 182);
            accent = new Color(232, 179, 61);     // cinema gold
        } else {
            background = new Color(244, 245, 248);
            cardBackground = Color.WHITE;
            textColor = new Color(33, 37, 41);
            subtleText = new Color(108, 117, 125);
            accent = new Color(184, 134, 11);     // darker gold for light bg
        }
    }

    private void buildGui() {
        applyTheme();

        frame = new JFrame(cinema.getName() + " - Movie Ticket Booking");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(background);

        // ----- Header -----
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(background);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(16, 20, 12, 20));

        JLabel titleLabel = new JLabel(cinema.getName());
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(accent);

        JLabel subLabel = new JLabel("Upcoming Blockbusters  -  Book Your Tickets Early");
        subLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        subLabel.setForeground(subtleText);
        subLabel.setBorder(BorderFactory.createEmptyBorder(4, 2, 0, 0));

        JPanel titleBlock = new JPanel();
        titleBlock.setLayout(new BoxLayout(titleBlock, BoxLayout.Y_AXIS));
        titleBlock.setBackground(background);
        titleBlock.add(titleLabel);
        titleBlock.add(subLabel);
        headerPanel.add(titleBlock, BorderLayout.WEST);

        themeButton = styledButton(darkMode ? "Light Mode" : "Dark Mode", false);
        themeButton.addActionListener(e -> toggleTheme());
        JPanel themeWrap = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        themeWrap.setBackground(background);
        themeWrap.add(themeButton);
        headerPanel.add(themeWrap, BorderLayout.EAST);

        frame.add(headerPanel, BorderLayout.NORTH);

        // ----- Movie cards inside a scroll pane -----
        int count = cinema.getMovieCount();
        seatsLabels = new JLabel[count];
        bookButtons = new JButton[count];

        moviesPanel = new JPanel(new GridLayout(0, 3, 16, 16));
        moviesPanel.setBackground(background);
        moviesPanel.setBorder(BorderFactory.createEmptyBorder(4, 20, 16, 20));

        Movie[] movies = cinema.getMovies();
        for (int i = 0; i < count; i++) {
            moviesPanel.add(createMovieCard(movies[i], i));
        }

        scrollPane = new JScrollPane(moviesPanel);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(background);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        frame.add(scrollPane, BorderLayout.CENTER);

        // ----- Footer -----
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 24, 12));
        footerPanel.setBackground(background);

        JButton viewBookingsButton = styledButton("View My Bookings", true);
        viewBookingsButton.addActionListener(e -> showBookings());
        footerPanel.add(viewBookingsButton);

        revenueLabel = new JLabel("Total revenue: UGX 0");
        revenueLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        revenueLabel.setForeground(textColor);
        footerPanel.add(revenueLabel);

        frame.add(footerPanel, BorderLayout.SOUTH);

        frame.setSize(860, 720);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Creates a button with flat styling. Accent buttons are filled gold.
     */
    private JButton styledButton(String text, boolean filled) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 18, 8, 18));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        if (filled) {
            button.setBackground(accent);
            button.setForeground(new Color(20, 20, 20));
        } else {
            button.setBackground(cardBackground);
            button.setForeground(textColor);
            button.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(subtleText),
                    BorderFactory.createEmptyBorder(7, 17, 7, 17)));
        }
        return button;
    }

    /**
     * Rebuilds the window in the opposite theme.
     */
    private void toggleTheme() {
        darkMode = !darkMode;
        frame.dispose();
        buildGui();
        // restore any sold-out states lost when the cards were rebuilt
        Movie[] movies = cinema.getMovies();
        for (int i = 0; i < cinema.getMovieCount(); i++) {
            updateCard(i);
        }
    }

    /**
     * Builds one card: poster, details, seats left and a Book button.
     */
    private JPanel createMovieCard(Movie movie, int index) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(cardBackground);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(darkMode ? new Color(60, 64, 84)
                        : new Color(220, 223, 230)),
                BorderFactory.createEmptyBorder(12, 12, 12, 12)));

        JLabel posterLabel = new JLabel(loadPoster(movie.getPosterFile()));
        posterLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(posterLabel);
        card.add(Box.createVerticalStrut(10));

        card.add(centeredLabel("<html><div style='text-align:center;'>"
                + movie.getTitle() + "</div></html>", Font.BOLD, 14, textColor));
        card.add(Box.createVerticalStrut(4));
        card.add(centeredLabel(movie.getGenre(), Font.PLAIN, 11, subtleText));
        card.add(centeredLabel("Release: " + movie.getReleaseDate(), Font.PLAIN, 11, subtleText));
        card.add(centeredLabel("Ticket: UGX "
                + String.format("%,.0f", movie.getTicketPrice()), Font.BOLD, 12, accent));
        card.add(Box.createVerticalStrut(2));

        seatsLabels[index] = new JLabel();
        seatsLabels[index].setFont(new Font("Segoe UI", Font.PLAIN, 11));
        seatsLabels[index].setAlignmentX(Component.CENTER_ALIGNMENT);
        refreshSeatsLabel(movie, index);
        card.add(seatsLabels[index]);

        card.add(Box.createVerticalStrut(8));
        bookButtons[index] = styledButton("Book Ticket", true);
        bookButtons[index].setAlignmentX(Component.CENTER_ALIGNMENT);
        bookButtons[index].addActionListener(e -> bookTicket(movie, index));
        card.add(bookButtons[index]);

        return card;
    }

    /**
     * Small helper that builds a centered, theme-coloured label.
     */
    private JLabel centeredLabel(String text, int style, int size, Color color) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", style, size));
        label.setForeground(color);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    /**
     * Colours the seats label: red when sold out, orange when few are left.
     */
    private void refreshSeatsLabel(Movie movie, int index) {
        int left = movie.getSeatsAvailable();
        if (movie.isSoldOut()) {
            seatsLabels[index].setText("Sold out");
            seatsLabels[index].setForeground(new Color(220, 80, 80));
        } else if (left <= 10) {
            seatsLabels[index].setText("Only " + left + " seat(s) left!");
            seatsLabels[index].setForeground(new Color(230, 140, 40));
        } else {
            seatsLabels[index].setText("Seats left: " + left);
            seatsLabels[index].setForeground(subtleText);
        }
    }

    /**
     * Loads a poster image, scales it to COVER a fixed 200x280 box and
     * centre-crops the overflow, so every poster is exactly the same size.
     * The path is resolved relative to this class file, so the app works
     * no matter which directory it is launched from. If the file is
     * missing, a labelled placeholder is drawn instead.
     */
    private ImageIcon loadPoster(String path) {
        java.net.URL url = MovieTicketApp.class.getResource(path);
        ImageIcon icon = (url != null) ? new ImageIcon(url) : new ImageIcon(path);

        if (icon.getIconWidth() <= 0) {
            return missingPoster(path);
        }

        double scale = Math.max((double) POSTER_W / icon.getIconWidth(),
                (double) POSTER_H / icon.getIconHeight());
        int w = (int) Math.ceil(icon.getIconWidth() * scale);
        int h = (int) Math.ceil(icon.getIconHeight() * scale);

        // getScaledInstance loads lazily; wrapping it in a new ImageIcon
        // forces the pixels to be ready before we paint.
        Image scaled = icon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
        Image scaledReady = new ImageIcon(scaled).getImage();

        BufferedImage buffer = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = buffer.createGraphics();
        g.drawImage(scaledReady, 0, 0, null);
        g.dispose();

        BufferedImage cropped = buffer.getSubimage(
                (w - POSTER_W) / 2, (h - POSTER_H) / 2, POSTER_W, POSTER_H);
        return new ImageIcon(cropped);
    }

    /**
     * Draws a simple placeholder poster used when an image file is missing.
     */
    private ImageIcon missingPoster(String path) {
        BufferedImage image = new BufferedImage(POSTER_W, POSTER_H,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        g.setColor(new Color(60, 64, 84));
        g.fillRect(0, 0, POSTER_W, POSTER_H);
        g.setColor(new Color(170, 174, 192));
        g.setFont(new Font("Segoe UI", Font.BOLD, 14));
        String text = "Poster missing";
        int textWidth = g.getFontMetrics().stringWidth(text);
        g.drawString(text, (POSTER_W - textWidth) / 2, POSTER_H / 2);
        g.dispose();
        return new ImageIcon(image);
    }

    /**
     * Booking flow: asks for the customer's name using the while loop,
     * the number of seats using the do-while loop and the payment method
     * using the switch, then creates and stores a Ticket.
     */
    private void bookTicket(Movie movie, int index) {
        if (movie.isSoldOut()) {
            JOptionPane.showMessageDialog(frame,
                    "Sorry, " + movie.getTitle() + " is sold out!");
            return;
        }

        // --- Ask for customer name; keep asking until it is not blank ---
        String name = "";
        while (name.trim().isEmpty()) {
            name = JOptionPane.showInputDialog(frame,
                    "Enter your name for \"" + movie.getTitle() + "\":");
            if (name == null) {
                return; // user pressed Cancel
            }
            if (name.trim().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Name cannot be empty.");
            }
        }
        name = name.trim();

        // --- Ask for number of seats; repeat until a valid amount ---
        int seats = -1;
        do {
            String input = JOptionPane.showInputDialog(frame,
                    "How many seats? (1 - " + movie.getSeatsAvailable() + ")");
            if (input == null) {
                return; // user pressed Cancel
            }
            try {
                seats = Integer.parseInt(input.trim());
            } catch (NumberFormatException ex) {
                seats = -1;
            }
            if (seats < 1 || seats > movie.getSeatsAvailable()) {
                JOptionPane.showMessageDialog(frame,
                        "Please enter a number between 1 and "
                                + movie.getSeatsAvailable() + ".");
                seats = -1;
            }
        } while (seats < 1);

        // --- Choose payment method; a switch sets the processing fee ---
        String[] methods = {"Cash", "Mobile Money", "Card"};
        String payment = (String) JOptionPane.showInputDialog(frame,
                "Choose a payment method:", "Payment",
                JOptionPane.QUESTION_MESSAGE, null, methods, methods[0]);
        if (payment == null) {
            return; // user pressed Cancel
        }

        double fee;
        switch (payment) {
            case "Card":
                fee = 0.02;   // 2% card processing fee
                break;
            case "Mobile Money":
                fee = 0.01;   // 1% mobile money fee
                break;
            default:          // Cash - no fee
                fee = 0.0;
                break;
        }
        double totalCost = seats * movie.getTicketPrice() * (1 + fee);

        // --- Reserve the seats and store the ticket ---
        if (movie.bookSeats(seats)) {
            Ticket ticket = new Ticket(name, movie, seats, payment, totalCost);
            cinema.addTicket(ticket);
            JOptionPane.showMessageDialog(frame,
                    "Booking confirmed!\n\n" + ticket.getSummary());
        } else {
            JOptionPane.showMessageDialog(frame, "Booking failed.");
        }

        updateCard(index);
    }

    /**
     * Refreshes the seats label, the revenue and the button state
     * of the card at the given index.
     */
    private void updateCard(int index) {
        Movie movie = cinema.getMovies()[index];
        refreshSeatsLabel(movie, index);
        if (movie.isSoldOut()) {
            bookButtons[index].setText("Sold Out");
            bookButtons[index].setEnabled(false);
        }
        revenueLabel.setText("Total revenue: UGX "
                + String.format("%,.0f", cinema.getTotalRevenue()));
    }

    /**
     * Shows every ticket booked so far in a scrollable text area.
     */
    private void showBookings() {
        Ticket[] tickets = cinema.getTickets();
        StringBuilder sb = new StringBuilder();

        if (cinema.getTicketCount() == 0) {
            sb.append("No tickets booked yet.");
        } else {
            for (int i = 0; i < cinema.getTicketCount(); i++) {
                sb.append(tickets[i].getSummary()).append("\n");
            }
            sb.append("\nTotal revenue: UGX ")
                    .append(String.format("%,.0f", cinema.getTotalRevenue()));
        }

        JTextArea area = new JTextArea(sb.toString(), 12, 55);
        area.setEditable(false);
        JOptionPane.showMessageDialog(frame, new JScrollPane(area),
                "My Bookings", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MovieTicketApp());
    }
}
