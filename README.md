# Galaxy Cinemas — Movie Ticket Booking System

CSC 1102 Practical Exam Project — a simple Java Swing application for booking
tickets to upcoming blockbuster movies.

## Group Members

| Name | Registration Number |
|---|---|
| Benjamin Benge | 26/U/31392/EVE |
| Kirungi Jonah | 26/U/03535/PSA |
| Ssekide Isma | 26/U/03639/PSA |
| Apio Immaculate | 26/U/03488/PSA |
| Mirembe Patricia | 26/U/03568/PSA |

## Features

- Browse upcoming blockbusters with official poster art
- See ticket price, release date and seats remaining per movie
- Book tickets: name, number of seats, payment method (Cash / Mobile Money / Card)
- Automatic cost calculation with per-method processing fee
- View all bookings and total revenue
- Light / dark mode toggle

## Classes

| Class | Purpose |
|---|---|
| `Movie` | A movie: title, genre, release date, price, poster, seat counts |
| `Ticket` | A booking: customer, movie, seats, payment, cost, unique ticket no. |
| `Cinema` | Manages `Movie[]` and `Ticket[]` arrays; search + revenue |
| `MovieTicketApp` | Swing GUI and `main()` entry point |

## How to Run

```
javac *.java
java MovieTicketApp
```

Keep the `posters/` folder next to the classes — images load relative to them.
