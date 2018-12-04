/* ronald shevchenko
   n01385011
   assignment 4 
   each method called for printing related to the vehicles is separated by dashes 
   "---------------------------------------" */
   
import java.io.*; 
import java.util.*;
   
public class n01385011 { 
   public static void main(String []args) throws FileNotFoundException {
   
      ArrayList<Vehicle> allVehicles = new ArrayList<Vehicle>(); 
   
      Scanner x = new Scanner(new File(args[0]));
      
      String info;
      
      do{ //gets the info from file into the arrays
         info = x.nextLine();
         if (info.equals("vehicle") || info.equals("car") || info.equals("american car") ||
                        info.equals("foreign car") || info.equals("bicycle") || info.equals("truck")){
                        
               String ownersName = x.nextLine();
               String address = x.nextLine();
               String phone = x.nextLine();
               String email = x.nextLine();
                              
            if(info.equals("vehicle")){
               allVehicles.add(new Vehicle(ownersName, address, phone, email));
            }
            else if(info.equals("car")){
               boolean isConvertible = x.nextBoolean();
               x.nextLine();
               String color = x.nextLine();
               allVehicles.add(new Car(ownersName, address, phone, email, isConvertible, color));
            }
            else if(info.equals("american car")){
               boolean isConvertible = x.nextBoolean();
               x.nextLine();
               String color = x.nextLine();
               boolean isMadeInDetroit = x.nextBoolean();
               x.nextLine();
               boolean isUnionShop = x.nextBoolean();
               if(x.hasNext())
                  x.nextLine();
               allVehicles.add(new AmericanCar(ownersName, address, phone, email, 
                                            isConvertible, color, isMadeInDetroit, isUnionShop));
            }
            else if(info.equals("foreign car")){
               boolean isConvertible = x.nextBoolean();
               x.nextLine();
               String color = x.nextLine(); 
               String countryOfManufacturer = x.nextLine();
               float importDuty = x.nextFloat();
               if(x.hasNext())
                  x.nextLine();
               allVehicles.add(new ForeignCar(ownersName, address, phone, email, 
                                           isConvertible, color, countryOfManufacturer, importDuty));
            }
            else if(info.equals("bicycle")){ 
               int numOfSpeeds = x.nextInt();
               if(x.hasNext())
                  x.nextLine();
               allVehicles.add(new Bicycle(ownersName, address, phone, email, 
                                        numOfSpeeds));
            }
            else if(info.equals("truck")){ 
               float numOfTons = x.nextFloat();
               x.nextLine();
               float costOfTruck = x.nextFloat();
               x.nextLine();
               String datePurchased = x.nextLine();
               allVehicles.add(new Truck(ownersName, address, phone, email, 
                                      numOfTons, costOfTruck, datePurchased));
            }
         }
      } while(x.hasNext());
      
      //methods of printing, sorting, printing number of vehicles, bikes and trucks only, and area code 987
      PrintAll(allVehicles);
      
      allVehicles = SortAll(allVehicles);
      PrintAll(allVehicles);
      
      PrintNum(allVehicles);
      
      BikeTruck(allVehicles);
      
      AreaCode(allVehicles);
      
      x.close();
   }

   public static void PrintAll(ArrayList<Vehicle> allVehicles){ //prints all vehicles 
      for (Vehicle vehicle : allVehicles)
         System.out.println(vehicle.toString());
      System.out.println("---------------------------------------\n");
   }
   
   public static ArrayList<Vehicle> SortAll(ArrayList<Vehicle> allVehicles){ //sorts the vehicles by email address using unicode
      Collections.sort(allVehicles, new Comparator<Vehicle>(){
         public int compare(Vehicle v1, Vehicle v2){
            return v1.getEmail().compareTo(v2.getEmail());
         }
      });
      return allVehicles;
   }
   
