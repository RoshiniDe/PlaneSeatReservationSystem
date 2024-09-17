import java.util.Scanner;
public class w2054000_PlaneManagement {
    private static final int ROWS = 4;
    private static final int[] SEATS_PER_ROW = {14,12,12,14};
    private static final int AVAILABLE = 0;
    private static final int SOLD = 1;
    private static int[][] seats; //2D array to represent the seating plan.
    private static Person[] person; //Array to store person's information.
    private static Ticket[] tickets; //Array to store tickets.
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the Plane Management application");
        //initialization for each array.
        initializeSeats();
        initializeTickets();
        initializePerson();
        displayMenu();
    }

    public static void initializeSeats() {
        seats = new int[ROWS][];
        for (int i = 0; i < ROWS; i++) {
            seats[i] = new int[SEATS_PER_ROW[i]];

            for (int j=0; j<SEATS_PER_ROW[i]; j++){
                seats[i][j] = AVAILABLE;
            }
        }
    }

    public static void initializeTickets(){
        tickets = new Ticket[52];
    }
    public static void initializePerson(){
        person = new Person[52];
    }

    public static void displayMenu() {
        boolean exit = false;
        while (!exit) {
            for (int i = 0; i < 50; i++) {
                System.out.print("*");
            }
            System.out.println();
            System.out.println("                 MENU OPTIONS                 ");
            for (int i = 0; i < 50; i++) {
                System.out.print("*");
            }
            System.out.println();
            System.out.println("1. Buy a seat");
            System.out.println("2. Cancel a seat");
            System.out.println("3. Find the first available seat");
            System.out.println("4. Show seating plan");
            System.out.println("5. Print tickets information and total sales");
            System.out.println("6. Search for a ticket");
            System.out.println("0. Exit");

            for (int i = 0; i < 50; i++) {
                System.out.print("*");
            }
            System.out.println();
            System.out.println("Select an option:");

            int choice = scanner.nextInt();
            //switch-cases to handle menu options.
            switch (choice) {
                case 1:
                    buySeat();
                    break;
                case 2:
                    cancelSeat();
                    break;
                case 3:
                    findFirstAvailable();
                    break;
                case 4:
                    showSeatingPlan();
                    break;
                case 5:
                    printTicketsInfo(tickets);
                    break;
                case 6:
                    searchTicket();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice, Please try again.");
                    displayMenu();
                    break;
            }
        }
    }
    public static void buySeat() {
        //prompt user to enter the row letter
        System.out.print("Enter row letter (A/B/C/D):");
        char row = scanner.next().toUpperCase().charAt(0);
        int rowNum = row - 'A';  //converting the row letter to its numerical index.

        if (rowNum<0 || rowNum>=ROWS){
            System.out.println("Invalid row.");
            return;
        }
        //prompt user to enter the seat number
        System.out.print("Enter seat number:");
        int seatNum = scanner.nextInt() -1; //adjustment for zero-based indexing.

        if (seatNum<0 || seatNum>= SEATS_PER_ROW[rowNum]){
            System.out.println("Invalid seat number.");
            return;
        }
        if (seats[rowNum][seatNum] == SOLD){
            System.out.println("Seat already sold.");
            return;
        }

        double price = price(seatNum);  //call to the method that determine ticket price.

        // Ask for passenger's information
        System.out.print("Enter passenger's name:");
        String name = scanner.next();

        System.out.print("Enter passenger's surname:");
        String surname = scanner.next();

        System.out.print("Enter passenger's email:");
        String email = scanner.next();

        for(int i = 0 ; i < 52 ; i++){
            if(tickets[i] == null){
                person[i] = new Person(name, surname, email);
                tickets[i] = new Ticket(String.valueOf(row), seatNum + 1 , price, person[i]);
                tickets[i].save();
                break;
            }
        }
        seats[rowNum][seatNum] = SOLD;
        System.out.println("Seat " + row + (seatNum+1) + " bought successfully.");

    }
    //price logic based on the seat number.
    private static double price(int seatNum){
        if (seatNum < 5){
            return 200;
        } else if (seatNum < 9){
            return 150;
        } else{
            return 180;
        }
    }

    public static void cancelSeat() {
        System.out.print("Enter row letter (A/B/C/D):");
        char row = scanner.next().toUpperCase().charAt(0);
        int rowNum = row - 'A';

        if (rowNum<0 || rowNum>= ROWS){
            System.out.println("Invalid row.");
            return;
        }
        System.out.print("Enter seat number:");
        int seatNum = scanner.nextInt() -1;

        if (seatNum<0 || seatNum>=SEATS_PER_ROW[rowNum]){
            System.out.println("Invalid seat number.");
            return;
        }
        if (seats[rowNum][seatNum] == AVAILABLE){
            System.out.println("Seat is available.");
            return;
        }

        tickets[rowNum * SEATS_PER_ROW[rowNum] + seatNum] = null;
        seats[rowNum][seatNum] = AVAILABLE;
        System.out.println("Seat " + row + (seatNum+1) +  " cancelled successfully.");
    }

    public static void findFirstAvailable() {
        boolean found = false;
        for (int i=0 ; i<ROWS ; i++){
            for (int j=0 ; j<SEATS_PER_ROW[i] ; j++){
                if (seats[i][j] == AVAILABLE){
                    System.out.println("First available seat:"+ (char)('A'+i) + (j+1));
                    found = true;
                    break;
                }
            }
            if (found){
                break;
            }
        }
        if (!found){
            System.out.println("No available seats.");
        }
    }

    public static void showSeatingPlan() {
        System.out.println("Seating Plan:");
        for (int i=0; i<ROWS ; i++){
            char rowLetter = (char)('A'+ i);
            System.out.print(rowLetter + " ");
            for (int j=0 ; j<SEATS_PER_ROW[i] ; j++){
                if (seats[i][j] == AVAILABLE) {
                    System.out.print("O");
                }
                else{
                    System.out.print("X");
                }
            }
            System.out.println();
        }
    }
    public static void printTicketsInfo(Ticket[] tickets){
        System.out.println("Tickets Information:");
        double totalSales = 0;
        for (int i = 0 ; i < 52 ; i++){
            if (tickets[i] != null){
                tickets[i].printInfo();
                totalSales += tickets[i].getPrice();
                System.out.println();
            }
        }
        System.out.println("Total Sales: £" + totalSales);
    }
    public static void searchTicket(){
        System.out.print("Enter row letter (A/B/C/D):");
        char row = scanner.next().toUpperCase().charAt(0);
        int rowNum = row - 'A';

        if (rowNum<0 || rowNum>=ROWS){
            System.out.println("invalid row.");
            return;
        }
        System.out.print("Enter seat number:");
        int seatNum= scanner.nextInt();

        if (seatNum<1 || seatNum>SEATS_PER_ROW[rowNum]){
            System.out.println("Invalid seat number.");
            return;
        }

        boolean ticketFound = false;
        for (Ticket ticket : tickets){
            if (ticket != null && ticket.getRow().equals(String.valueOf(row)) && ticket.getSeat() == seatNum){
                ticket.printInfo();
                ticketFound = true;
                break;
            }
        }
        if (!ticketFound){
            System.out.println("This seat is available.");
        }
    }
}
