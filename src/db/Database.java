package db;

import entity.*;
import entity.Package;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import view.tm.MaintenanceRoomsTM;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Database {

    public static char currentDB = 'R';
    public static String currentUserName;

    // Package Details
    public static ArrayList<Package> lightPackages = new ArrayList<>();
    public static ArrayList<Package> midPackages = new ArrayList<>();
    public static ArrayList<Package> premiumPackages = new ArrayList<>();

    static {
        // initialize lightPackages ArrayList
        lightPackages.add(new Package("Non AC Room With Breakfast / Dinner",1500,"PL001"));
        lightPackages.add(new Package("Non AC Room With Breakfast / Lunch / Dinner",1800,"PL002"));
        lightPackages.add(new Package("AC Room With Breakfast / Dinner",1900,"PL003"));
        lightPackages.add(new Package("AC Room With Breakfast / Lunch / Dinner",2000,"PL004"));
        // initialize midPackages ArrayList
        midPackages.add(new Package("Non AC Room With Breakfast / Dinner & Pool",1900,"PM001"));
        midPackages.add(new Package("Non AC Room With Breakfast / Lunch / Dinner & Pool",2100,"PM002"));
        midPackages.add(new Package("AC Room With Breakfast / Dinner & Pool",2200,"PM003"));
        midPackages.add(new Package("AC Room With Breakfast / Lunch / Dinner & Pool",2500,"PM004"));
        // initialize premiumPackages ArrayList
        premiumPackages.add(new Package("Non AC Room With Breakfast / Dinner & Pool / Night Club",2500,"PP001"));
        premiumPackages.add(new Package("Non AC Room With Breakfast / Lunch / Dinner & Pool / Night Club",2800,"PP002"));
        premiumPackages.add(new Package("AC Room With Breakfast / Dinner & Pool / Night Club",2900,"PP003"));
        premiumPackages.add(new Package("AC Room With Breakfast / Lunch / Dinner & Pool / Night Club",3400,"PP004"));
    }

    // Rooms
    public static ArrayList<Room> rooms = new ArrayList<>();

    static {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        rooms.add(new Room("R001",false,true, (byte) 5,1500,new Customer()));
        rooms.add(new Room("R002",false,true, (byte) 5,1500,new Customer()));
        rooms.add(new Room("R003",false,true, (byte) 5,1500,new Customer()));
        rooms.add(new Room("R004",false,true, (byte) 5,1500,new Customer()));
        rooms.add(new Room("R005",false,true, (byte) 5,1500,new Customer()));
        rooms.add(new Room("R006",true,true, (byte) 5,1900,new Customer()));
        rooms.add(new Room("R007",true,true, (byte) 5,1900,new Customer()));
        rooms.add(new Room("R008",true,true, (byte) 5,1900, new Customer()));
        rooms.add(new Room("R009",true,true, (byte) 5,1900, new Customer()));
        rooms.add(new Room("R010",true,true, (byte) 5,1900,new Customer()));
        rooms.add(new Room("R011",true,true, (byte) 5,1900,new Customer()));
        rooms.add(new Room("R012",true,true, (byte) 5,1900,new Customer()));
        rooms.add(new Room("R013",true,true, (byte) 5,1900,new Customer()));
        rooms.add(new Room("R014",true,true, (byte) 5,1900,new Customer()));
        rooms.add(new Room("R015",true,true, (byte) 5,1900,new Customer()));
    }


    // Meal Plans
    public static ArrayList<MealPlan> mealPlans = new ArrayList<>();

    static {
        mealPlans.add(new MealPlan("Chicken Rice","MP001",280));
        mealPlans.add(new MealPlan("Vegetable Rice","MP002",200));
        mealPlans.add(new MealPlan("Egg Rice","MP003",240));
        mealPlans.add(new MealPlan("Mongolian Rice","MP004",320));
        mealPlans.add(new MealPlan("Biryani","MP005",350));
        mealPlans.add(new MealPlan("Chicken Kottu","MP006",290));
        mealPlans.add(new MealPlan("Egg Kottu","MP007",240));
        mealPlans.add(new MealPlan("Soup","MP008",120));
        mealPlans.add(new MealPlan("Short Eats","MP009",50));
    }

    // Customers
    public static ArrayList<Customer> customers = new ArrayList<>();


    // Users
    public static ArrayList<Receptionist> receptionists = new ArrayList<>();
    public static ArrayList<Admin> admins = new ArrayList<>();



    // Maintenance Rooms
    public static ArrayList<MaintenanceRoomsTM> maintenanceRooms = new ArrayList<>();


}
