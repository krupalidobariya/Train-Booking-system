
import java.util.Scanner;
import java.util.Date;
import java.io.*;
 
public class Booking1{

    // Create an array of 12 seats, 6 window and 6 aisle.
    private static int[] seats = new int[12];

    public static void main(String args[]) {
        System.out.println("***** Welcome to the GUJARAT EXPRESS train reservation system! *****");
Scanner sc=new Scanner(System.in);
    try{
         
    //Close the output stream
   
    System.out.println("Enter current place:");
        String p=sc.nextLine();   
        System.out.println("The current location is:"+p);
    System.out.println("Enter the going place:");
        String g=sc.nextLine();   
        System.out.println("The going place is:"+g);

        System.out.println("How many seats you are booking:");
                int sb =sc.nextInt();
        System.out.println("The seats are booking:"+sb);
         
        System.out.println("Enter name: ");
         String name= sc.next();
         
        System.out.println("Enter Gender (Male/Female): ");
        String gender=sc.next();
          System.out.println("Name: " + name);
        System.out.println("Gender: " + gender);
     
        System.out.println();
        
        // Lets start by setting all seats equal to 0 (aka Empty)
        for (int i = 0; i < 12; i++) {
            seats[i] = 0;
        }

        // Setup our scanner and default the choice to window.
        Scanner s = new Scanner(System.in);
        int choice = 1;

        // Ask user for a window or an aisle seat and store their coice.
        System.out.print("Please enter 1 for window, 2 for aisle, or 0 to exit: ");
        choice = s.nextInt();

        // While their choice is not the one for exit, execute our booking.
        while (choice != 0) {
            int seatnumber = 0;

            // If they chose a window seat, attempt to book it.
            if (choice == 1) {
                seatnumber = bookWindow();

                // No window seats available, try booking an aisle seat for them instead.
                if (seatnumber == -1) {
                    seatnumber = bookAisle();
                
                    if (seatnumber != -1) {
                        System.out.println("Sorry, we were not able to book a window seat. But do have an aisle seat.");
                        printBoardingPass(seatnumber,name,p,g);
                    }
                }
                else {
                    // Booking a window seat was successful.
                    System.out.println("You are luckie, we have a window seat available!");
                    System.out.println("your seat is window");
                    printBoardingPass(seatnumber,name,p,g);
                }
            }
            else if (choice == 2) {

                // If they chose booking an isle, check to see if it is available.
                seatnumber = bookAisle();
            
                // If not available, see if we have window seats available.
                if (seatnumber == -1) {
                    seatnumber = bookWindow();

                    if (seatnumber != -1) {
                        System.out.println("Sorry, we were not able to book an aisle seat. But do have a window seat.");
                        printBoardingPass(seatnumber,name,p,g);
                    }
                }
                else {
                    // Booking an aisle seat was successful.
                    System.out.println("You are in luck, we have an aisle seat available!");
                                    System.out.println("your seat is aisle");
                                printBoardingPass(seatnumber,name,p,g);
                }
            }
            else {
                // Print an error message if they did not choose 1, 2, or 0 for their choice.
                System.out.println("Invalid choice made. Please try again!");
                choice = 0;
            }

            // No window or aisle seats were available. 
            if (seatnumber == -1) {
                System.out.println("We are sorry, there are no window or aisle seats available.");
                System.out.println();
            }
            
            // Reprompt for a choice
            System.out.println("For enter details of another ticket.");
            System.out.print("Please enter 1 for window, 2 for aisle, or 0 to exit: ");
            choice = s.nextInt();
        }

     }  
     catch(Exception e)
    {
        System.out.println(e);
    }

    }

    // This function checks for window seats and returns seat number or -1 if full.
    private static int bookWindow() {
        for (int i = 0; i < 6; i++) {
            if (seats[i] == 0) {
                seats[i] = 1;
                return i + 1;
            }
        }
        return -1;
    }

    // This function checks to see if aisle seats were available, -1 if full.
    private static int bookAisle() {
        for (int i = 6; i < 12; i++) {
            if (seats[i] == 0) {
                seats[i] = 1;
                return i + 1;
            }
        }
        return -1;

    }

    // This simply prints out a nice little boarding pass message with their seat number and date of issue.
    private static void printBoardingPass(int seatnumber,String name,String p,String g) throws IOException {
        FileWriter fstream = new FileWriter("Record.txt");
        BufferedWriter out = new BufferedWriter(fstream);
	out.write("Name:");            
	out.write(name);
            out.newLine();
           out.write("Seat number:");
            out.write("1");
 	out.newLine();
             out.write("current location:");
            out.write(p);
 	out.newLine();
            out.write("Arrival location:"); 
            out.write(g);
            out.close();
        Date timenow = new Date();
        System.out.println();
        System.out.println("Date: " + timenow.toString());
        System.out.println("Name: "+ name);
        System.out.println("Boarding pass for seat number: " + seatnumber);
        System.out.println("Your ticket is from " + p +" to " + g);
        System.out.println("Your traveler time is: 4:30 hours");
        System.out.println("Your departure time is:8:30 pm");
        System.out.println("This ticket is non-refundable and non-transferable.");
        System.out.println("Please be curteous, do not smoke. Enjoy your trip.");
        System.out.println("******* THANK YOU ******");
        System.out.println();

}

}

