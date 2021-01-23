import javax.swing.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //creating a household and populating with farmers
        Household test = new Household(1, 10, 5, 7, 2);
        Farmer a = new Farmer(10);
        Farmer b = new Farmer(11);
        test.addFarmers(a);
        test.addFarmers(b);
        /*
        //Testing out town stuff
        Town theTown = new Town(1,15);
        theTown.AnnualTownFoodConsumption();
        System.out.println();
        System.out.println("Town Stats Before: ");
        System.out.println();
        theTown.gimmieTheTownFax();
        System.out.println();
        System.out.println("Managing starvations: ");
        System.out.println();
        theTown.manageStarvation();
        System.out.println();
        System.out.println("Town Stats After: ");
        System.out.println();
        theTown.gimmieTheTownFax();
         */

        //loop for 1 year (52 weeks)
        //going to label the week, so initialize the string here
        String weekName = "";
        //vector to store the foodstored in the test household and passed to the plotting class
        Vector<Integer> foodStoreList = new Vector<Integer>(53);
        //System.out.println("Food Stores");

        //quick fix to enable start with winter wheat already sown
        test.plow(test.fields.get(1));
        test.sow(test.fields.get(1), Crops.Wheat);

        for(int i = 0; i <= 52; i++){
            //ghetto display table, cuz f***
            weekName = " week" + i + ' ';
            System.out.print(weekName);
            System.out.print(test.foodStores);
            foodStoreList.add(test.foodStores);
            System.out.println();
            /*
            try {
                TimeUnit.SECONDS.sleep(1); //so that you can see display in real time
            }
            catch(InterruptedException ex){}
             */

            //actions
            test.consumeFood();
            if(i == 12) {
                test.plow(test.fields.get(0));
                test.plow(test.fields.get(1));
            }
            if(i == 14)
                test.sow(test.fields.get(0), Crops.Barley); //spring barley
            if(i == 36)
                test.harvest(test.fields.get(0));
            if(i == 38)
                test.harvest(test.fields.get(1));
            if(i == 40)
                test.sow(test.fields.get(1), Crops.Wheat); //winter wheat
            //renewing labor pool
            for(Farmer temp : test.farmerList) {
                temp.renewLabor();
            }
            if(test.foodStores < 0)
                test.starvation();
        }
        /*
        //generates a bar graph object, and the following functions are display parameters
        DisplayFoodStores_Bar example = new DisplayFoodStores_Bar("plot of food stored", foodStoreList);
        example.setSize( 1200, 400);
        example.setLocationRelativeTo(null);
        example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        example.setVisible(true);
         */
    }
}
