import java.lang.Math;

public class Trading {
    // This class will handle all trading between agents
    // it will need to be abstracted to more than just merchant / manor later
    public static int trade(Merchant p1, Manor p2, Crops object){
        //if the merchant is empty they will buy as much as possible, regardless of price
        if(p1.inventory.isEmpty()){
            return buy(p1, p2, object);
        }
        else {
            //otherwise always try to sell
            return sell(p1, p2, object);
        }
    }
    static double findPrice(int lastHarvest, double surplus){
        double daysTillExpiration = 300 - lastHarvest; //we are interested in how many days are left; i picked 300 because im not sure how long the grains will last 300 is big enough to not get a negative number
        double cost_ageOfGrain = 2*Math.log(daysTillExpiration); //more days (a fresh harvest) makes it more valuable
        double normalized_surplus = surplus/100; //normalize as in if 1000 is 10, then a surplus of 200 is 2; it just makes more sense when using my cost function
        double cost_surplus = 10/(normalized_surplus*normalized_surplus+1); //I used the function 1/(x^2+1); actually contact me about the math idk what im doing
        double price = cost_ageOfGrain * cost_surplus; //probably should be normalized
        System.out.println("Cost: " + price);
        System.out.println("daysTillExpiration: " + daysTillExpiration);
        return price;
    }

    static int buy(Merchant p1, Manor p2, Crops object){ //returns amount sold or price ?
        p1.showMeSomeID(); //check the contents of this merchant before the deal
        if(p2.foodStores < 1000) { //placeholder; will need to create an linear price function depending on amount of food stored
            return 0;
        }
        double surplus = p2.foodStores - 1000; //preserving the 1000 cutoff
        System.out.println("surplus: " + surplus);
        double price = findPrice(p2.lastHarvest, surplus);
        //find out how much the merchant can buy at this price
        int merchantCanAfford = (int)(p1.wallet/price); //cast to int
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
        p1.prevPrice = price;
        return 0;
    }
    static int sell(Merchant p1, Manor p2, Crops object){
        System.out.println(p2.foodStores);
        p1.showMeSomeID(); //check the contents of this merchant before the deal
        if(p2.foodStores < 1000) { //placeholder; will need to create an linear price function depending on amount of food stored
            System.out.println("surplus: " + p2.foodStores);
            System.out.println(p2.lastHarvest);
            System.out.println(p2.foodStores);
            double price = findPrice(p2.lastHarvest, p2.foodStores); // this is wrong, just a placeholder - should be based on p1 grain age
            if (price > p1.prevPrice) {
                System.out.println("Sold");
                p2.foodStores += p1.inventory.get(0);
                p1.wallet+=p1.inventory.get(0)*price;
                p1.inventory.remove(0);
            }
        }
        return 0;
    }
}
