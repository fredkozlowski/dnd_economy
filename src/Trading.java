import java.lang.Math;

public class Trading {
    // This class will handle all trading between agents
    public static int trade(Merchant p1, Manor p2, Crops object){
        //if the merchant is empty they will buy as much as possible, regardless of price
        if(p1.inventory.isEmpty()){
            return buy(p1, p2, object);
        }
        else {
            return sell(p1, p2, object);
        }
    }

    static int buy(Merchant p1, Manor p2, Crops object){ //returns amount sold or price ?
        /*
        if(p2.foodStores < 1000) { //placeholder; will need to create an linear price function depending on amount of food stored
            return 0;
        }
        double ageOfGrain = Math.exp(p2.lastHarvest);
        double surplus = p2.foodStores - 1000; //preserving the 1000 cutoff
        double willingnessToSell = ageOfGrain * surplus; //probably should be normalized; add exponential
        if(willingnessToSell > 1000){ //some arbitrary number
            p2.foodStores -= 1000;
            p1.inventory.add(1000);
        }
        return 0;
         */
        p1.showMeSomeID(); //check the contents of this merchant before the deal
        if(p2.foodStores < 1000) { //placeholder; will need to create an linear price function depending on amount of food stored
            return 0;
        }
        double daysTillExpiration = 300 - p2.lastHarvest; //we are interested in how many days are left; i picked 300 because im not sure how long the grains will last 300 is big enough to not get a negative number
        double cost_ageOfGrain = 2*Math.log(daysTillExpiration); //more days (a fresh harvest) makes it more valuable
        double surplus = p2.foodStores - 1000; //preserving the 1000 cutoff
        double normalized_surplus = surplus/100; //normalize as in if 1000 is 10, then a surplus of 200 is 2; it just makes more sense when using my cost function
        double cost_surplus = 10/(normalized_surplus*normalized_surplus+1); //I used the function 1/(x^2+1); actually contact me about the math idk what im doing
        double price = cost_ageOfGrain * cost_surplus; //probably should be normalized
        System.out.println("daysTillExpiration: " + daysTillExpiration);
        System.out.println("surplus: " + surplus);
        System.out.println("Cost: " + price);

        //find out how much the merchant can buy at this price
        int merchantCanAfford = (int)(p1.wallet/price); //caste to int
        System.out.println("merchantCanAfford: " + merchantCanAfford);
        //add inventory to merchant & reduce supply of the town
        if(merchantCanAfford>surplus){
            p2.foodStores -= surplus;
            p1.inventory.add((int)surplus);
            p1.wallet-=merchantCanAfford*price;
        }
        else{
            p2.foodStores -= merchantCanAfford;
            p1.inventory.add((int)merchantCanAfford);
            p1.wallet-=merchantCanAfford*price; //round up or down on wallet? make double?
        }
        p1.showMeSomeID();
        return 0;
    }
    static int sell(Merchant p1, Manor p2, Crops object){
        return 0;
    }
}
