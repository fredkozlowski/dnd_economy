import javax.swing.*;
import java.util.concurrent.TimeUnit;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //creating a household and populating with farmers
        Household test = new Household(1, 100, 5, 7, 2);
        Farmer a = new Farmer(10);
        Farmer b = new Farmer(11);
        test.addFarmers(a);
        test.addFarmers(b);

        //loop for 1 year (52 weeks)
        System.out.print("Food Stores");
        System.out.println();
        //going to label the week, so initialize the string here
        String weekName = "";
        Vector<Integer> foodStoreList = new Vector<Integer>(53);
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
            if(i == 12)
                test.plow();
            if(i == 14)
                test.sowSpring();
            if(i == 36)
                test.harvest(2);
            //renewing labor pool
            for(Farmer temp : test.farmerList) {
                temp.renewLabor();
            }
        }
        //generates a bar graph object, and the following functions are display parameters
        DisplayFoodStores_Bar example = new DisplayFoodStores_Bar("plot of food stored", foodStoreList);
        example.setSize( 1200, 400);
        example.setLocationRelativeTo(null);
        example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        example.setVisible(true);
    }
}