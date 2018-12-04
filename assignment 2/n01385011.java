/* Ronald Shevchenko, n01385011
   This assignment gets an odd integer from the user and 
   uses it to get the sum of a specified series and to approximate pi. */
   
import java.util.Scanner;

public class n01385011 {
   public static void main (String []args){
   
      Scanner input = new Scanner(System.in);
      System.out.println("Please enter a single, odd, positive integer: ");
      double number = input.nextInt();
      while(number % 2 == 0 || number < 0){ //checks to see if the number is even/negative.
         System.out.println("Please try again. Your number was even and/or negative.");
         number = input.nextInt();
      }
      
      double i = 1, j = 3, k = 1; 
      double sum = 0, pi = 0;      
      
      while(i  != number){ // sums the series given the input
         sum = sum + (i/j);
         i += 2;
         j += 2;
      }
      
      while(k != number){ // approximates pi given the input
         pi = pi + (Math.pow(-1,(k+1)))/(2*k - 1);
         k++;
      }
      
      pi = pi*4;
      
      System.out.printf("Sum of the series: %2.12f\n", sum);
      System.out.printf("Approximation of pi: %2.12f", pi);
   }


}