   public static void PrintNum(ArrayList<Vehicle> allVehicles){ //prints the number of vehicles
      int count = 0;
      for (Vehicle vehicle : allVehicles)
         count += 1;
      System.out.println(count + "\n");
      System.out.println("---------------------------------------\n");
   }
   
   public static void BikeTruck(ArrayList<Vehicle> allVehicles){ //only prints bikes and trucks
      for (Vehicle vehicle : allVehicles)
         if((vehicle.getClass().getName() == "Bicycle") || (vehicle.getClass().getName() == "Truck"))
            System.out.println(vehicle.toString());
      System.out.println("---------------------------------------\n");
   }
   
   public static void AreaCode(ArrayList<Vehicle> allVehicles){ //prints vehicles with the area code 987 in their phone number
      for (Vehicle vehicle : allVehicles)
         if(vehicle.getPhone().startsWith("(987)"))
            System.out.println(vehicle.toString());
      System.out.println("---------------------------------------\n");
   }
}

class Vehicle { //vehicle class, the parent to all classes

   private String ownersName, address, phone, email;
   
   Vehicle(String ownersName, String address, String phone){ 
      this.ownersName = ownersName;
      this.address = address;
      this.phone = phone;
      this.email = email;
   }
   
   public String toString(){
      return this.getClass().getName() + "\n"
              + ownersName + "\n"
              + address + "\n"
              + phone + "\n" 
              + email + "\n";
   } 
   
   public String getEmail(){
      return email;
   }
   public String getPhone(){
      return phone;
   }
}

class Car extends Vehicle { //car class

   private boolean isConvertible;
   private String color;
  
   public Car(String ownersName, String address, String phone, String email, 
              boolean isConvertible, String color) {
      super(ownersName, address, phone, email);
      this.isConvertible = isConvertible;
      this.color = color; 
   }
   
   public String toString(){
      return super.toString()
             + isConvertible + "\n"
             + color + "\n";
   }
}

class AmericanCar extends Car { //american car class

   private boolean isMadeInDetroit, isUnionShop;

   public AmericanCar(String ownersName, String address, String phone, String email, 
                      boolean isConvertible, String color, 
                      boolean isMadeInDetroit, boolean isUnionShop){
     super(ownersName, address, phone, email, isConvertible, color);
     this.isMadeInDetroit = isMadeInDetroit;
     this.isUnionShop = isUnionShop;    
   }
   
   public String toString(){
      return super.toString() + isMadeInDetroit + "\n"
             + isUnionShop + "\n";
   }                      
}

class ForeignCar extends Car { //foreign car class

   private String countryOfManufacturer;
   private float importDuty;
   
   public ForeignCar(String ownersName, String address, String phone, String email, 
                      boolean isConvertible, String color, 
                      String countryOfManufacturer, float importDuty){
     super(ownersName, address, phone, email, isConvertible, color);
     this.countryOfManufacturer = countryOfManufacturer;
     this.importDuty = importDuty;
   }
   
   public String toString(){
      return super.toString() + countryOfManufacturer + "\n"
             + importDuty + "\n";
   }
}

class Bicycle extends Vehicle { //bicycle class

   private int numOfSpeeds;
   
   public Bicycle(String ownersName, String address, String phone, String email,
                  int numOfSpeeds){
      super(ownersName, address, phone, email);
      this.numOfSpeeds = numOfSpeeds;   
   }
   
   public String toString(){
      return super.toString() + numOfSpeeds + "\n";
   }
}

class Truck extends Vehicle { //truck class

   private float numOfTons, costOfTruck;
   private String datePurchased;
   
   public Truck(String ownersName, String address, String phone, String email,
               float numOfTons, float costOfTruck, String datePurchased){
      super(ownersName, address, phone, email);
      this.numOfTons = numOfTons;
      this.costOfTruck = costOfTruck;
      this.datePurchased = datePurchased;
   }
   
   public String toString(){
      return super.toString() + numOfTons + "\n"
             + costOfTruck + "\n"
             + datePurchased + "\n";
   }
}
