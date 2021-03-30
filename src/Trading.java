import java.lang.Math;

public class Trading {
    // This class will handle all trading between agents
    public static int trade(Merchant p1, Manor p2, Crops object){
        if(p1.inventory.isEmpty()){
            return buy(p1, p2, object);
        }
        else {
            return sell(p1, p2, object);
        }
    }

    static int buy(Merchant p1, Manor p2, Crops object){ //returns amount sold or price ?
        if(p2.foodStores < 1000) { //placeholder
            return 0;
        }
        double ageOfGrain = Math.exp(p2.lastHarvest);
        double surplus = p2.foodStores - 1000;
        double willingnessToSell = ageOfGrain * surplus; //probably should be normalized
        if(willingnessToSell > 1000){ //some arbitrary number
            p2.foodStores -= 1000;
            p1.inventory.add(1000);
        }
        return 0;
    }
    static int sell(Merchant p1, Manor p2, Crops object){
        return 0;
    }
}
