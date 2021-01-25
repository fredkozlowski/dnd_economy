import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;
import java.lang.Math;


public class Town {
    int id; //name of town
    ArrayList<Household> householdList; //list of households

    public Town(int id, int numHouseholds){
        this.id = id;
        this.householdList = new ArrayList<>();

        int foodStore = 30; //the initial amount of food stored in each household

        //create a bunch of household, household variables right now are asserted randomly
        for(int i = 0; i < numHouseholds; i++){
            //all field values are randomly assigned (max value 10)
            int field1 = ThreadLocalRandom.current().nextInt(1, 10 + 1);
            int field2 = ThreadLocalRandom.current().nextInt(1, 10 + 1);
            int field3 = ThreadLocalRandom.current().nextInt(1, 10 + 1);
            Household aHouse = new Household(i, foodStore, field1, field2, field3);
            householdList.add(aHouse);

            //create a random number of farmers for each house (up to 6)
            int numOfFarmers = ThreadLocalRandom.current().nextInt(1, 6 + 1);
            for(int j = 0; j < numOfFarmers; j++){
                Farmer Fred = new Farmer(j); //Fred is just a variable, nobody important....not yet
                aHouse.addFarmers(Fred);
            }

            //initialize the fields by doing actions
            aHouse.plow(aHouse.fields.get(1));
            aHouse.sow(aHouse.fields.get(1), Crops.Wheat);
        }
    }

    public void gimmieTheTownFax(){ //prints out the household details in this town
        for(int i = 0; i < householdList.size(); i++) {
            System.out.println("Household: " + householdList.get(i).id);
            for (int j = 0; j < 3; j++) {
                Field oneOfTheFields = householdList.get(i).fields.get(j);

                System.out.print(" field" + (j+1) + ": ");
                System.out.print("size: "+ oneOfTheFields.size);
                System.out.print(" crop: "+ oneOfTheFields.type);
                System.out.print(" use: "+ oneOfTheFields.fieldUsage);
                System.out.println(" fert: "+ oneOfTheFields.fertility);

            }
            System.out.println(" foodstored: " + householdList.get(i).foodStores);
            System.out.print(" Farmers:");
            for (int k = 0; k < householdList.get(i).farmerList.size(); k++) {
                System.out.print(" farmer" + householdList.get(i).farmerList.get(k).id);
            }
            System.out.println();
        }
    }

    //town consumption in a year
    public void AnnualTownFoodConsumption(){ //loops through 52 weeks like in main(), but applies to every household
        for(int i = 0; i < 52; i++){

            //actions
            town_consume();
            if(i == 12)
                town_plow();
            if(i == 14)
                town_sow("spring");
            if(i == 36)
                town_harvest(0);
            if(i == 38)
                town_harvest(1);
            if(i == 40)
                town_sow("winter");

            //LOGGING
            System.out.println();System.out.println("Town Stats Before: "); System.out.println();gimmieTheTownFax();
            System.out.println(); System.out.println("Managing starvations: "); System.out.println();
            //=======

            //manage starving households
            manageStarvation();
            //take account deaths on account of starvations
            town_starvation();
            //renewing labor pool
            town_renewLaborForAllFarmers(); //UNIONIZE_THE_WORKERS jk we feudalistic, increase the tithe i want a new carpet


            //this is the code in main i just adpated the action to apply to the whole village
            //test.consumeFood();
            /*
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
             */
        }
    }

