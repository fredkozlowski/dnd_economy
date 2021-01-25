import java.util.ArrayList;
import java.util.Random;

public class Household {
    int id;
    int foodStores;
    ArrayList<Farmer> farmerList; //refactor to be person list, with different types inside
    ArrayList<Field> fields; //unit tbd
    /*int cropRotation; //ranges from 0 to 2 and is index of fallow field in fieldSizes array
                      //the next will be spring sowing and after winter sowing, will loop over
     */


    public Household(int id, int foodStores, int field1Size, int field2Size, int field3Size){
        this.id = id;
        this.foodStores = foodStores;
        this.farmerList = new ArrayList<>();
        this.fields = new ArrayList<>();
        Field field1 = new Field();
        field1.size = field1Size;
        Field field2 = new Field();
        field2.size = field2Size;
        Field field3 = new Field();
        field3.size = field3Size;
        fields.add(field1);
        fields.add(field2);
        fields.add(field3);
    }

    public void consumeFood(){
        //week is basic unit of time
        if(foodStores > 0) {
            for (int i = 0; i < farmerList.size(); i++) {
                foodStores -= 1; //units of food need to be decided
                //also a calorie system probably needs to be implemented to cover diff b/w barley and wheat
            }
            //there's a possibility that reduced foodstore becomes negative: MUST EXTERMINATE POSSIBILITY
            if(foodStores < 0){
                foodStores = 0;
            }
        }
    }
    //using a 3 crop rotation system

    public void sow(Field field, Crops crop){
        //should the total amount of crop be expressed in calories or weight?
        //regional variation in crop sown?
        field.type = crop;
        int laborPool = 0;
        double laborReq = (int) Math.round(0.3 * field.size); //placeholder value
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
        //this is the field usage calculation
        if(laborPool >= laborReq){
            field.fieldUsage = 1.0;
        }
        else{
            field.fieldUsage = laborReq / (double) laborPool;
        }
    }

    public void harvest(Field field){
        //only implemented for one field, possibly split into 2 harvests?
        Random random = new Random();
        double weather = random.nextGaussian() * 0.2 + 1; //replace with more realistic distribution later
        int harvestedCrops = 0;
        harvestedCrops += (int) Math.round(field.size * 10 * weather * field.fieldUsage * field.fertility);
        harvestedCrops = (int) Math.round(0.9 * harvestedCrops); //Church tithes
        foodStores += harvestedCrops;
    }

    public void plow(Field field){
        //dependent on large landowner capital
        //possibly put manuring under this function

        Random random = new Random();
        double fertility = random.nextGaussian() * 0.2 + 1; //replace with more realistic distribution later
        field.fertility = fertility;
    }

    public void addFarmers(Farmer f){
        farmerList.add(f);
    }

    public void starvation(){
        //to model starvation, give a 1% chance of dying per week to everyone
        //later change to give children and elderly higher death chance

        //We will store the dead farmers in this array, then kill them after iterating the list
        ArrayList<Farmer> DeathNote = new ArrayList<>();
        for(Farmer f : farmerList){
            double rand = Math.random();
            System.out.println(rand);
                    /* ERROR
                    This leads to the error: ConcurrentModificationException
                    Java dont like to remove things from a list while iterating so i'm building a work around
                    https://www.baeldung.com/java-concurrentmodificationexception
                    if(rand < 0.01){
                        System.out.println("Farmer " + f.id + " has died");
                        farmerList.remove(f);
                    }
                    */
            if(rand < 0.01){ //i made the chance of death higher for testing
                System.out.println("Farmer " + f.id + " of Household " + id + " has died.");
                DeathNote.add(f);
            }
        }
        for(Farmer Fred : DeathNote){
            farmerList.remove(Fred); //GEETEM OUTTA HERE!
        }
    }
}
