import java.util.ArrayList;

public class Household {
    int id;
    int foodStores;
    ArrayList<Farmer> farmerList; //refactor to be person list, with different types inside

    public Household(int id, int foodStores){
        this.id = id;
        this.foodStores = foodStores;
        this.farmerList = new ArrayList<>();
    }

    public void consumeFood(){
        //decision on basic unit of time is needed
        //possibly based on week?

        for(int i = 0; i < farmerList.size(); i++){
            foodStores -= 10; //units of food need to be decided
        }
    }
    public void sow(){
        for(int i = 0; i < farmerList.size(); i++){
            //tbd
        }
    }

    public void harvest(){
        for(int i = 0; i < farmerList.size(); i++){
            //tbd
        }
    }


}