    //all the household actions are adapted to be applied to all houses in this town
    private void town_consume(){
        for(int i = 0; i < householdList.size(); i++){
            householdList.get(i).consumeFood();
        }
    }
    private void town_plow(){
        for(int i = 0; i < householdList.size(); i++){
            householdList.get(i).plow(householdList.get(i).fields.get(0)); // !!! the field being plowed changes
            householdList.get(i).plow(householdList.get(i).fields.get(1));

            /*analogous to:
                house1.plow(house1.fields.get(0));
                house1.plow(house1.fields.get(1));
             */
        }
    }
    private void town_sow(String season){
        //The season determines what field and crop will be sowed
        if(season == "spring") { // !!! create an enum for seasons
            for (int i = 0; i < householdList.size(); i++) {
                householdList.get(i).sow(householdList.get(i).fields.get(0), Crops.Barley);
                                        // !!! fields being sowed may change
                /*analogous to:
                house1.sow(house1.fields.get(0), Crops.Barley);
                */
            }
        }
        if( season == "winter"){
            for (int i = 0; i < householdList.size(); i++) {
                householdList.get(i).sow(householdList.get(i).fields.get(1), Crops.Wheat);
            }
        }
        //FILL IN THE OTHER SEASONS
    }
    private void town_harvest(int thisField){ //we should add a harvest festival feature; everyone loves a good party
        for(int i = 0; i < householdList.size(); i++){
            householdList.get(i).harvest(householdList.get(i).fields.get(thisField));

            /*analogous to:
              house1.harvest(house1.fields.get(0)); //assuming thisField == 0
            */
        }
    }
    private void town_renewLaborForAllFarmers(){
        for(int i = 0; i < householdList.size(); i++) {
            for (int k = 0; k < householdList.get(i).farmerList.size(); k++) {
                householdList.get(i).farmerList.get(k).renewLabor();
            }
        }
    }
    private void town_starvation(){
        //identify all the households at 0 food
        ArrayList<Household> thePoors = identifyStarvation();
        //check to see if anyone died
        for(int i = 0; i < thePoors.size(); i++){
            thePoors.get(i).starvation();
        }
    }

    public ArrayList<Household> identifyStarvation(){ //identifies the households that are starving
        ArrayList<Household> starving = new ArrayList<>();
        for(int i = 0; i < householdList.size(); i++){
            if(householdList.get(i).foodStores == 0){
                starving.add(householdList.get(i));
            }
        }
        return starving;
    }

    private int indexMaxFoodstore(ArrayList<Household> list){
        int max = Integer.MIN_VALUE;
        int index = 0;
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).foodStores > max){
                max = list.get(i).foodStores;
                index = i;
            }
        }
        return index;
    }

    public void manageStarvation(){
        //identify starvation
        ArrayList<Household> thePoors = identifyStarvation();

        //LOGGING
            //outputs the details of the starving households
        System.out.println("The Welfare List: ");
        for(int i = 0; i < thePoors.size(); i++) {
            System.out.println("Household: " + thePoors.get(i).id);
            System.out.println(" foodstored: " + thePoors.get(i).foodStores);
        }
        System.out.println();
        //=======


        int food_theOnePercent = 0; //the largest foodstore in town
        int numOfFarmers = 0; //number of farmers in a household; a variable we'll use later
        int mostFood = 0; //the index of the household with the most food stored
        int numTimestoRepeat = thePoors.size();

        //loop through the list of starving households
        for(int i = 0; i < numTimestoRepeat; i++){
            //find the max amount from the non-starving
            mostFood = indexMaxFoodstore(householdList);
            food_theOnePercent = householdList.get(mostFood).foodStores;


            //LOGGING
            System.out.println("Wealthy Household: " + householdList.get(mostFood).id);
            System.out.println(" foodstored: " + householdList.get(mostFood).foodStores);
            System.out.println("Poor Household: " + thePoors.get(i).id);
            System.out.println(" foodstored: " + thePoors.get(i).foodStores);
            //=======


            //check if the highest household is able to fill the deficit
            if(food_theOnePercent >= 30){
                //***using 50 as placeholder, assuming a household with less than 50 wouldn't be willing to share
                //***we can make a more sophisticated  model that looks at the the projected food use per household

                // update the foodstore values
                    //subtract the amount of food donated = amount necessary for the household to get thru the next week = the number of farmers in the household
                numOfFarmers = thePoors.get(i).farmerList.size();
                householdList.get(mostFood).foodStores -= numOfFarmers;
                thePoors.get(i).foodStores += numOfFarmers;

                //LOGGING
                System.out.print("    Wealthy Household: " + householdList.get(mostFood).id);
                System.out.println("     updated foodstored: " + householdList.get(mostFood).foodStores);
                System.out.print("    Poor Household: " + thePoors.get(i).id);
                System.out.println("     updated foodstored: " + thePoors.get(i).foodStores);
                //=======
            }
        }
    }



}
