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

        int foodStore = 0; //the initial amount of food stored in each household

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
        }
    }

    public void gimmieTheTownFax(){ //prints out the household details in this town
        for(int i = 0; i < householdList.size(); i++) {
            System.out.println("Household: " + householdList.get(i).id);
            for (int j = 0; j < 3; j++) {
                System.out.print(" field" + (j+1) + ": " + householdList.get(i).fieldSizes.get(j));
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
                town_harvest(1);
            //renewing labor pool
            renewLaborForAllFarmers(); //UNIONIZE_THE_WORKERS jk we feudalistic, increase the tithe i want a new carpet
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
            householdList.get(i).plow();
        }
    }
    private void town_sow(String season){
        if(season == "spring") {
            for (int i = 0; i < householdList.size(); i++) {
                householdList.get(i).sowSpring();
            }
        }
        //FILL IN THE OTHER SEASONS
    }
    private void town_harvest(int fieldcoeff){ //we should add a harvest festival feature; everyone loves a good party
        for(int i = 0; i < householdList.size(); i++){
            householdList.get(i).harvest(fieldcoeff);
        }
    }
    private void renewLaborForAllFarmers(){
        for(int i = 0; i < householdList.size(); i++) {
            for (int k = 0; k < householdList.get(i).farmerList.size(); k++) {
                householdList.get(i).farmerList.get(k).renewLabor();
            }
        }
    }

    public ArrayList<Household> identifyStarvation(){ //identifies the households that are starving
        ArrayList<Household> starving = new ArrayList<>();
        for(int i = 0; i < householdList.size(); i++){
            if(householdList.get(i).foodStores < 0){
                starving.add(householdList.get(i));
            }
        }

        //outputs the details of the starving households
        System.out.println("The Welfare List: ");
        for(int i = 0; i < starving.size(); i++) {
            System.out.println("Household: " + starving.get(i).id);
            System.out.println(" foodstored: " + starving.get(i).foodStores);
        }
        System.out.println();
        /*
        System.out.println("Household: " + starving.get(indexMaxFoodstore(starving)).id);
        System.out.println(" foodstored: " + starving.get(indexMaxFoodstore(starving)).foodStores);
        */
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
        int food_theOnePercent = 0; //the largest foodstore in town
        int food_oneOfThePoors = 0; //the foodstore of one of the starving
        int mostFood = 0; //the index of the household with the most food stored
        int leastStarving = 0; //the index of the household with the smallest magnitude below 0
        int numTimestoRepeat = thePoors.size();

        //loop through the list of starving households
        for(int i = 0; i < numTimestoRepeat; i++){
            //find the max amount from the non-starving
            mostFood = indexMaxFoodstore(householdList);
            food_theOnePercent = householdList.get(mostFood).foodStores;
            System.out.println("Wealthy Household: " + householdList.get(mostFood).id);
            System.out.println(" foodstored: " + householdList.get(mostFood).foodStores);
            //find the max amount from starvation (the largest negative number, smallest defecit to close)
            leastStarving = indexMaxFoodstore(thePoors);
            food_oneOfThePoors = thePoors.get(leastStarving).foodStores;
            System.out.println("Poor Household: " + thePoors.get(leastStarving).id);
            System.out.println(" foodstored: " + thePoors.get(leastStarving).foodStores);
            //check if the highest household is able to fill the deficit
            if(food_theOnePercent >= Math.abs(food_oneOfThePoors)){ //Math.abs() comes from the imported lib
                // update the foodstore values
                householdList.get(mostFood).foodStores += food_oneOfThePoors;
                thePoors.get(leastStarving).foodStores = 0;
                thePoors.remove(leastStarving);
            }
        }
    }



}
