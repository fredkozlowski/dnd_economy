import java.util.ArrayList;
import java.util.Random;

public class Household {
    int id;
    int foodStores;
    ArrayList<Farmer> farmerList; //refactor to be person list, with different types inside
    ArrayList<Integer> fieldSizes; //unit tbd
    int cropRotation; //ranges from 0 to 2 and is index of fallow field in fieldSizes array
                      //the next will be spring sowing and after winter sowing, will loop over

    public Household(int id, int foodStores, int field1, int field2, int field3){
        this.id = id;
        this.foodStores = foodStores;
        this.farmerList = new ArrayList<>();
        this.fieldSizes = new ArrayList<>();
        fieldSizes.add(field1);
        fieldSizes.add(field2);
        fieldSizes.add(field3);
    }

    public void consumeFood(){
        //week is basic unit of time
        if(foodStores > 0) {
            for (int i = 0; i < farmerList.size(); i++) {
                foodStores -= 1; //units of food need to be decided
                //also a calorie system probably needs to be implemented to cover diff b/w barley and wheat
            }
        }
    }
    //using a 3 crop rotation system

    public void sowWinter(){
        for(int i = 0; i < farmerList.size(); i++){
            //tbd
        }
    }

    public double sowSpring(){
        //sowing barley/oats
        //should the total amount of crop be expressed in calories or weight?
        //regional variation in crop sown?
        int laborPool = 0;
        double laborReq = (int) Math.round(0.3 * fieldSizes.get(0)); //placeholder value
        for(Farmer temp : farmerList){
            if(temp.labor >= 5) { //5 is labor cost
                laborPool += 5;
                temp.labor -= 5;
            }
            else{
                laborPool += temp.labor;
                temp.labor -= temp.labor;
            }
        }
        //need to determine labor per field
        //calculate proportion of work that can be completed
        if(laborPool >= laborReq){
            return 1.0;
        }
        else{
            return laborReq / (double) laborPool;
        }
    }

    public void harvest(double fieldCoeff){ //only implemented for one field, possibly split into 2 harvests?
        Random random = new Random();
        double weather = random.nextGaussian() * 0.2 + 1; //replace with more realistic distribution later
        int harvestedCrops = 0;
        for(int i = 0; i < fieldSizes.size(); i++){
            if(i == cropRotation)
                continue;
            harvestedCrops += (int) Math.round(fieldSizes.get(i) * 10 * weather * fieldCoeff);
        }
        harvestedCrops = (int) Math.round(0.9 * harvestedCrops); //Church tithes
        foodStores += harvestedCrops;
    }

    public void plow(){
        //dependent on large landowner capital
    }

    public void addFarmers(Farmer f){
        farmerList.add(f);
    }

    public void starvation(){
        //to model starvation, give a 1% chance of dying per week to everyone
        //later change to give children and elderly higher death chance
        for(Farmer f : farmerList){
            double rand = Math.random();
            System.out.println(rand);
            if(rand < 0.01){
                farmerList.remove(f);
                System.out.println("Farmer " + f.id + " has died");
            }
        }
    }

}
