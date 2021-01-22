import java.util.ArrayList;
import java.util.Random;

public class Manor {
    int id;
    int foodStores;
    int population;
    ArrayList<Field> fieldList;
    ArrayList<Livestock> livestockList;
    ArrayList<Household> householdList;
    boolean plowTeam; //Scheduling must be determined. Very important to handle separately as capital

    public Manor(int id, int foodStores, boolean plowTeam){
        this.id = id;
        this.foodStores = foodStores;
        this.fieldList = new ArrayList<>();
        this.livestockList = new ArrayList<>();
        this.plowTeam = plowTeam;
    }

    //possibly refactor since shared with household
    public void consumeFood(){
        //week is basic unit of time
        if(foodStores > 0) {
            for (int i = 0; i < population; i++) {
                foodStores -= 1; //units of food need to be decided
                //also a calorie system probably needs to be implemented to cover diff b/w barley and wheat
            }
        }
    }

    public void collectTax(){
        for(int i = 0; i < householdList.size(); i++){
            double temp = householdList.get(i).foodStores * .15; //assume 15% tax rate
            householdList.get(i).foodStores -= temp;
            foodStores += temp;
        }
    }

    //manure giving to poorer farmers must be implemented

}
