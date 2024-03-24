import java.util.*;
import java.io.*;
public class main {
   public static void main(String[] args) throws FileNotFoundException {
      File file = new File("all_alpha_24(1).csv");
      CarModelTree Cars = new CarModelTree(new Scanner(file));
      Scanner userInput = new Scanner(System.in);
      System.out.println("Welcome to Car Model Comparer!");
      System.out.println("This program finds car models and shows what the fuel costs would look like.");
      System.out.println();
      while(true) {
         Map<String, Double> map = Cars.getFilteredList(getUserPref(userInput).toLowerCase());
         if(map.size() != 0) {
            int i = 1;
            for(String modelName : map.keySet()) {
               System.out.println("[" + i + "]" + " :" + modelName);
               i++;
            }
            System.out.print("Select from the following above (Number corresponds with choice): ");
            int choice = Integer.parseInt(userInput.nextLine());
            i = 1;
            double mpg = 0.0;
            for(String modelName : map.keySet()) {
               if(i == choice) {
                  mpg = map.get(modelName);
                  break;
               }
               i++;
            }
            calculateCost(userInput, mpg);
            System.out.println();
            System.out.println("Search again(y) or quit(n)? ");
            if(!userInput.nextLine().toLowerCase().contains("y")) break;
         } else {
            System.out.println("No cars match critera. Search again(y) or quit(n)?");
            if(!userInput.nextLine().toLowerCase().contains("y")) break;
         }


         
      }            
   }
   
   public static String getUserPref(Scanner userInput) {
         String userData = "";
         System.out.print("Enter desired Brand Type (enter for no preference): ");
         userData +=  userInput.nextLine() + " ";
         System.out.print("Enter desired Drivetrain Type (2WD or 4WD) (enter for no preference): ");
         userData += userInput.nextLine() + " ";
         System.out.print("Enter desired Category (car, wagon, suv, pickup) with (small, standard, large, midsize)  (enter for no preference): ");
         userData += userInput.nextLine() + " ";
         System.out.print("Enter desired Fuel Type(Electric/Gas/Hybrid (enter for no preference): ");
         userData += userInput.nextLine() + " ";
         System.out.println();
         System.out.println();
         return userData;
   }
   
   public static void calculateCost(Scanner userInput, double mpg) {
       System.out.print("How many miles do you drive in a year? ");
       double miles = Double.parseDouble(userInput.nextLine());
       System.out.print("How much do you pay for gas? (dollars per gallon) ");
       double price = Double.parseDouble(userInput.nextLine());
       double amount = (miles / mpg) * price;
       System.out.println();
       System.out.println("You would spend about $" + amount + " if you bought this car.");
   }
}