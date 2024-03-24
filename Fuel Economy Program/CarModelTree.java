import java.util.*;
import java.io.*;
public class CarModelTree {
   private HashSet<CarModelNode> models;
   private int year; // What year the car models are from
       
   public CarModelTree(Scanner file) {
      this.models = new HashSet<CarModelNode>();
      String temp = file.nextLine();
      while(file.hasNextLine()) {
         models.add(new CarModelNode(file.nextLine().split(",")));
      }
   }
   
   public Map<String, Double> getFilteredList(String filterData) {
      Map<String, Double> map = new HashMap<>();
      for(CarModelNode model : models) {
         if(meetsRequirements(filterData, model)) {
            map.put(model.modelName, model.MPG);
         }
      }
      return map;
   }
   
   private boolean meetsRequirements(String data, CarModelNode model) {
      String[] splitedData = data.split(" ");
      String modelData = model.modelName + model.driveTrain + model.category + model.fuelType;
      for(String piece : splitedData) {
         if(!modelData.contains(piece)) return false;
      }
      return true;
   }
   
   public void printAllModels() {
      for(CarModelNode model : models) {System.out.println(model.modelName + " " + model.fuelType + " " + model.driveTrain + " " + model.category);}
   }
   
    private static class CarModelNode {
      public String modelName;
      public double MPG;
      public String fuelType; // Gas, EV, Hybrid
      public String driveTrain; // 2WD,4WD, RWD, AWD
      public String category; // SUV, compact, etc
      
      public CarModelNode(String[] data) {
         modelName = data[0].toLowerCase();
         driveTrain = data[4].toLowerCase();
         category = data[13].toLowerCase();
         fuelType = data[5].contains("/") ? "hybrid(phev)" : data[5].toLowerCase();
         if(data[12].contains("/")) {
            String[] mpgs = data[14].split("/");
            MPG = (Integer.parseInt(mpgs[0]) + Integer.parseInt(mpgs[1])) / 2.0;
         } else {
            MPG = Double.parseDouble(data[14]);
         }


      }
          
   }  
   
}