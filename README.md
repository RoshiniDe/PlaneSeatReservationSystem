# PlaneSeatReservationSystem
# Description
Java program for managing and tracking seat reservations for a private plane company. This project focuses on designing and implementing an efficient system for booking and managing seats.
# Key Features 
Seat Management:
Tracks seat availability using arrays, where available seats are marked as '0' and sold seats as '1'.
Displays the seating plan, showing available seats with 'O' and sold seats with 'X'.

Buy Seat:
Users can buy a seat by entering a row letter and seat number.
Ensures the seat is available before processing the purchase.
Collects personal information (name, surname, email) to create a ticket.

Cancel Seat:
Allows users to cancel a seat reservation, making the seat available again.
Removes the corresponding ticket from the record.

Find First Available Seat:
Automatically finds the first available seat starting from row A to row D.

Ticket Management:
Stores ticket details including seat, price, and customer information (using Person and Ticket classes).
Prints information about all sold tickets, including customer details and total revenue generated.

Search Ticket:
Users can search for a ticket by seat number to view its details, or to check if the seat is still available.

Save Ticket to File:
Saves ticket information to a file named after the row and seat (e.g., A2.txt) when a ticket is sold.

User Menu:
Offers a simple menu interface to interact with the system, with options to buy, cancel, find available seats, and view ticket information.
