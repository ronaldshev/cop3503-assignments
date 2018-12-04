import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class n01385011
{
   public static void main(String[] args) 
   {
      ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
      
      // Scans the file
      
      try
      {
         File file = new File(args[0]);
         Scanner in = new Scanner(file);
         
         String line;
         while (in.hasNext())
         {
            line = in.nextLine();
            if (line.equals("vehicle") || line.equals("car") || line.equals("truck") ||
                line.equals("american car") || line.equals("foreign car") || 
                line.equals("bicycle"))
            {
               String ownerName = in.nextLine();
               String address = in.nextLine();
               String phone = in.nextLine();
               String email = in.nextLine();
               
               if (line.equals("vehicle"))
               {
                  vehicles.add(new Vehicle(ownerName, address, phone, email));
               }
               if (line.equals("car"))
               {
                  boolean convertible = in.nextBoolean();
                  in.nextLine();
                  String color = in.nextLine();
                  vehicles.add(new Car(ownerName, address, phone, email, convertible, color));
               }
               if (line.equals("truck"))
               {
                  float numTons = in.nextFloat();
                  in.nextLine();
                  float cost = in.nextFloat();
                  in.nextLine();
                  vehicles.add(new Truck(ownerName, address, phone, email, numTons, cost));
               }
               if (line.equals("american car"))
               {
                  boolean convertible = in.nextBoolean();
                  in.nextLine();
                  String color = in.nextLine();
                  boolean madeInDetroit = in.nextBoolean();
                  in.nextLine();
                  boolean unionShop = in.nextBoolean();
                  in.nextLine();
                  vehicles.add(new AmericanCar(ownerName, address, phone, email, convertible, color,
                                               madeInDetroit, unionShop));
               }
               if (line.equals("foreign car"))
               {
               
                  boolean convertible = in.nextBoolean();
                  in.nextLine();
                  String color = in.nextLine();
                  String country = in.nextLine();
                  float importDuty = in.nextFloat();
                  in.nextLine();
                  vehicles.add(new ForeignCar(ownerName, address, phone, email, convertible, color,
                                              country, importDuty));
               }
               if (line.equals("bicycle"))
               {
                  int speeds = Integer.parseInt(in.nextLine());
                  vehicles.add(new Bicycle(ownerName, address, phone, email, speeds));
               }
            }
         }
      }
      catch (Exception e)
      {
      
      }
      // Prints the Results
      
      printAll(vehicles);
      System.out.println("\n________");
      
      vehicles = sortRecords(vehicles);
      printAll(vehicles);
      System.out.println("\n________\n\n");
      
      printNumRecords(vehicles);
      System.out.println("\n________");
      
      printBikesAndTrucks(vehicles);
      System.out.println("\n________");
      
      printPhoneNumber(vehicles);
   }
   
   public static void printAll(ArrayList<Vehicle> vehicles)
   {
      for (Vehicle v : vehicles)
      {
         System.out.println(v.toString());
      }
   }
   
   // Sorts Emails Alphabetically
   
   public static ArrayList<Vehicle> sortRecords(ArrayList<Vehicle> vehicles)
   {
      Collections.sort(vehicles, new Comparator<Vehicle>() 
      {
         public int compare(Vehicle v1, Vehicle v2) 
         {
            return v1.getEmail().compareTo(v2.getEmail());
         }
      });
      return vehicles;
   }
   
   // Prints Number of Records
   
   public static void printNumRecords(ArrayList<Vehicle> vehicles)
   {
      System.out.println(vehicles.size());
   }
   
   // Prints Just Bikes and Trucks After Sorted
   
   public static void printBikesAndTrucks(ArrayList<Vehicle> vehicles)
   {
      for (Vehicle v : vehicles)
      {
         if (v instanceof Truck || v instanceof Bicycle)
         {
            System.out.println(v.toString());
         }
      }
   }
   
   // Prints Phone Numbers that start with 987
   
   public static void printPhoneNumber(ArrayList<Vehicle> vehicles)
   {
      for (Vehicle v : vehicles)
      {
         if (v.getPhone().startsWith("(987)"))
         {
            System.out.println(v.toString());
         }
      }      
   }
}
      
      
/***** Vehicle Class *****/

class Vehicle 
{
   private String ownerName, address, phone, email;
   
   public Vehicle(String ownerName, String address, String phone,
                  String email)
   {
      this.ownerName = ownerName;
      this.address = address;
      this.phone = phone;
      this.email = email;
   }
   
   public String getPhone()
   {
      return phone;
   }
   
   public String getEmail()
   {
      return email;
   }   
   
   public String toString()
   {
      String str = "";
      str += this.getClass().getName() + "\n";
      str += ownerName + "\n";
      str += address + "\n";
      str += phone + "\n";
      str += email + "\n";
      
      return str;
   }   
}

/***** Car Class *****/

class Car extends Vehicle 
{
   private String color;
   private boolean convertible;
   
   public Car(String ownerName, String address, String phone,
              String email, boolean convertible, String color) 
   {
      super(ownerName, address, phone, email);
      
      this.convertible = convertible;
      this.color = color;
   }
   
    public String toString()
   {
      String str = super.toString();
      str += convertible + "\n";
      str += color + "\n";
      
      return str;
   }   

}

//***** Truck Class *****//

class Truck extends Vehicle 
{
   private float numTons, cost;
   
   public Truck(String ownerName, String address, String phone,
                String email, float numTons, float cost)
   {
      super(ownerName, address, phone, email);
      this.numTons = numTons;
      this.cost = cost;
     
   }
    
   public String toString()
   {
      String str = super.toString();
      str += numTons + "\n";
      str += cost + "\n";
      
      return str;
   }   
            
}

//***** American Car Class *****//

class AmericanCar extends Car 
{
   private boolean madeInDetroit, unionShop;
   
   public AmericanCar(String ownerName, String address, String phone,
                      String email, boolean convertible,  String color, 
                      boolean madeInDetroit, boolean unionShop)
   {
      super(ownerName, address, phone, email, convertible, color);
      this.madeInDetroit = madeInDetroit;
      this.unionShop = unionShop;
   }   
   
    public String toString()
   {
      String str = super.toString();
      str += madeInDetroit + "\n";
      str += unionShop + "\n";
      
      return str;
   }   
  
}

//***** Foreign Car Class *****//

class ForeignCar extends Car 
{
   private String country;
   private float importDuty;
   
   public ForeignCar(String ownerName, String address, String phone,
                     String email, boolean convertible,  String color,
                     String country, float importDuty)
   {
      super(ownerName, address, phone, email, convertible, color);
      this.country = country;
      this.importDuty = importDuty;
   }  
   
    public String toString()
   {
      String str = super.toString();
      str += country + "\n";
      str += importDuty + "\n";
      
      return str;
   }   
                
} 
  
//***** Bicycle Class *****//

class Bicycle extends Vehicle 
{
   private int numberOfSpeeds;
   
   public Bicycle(String ownerName, String address, String phone,
                  String email, int numberOfSpeeds)
   {
      super(ownerName, address, phone, email);
      this.numberOfSpeeds = numberOfSpeeds;
   }      
   
    public String toString()
   {
      String str = super.toString();
      str += numberOfSpeeds + "\n";
      
      return str;
   }   
         
}         