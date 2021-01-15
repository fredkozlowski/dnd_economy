import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args){
        //creating a household and populating with farmers
        Household test = new Household(1, 100, 5, 7, 2);
        Farmer a = new Farmer(10);
        Farmer b = new Farmer(11);
        test.addFarmers(a);
        test.addFarmers(b);


        //time loop
        for(int i = 0; i <= 52; i++){
            //ghetto display bar, didn't want to introduce dependency this early
            System.out.print("Food Stores");
            for(int j = 0; j <= test.foodStores; j++)
                System.out.print("*");
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
                test.plough();
            if(i == 14)
                test.sowSpring();
            if(i == 36)
                test.harvest();
            //renewing labor pool
            for(Farmer temp : test.farmerList){
                temp.renewLabor();
            }


        }
    }
}
