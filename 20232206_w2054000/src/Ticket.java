import java.io.FileWriter;
import java.io.IOException;
public class Ticket {
    private String row;
    private int seat;
    private double price;
    private Person person;

    //Constructor to initialize ticket object.
    public Ticket(String row, int seat, double price, Person person) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    //Getter and setter methods to retrieve and set ticket information.
    public String getRow(){
        return row;
    }
    public void setRow(String row){
        this.row = row;
    }
    public int getSeat(){
        return seat;
    }
    public void setSeat(int seat){
        this.seat = seat;
    }
    public double getPrice(){
        return price;
    }
    public void setPrice(double price){
        this.price = price;
    }
    public Person getPerson(){
        return person;
    }
    public void setPerson(Person person){
        this.person = person;
    }

    //Code to print the information of the ticket to the console.
    public void printInfo() {
        System.out.println("Row: " + row);
        System.out.println("Seat: " + seat);
        System.out.println("Price: £" + price);
        System.out.println("Person Information:");
        person.printInfo();
    }

    //method to save ticket information to a text file.
    public void save() {
        String fileName = row + seat + ".txt";
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write("Row:" + row + "\n");
            fileWriter.write("Seat:" + seat + "\n");
            fileWriter.write("Price:" + price + "\n");
            fileWriter.write("Person Information:\n");
            fileWriter.write("Name:" + person.getName() + "\n");
            fileWriter.write("Surname:" + person.getSurname() + "\n");
            fileWriter.write("Email:" + person.getEmail() + "\n");
            fileWriter.close(); //Close the file writer.
            System.out.println("Ticket information saved to " + fileName);
        }
        catch (IOException e){
            System.out.println("An error occurred while saving the ticket information.");
        }
    }
}