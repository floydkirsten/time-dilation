import java.util.Scanner;

public class TimeDilation {
    public static void main(String[] args) {
        //initialize variables
        double observerTime = 0; 
        double v = 0;
        int t = 0;
        boolean active = true; //currently calculating
        final double c = 300000000; //speed of light

        Scanner input = new Scanner(System.in);

        while (active) {
            System.out.println("\nHello! Let's calculate the effects of time dilation.");

            //get and check observer time
            boolean validT = false; //indicates if observer time has been validated
            while (!validT) {
                System.out.print("Enter t\u2092 in yrs (time in observer's frame of reference): ");
                if (input.hasNextDouble()) { //if user inputs a number
                    observerTime = input.nextDouble();
                    if (observerTime >= 0) { //if non-negative
                        validT = true; //observer time is valid
                        input.nextLine();
                    } else {
                        System.out.println("Must enter a non-negative value.");
                        input.nextLine();
                    }
                } else {
                    System.out.println("Must enter a number.");
                    input.nextLine();
                }  
            }
    
            //get and check velocity
            boolean validV = false; //indicates if velocity has been validated
            System.out.print("\n");
            while (!validV) {
                System.out.print("Enter v in m/s (speed of moving object): ");
                if (input.hasNextDouble()) { //if user inputs a number
                    v = input.nextDouble();
                    if (v >= 0 && v < c) { //if non-negative and less than speed of light
                        validV = true; //velocity is valid
                    } else if (v >= c){ //if greated than speed of light
                        System.out.println("Value of v must be less than the speed of light.");
                        input.nextLine();
                    } else {
                        System.out.println("Must enter a non-negative value.");
                        input.nextLine();
                    }
                } else {
                    System.out.println("Must enter a number.");
                    input.nextLine();
                }  
            }
    
            //calculate t
            t = performCalculation(observerTime, v, c);
    
            //print results
            System.out.println("-------------------------------------");
            System.out.println("For values t\u2092 = " + observerTime + " yrs and v = " + v + " m/s\n");
            System.out.println("t (time observed in other reference frame) = " + t + " yrs\n");
    
            //start new calulation or exit?
            System.out.print("Would you like to perform another calculation? (yes/no) ");
            input.nextLine();
            if (input.nextLine().equalsIgnoreCase("yes")) {
                active = true;
            } else {
                active = false;
            }
        }

        //if user wants to exit
        input.close();
        System.out.println("Bye!");
        System.exit(0);
    }

    public static int performCalculation(double to, double v, double c) {
        int roundedOutput = 0; //initialize output
        
        try { //attempt calculation
            double output = (to) / Math.sqrt(1 - ((Math.pow(v, 2))/(Math.pow(c, 2)))) ;
            roundedOutput = (int) Math.round(output); //rounds output to nearest integer
        } catch (Exception e) {
            System.out.println("Could not execute calculation.");
        }
        return roundedOutput;
    }
}
