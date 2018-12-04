/* ronald shevchenko, n01385011
   this program gathers distance needed to drive, miles per gallon the car drives, 
   the price per gallon, and then displays the cost of the trip */

import java.util.Scanner;

public class n01385011 {
   public static void main(String []args){
   
   Scanner input = new Scanner(System.in);
   
   System.out.println("Enter the driving distance: ");
   double milesNeeded = input.nextDouble();
   
   System.out.println("Enter miles per gallon: ");
   double mPG = input.nextDouble();
   
   System.out.println("Enter price per gallon: ");
   double priceGallon = input.nextDouble();
   
   double totalCost = ((milesNeeded / mPG) * priceGallon);
  
   System.out.println("The cost of driving is $" + ((int)(totalCost * 100) / 100.0));
   
   
   }
}