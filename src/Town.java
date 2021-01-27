import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;


public class Town {
    int id; //name of town
    ArrayList<Household> householdList; //list of households

    public Town(int id, int numHouseholds){
        this.id = id;
        this.householdList = new ArrayList<>();

        int foodStore = 100; //the initial amount of food stored in each household

        //create a bunch of household, household variables right now are asserted randomly
        for(int i = 0; i < numHouseholds; i++){
            //all field values are randomly assigned (max value 10)
            int field1 = ThreadLocalRandom.current().nextInt(1, 10 + 1);
            int field2 = ThreadLocalRandom.current().nextInt(1, 10 + 1);
            int field3 = ThreadLocalRandom.current().nextInt(1, 10 + 1);
            Household house = new Household(i, foodStore, field1, field2, field3);
            householdList.add(house);

            //create a random number of farmers for each house (up to 6)
            int numOfFarmers = ThreadLocalRandom.current().nextInt(1, 6 + 1);
            for(int j = 0; j < numOfFarmers; j++){
                Farmer Fred = new Farmer(j); //Fred is just a variable, nobody important....not yet
                house.addFarmers(Fred);
            }

            //initialize the fields by doing actions
            house.plow(house.fields.get(1));
            house.sow(house.fields.get(1), Crops.Wheat);
        }
    }

    public void gimmeTheTownFax(){ //prints out the household details in this town
        for(int i = 0; i < householdList.size(); i++) {
            System.out.println("Household: " + householdList.get(i).id);
            for (int j = 0; j < 3; j++) {
                Field oneOfTheFields = householdList.get(i).fields.get(j);

                System.out.print(" field" + (j+1) + ": ");
                System.out.print("size: "+ oneOfTheFields.size);
                System.out.print(" crop: "+ oneOfTheFields.type);
                System.out.print(" use: "+ oneOfTheFields.fieldUsage);
                System.out.println(" fertility: "+ oneOfTheFields.fertility);

            }
            System.out.println(" food stored: " + householdList.get(i).foodStores);
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

            System.out.println();System.out.println("Town Stats Before: "); System.out.println();
            gimmeTheTownFax();
            System.out.println(); System.out.println("Managing starvations: "); System.out.println();

            //manage starving households
            manageStarvation();
            //take account deaths on account of starvation
            town_starvation();
            //renewing labor pool
            town_renewLaborForAllFarmers(); //UNIONIZE_THE_WORKERS jk we feudalistic, increase the tithe i want a new carpet
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
        ArrayList<Household> thePoor = identifyStarvation();
        //check to see if anyone died
        for(int i = 0; i < thePoor.size(); i++){
            thePoor.get(i).starvation();
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
        ArrayList<Household> thePoor = identifyStarvation();

        //LOGGING
            //outputs the details of the starving households
        System.out.println("List of Starving Households: ");
        for(int i = 0; i < thePoor.size(); i++) {
            System.out.println("Household: " + thePoor.get(i).id);
            System.out.println(" food stored: " + thePoor.get(i).foodStores);
        }
        System.out.println();

        int foodOfTheOnePercent = 0; //the largest foodstore in town
        int numOfFarmers = 0; //number of farmers in a household; a variable we'll use later
        int mostFood = 0; //the index of the household with the most food stored
        int numRepetitions = thePoor.size();

        //loop through the list of starving households
        for(int i = 0; i < numRepetitions; i++){
            //find the max amount from the non-starving
            mostFood = indexMaxFoodstore(householdList);
            foodOfTheOnePercent = householdList.get(mostFood).foodStores;

            //LOGGING
            System.out.println("Wealthy Household: " + householdList.get(mostFood).id);
            System.out.println(" food stored: " + householdList.get(mostFood).foodStores);
            System.out.println("Poor Household: " + thePoor.get(i).id);
            System.out.println(" food stored: " + thePoor.get(i).foodStores);

            //check if the highest household is able to fill the deficit
            if(foodOfTheOnePercent >= 30){
                //***using 50 as placeholder, assuming a household with less than 50 wouldn't be willing to share
                //***we can make a more sophisticated  model that looks at the the projected food use per household

                // update the food store values
                    //subtract the amount of food donated = amount necessary for the household to get through the next week = the number of farmers in the household
                numOfFarmers = thePoor.get(i).farmerList.size();
                householdList.get(mostFood).foodStores -= numOfFarmers;
                thePoor.get(i).foodStores += numOfFarmers;

                //LOGGING
                System.out.print("    Wealthy Household: " + householdList.get(mostFood).id);
                System.out.println("     updated food stored: " + householdList.get(mostFood).foodStores);
                System.out.print("    Poor Household: " + thePoor.get(i).id);
                System.out.println("     updated food stored: " + thePoor.get(i).foodStores);
            }
        }
    }



}
