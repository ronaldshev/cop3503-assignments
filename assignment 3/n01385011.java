/* Ronald Shevchenko
   n01385011
      This program gathers two arrays, and runs them through a series of tests. */

import java.util.Scanner;

public class n01385011 {
   public static void main (String []args){
   Scanner input = new Scanner(System.in);
   
   System.out.println("Please type in 18 numbers.");

   // initializes two arrays
   int[][] m1 = new int[3][3];
   int[][] m2 = new int[3][3];
   
   //gathers data from the users about the arrays
   int i;
   int j;
   for(i = 0; i <= 2; i++){
      for(j = 0; j <= 2; j++){
         m1[i][j] = input.nextInt();
      }
   }
   for(i = 0; i <= 2; i++){
      for(j = 0; j <= 2; j++){
         m2[i][j] = input.nextInt();
      }
   } 
   
   System.out.println("\nAre the arrays identical? " + Strict.equals(m1, m2));
   System.out.println("\nHow many values in the arrays are identical? " + Strict.howmany(m1, m2));
   System.out.println("\nHow many values on the diagonal are identical? " + Strict.diagonal(m1, m2));
   System.out.println("\nWhat is the average of the two arrays? " + Strict.average(m1, m2) + "\n");
   Strict.display(m1, m2);
   System.out.println("\nAre the arrays 'silly'? " + Strict.silly(m1, m2));
   }
}

class Strict {
   // equals method
   public static boolean equals(int[][] m1, int[][] m2){
      boolean equal = true;
      
      for(int i = 0; i <= 2; i++){
         for(int j = 0; j <= 2; j++){
            if(m1[i][j] != m2[i][j]){
               equal = false;
            }
         }
      }
      return equal;
   }
    
   //howmany method
   public static int howmany(int[][] m1, int[][] m2){
      int thisMany = 0;
      
      for(int i = 0; i <= 2; i++){
         for(int j = 0; j <= 2; j++){
            if(m1[i][j] == m2[i][j]){
               thisMany++;
            }
         }
      }
      return thisMany;
   }
   
   //diagonal method
   public static int diagonal(int[][] m1, int[][] m2){
      int diagonalSum = 0, i = 0, j = 0;
      
      while(i <= 2){
         if(m1[i][j] == m2[i][j]){
            diagonalSum++;
         }     
         i++;
         j++;
      }
      return diagonalSum;
   }
   
   //average method
   public static double average(int[][] m1, int[][] m2){
      double sum = 0;
      for(int i = 0; i <= 2; i++){
         for(int j = 0; j <= 2; j++){
            sum += m1[i][j] + m2[i][j];
            }
         }
      sum = sum/18;
      return sum;
      }
 
   //display method
   public static void display(int[][] m1, int[][] m2){
      
      for (int i = 0; i < m1.length; i++)
      {
         String line = "";
         for (int j = 0; j < m1[0].length; j++)
         {
            if (m1[i][j]%2 != 0)
            {
               if (m1[i][j] < 10)
               {
                  line += "0";
               }
               line += m1[i][j];
            }
            else
            {
               line += "  ";
            }
            if (j < m1[0].length-1)
               line += "|";
         }
         System.out.println(line);
         if (i < m1.length-1)
            System.out.println("--+--+--");
      }
      System.out.println("");
      for (int i = 0; i < m2.length; i++)
      {
         String line = "";
         for (int j = 0; j < m2[0].length; j++)
         {
            if (m2[i][j]%2 != 0)
            {
               if (m2[i][j] < 10)
               {
                  line += "0";
               }
               line += m2[i][j];
            }
            else
            {
               line += "  ";
            }
            if (j < m2[0].length-1)
               line += "|";
         }
         System.out.println(line);
         if (i < m2.length-1)
            System.out.println("--+--+--");
      }
   }
   
   //silly method
   public static boolean silly(int[][] m1, int[][] m2){
      boolean satisfies = true;
      
      for(int i = 0; i <= 2; i++){
         for(int j = 0; j <= 2; j++){
            if(((1 < m1[i][j]) && (m1[i][j] <= 10)) &&
            ((1 < m2[i][j]) && (m2[i][j] <= 10))){
            }
            else{
               satisfies = false;
            }
         }
      }
      return satisfies;
   }
   
}