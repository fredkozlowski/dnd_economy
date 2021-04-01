import java.util.ArrayList;

public class Merchant extends Person{

    ArrayList<Integer> inventory;
    int location; //id of current location
    int wallet; //What's in your wallet?

    public Merchant(int id){
        super(id);
        this.inventory = new ArrayList<>();
        this.location = 1; //where the merchant spawns
        this.wallet = 100; //need to decide what currency to use
    }

    public int trade(Manor tradingWith, Crops object){ //probably extend later past just crops
        Trading.trade(this, tradingWith, object);
        if(wallet == 0 && inventory.isEmpty()){
            return -1; //set merchant to null
        }
        return 0;
    }

    public int travel(int idToVisit) { //returns length of time it takes to get there
        return 0;
    }

    public void showMeSomeID(){ //print merchant info
        System.out.println("My Merchant License No.:"+this.id);
        System.out.println("Wallet:" + this.wallet);
        System.out.println("Knapsack:"+ this.inventory);
    }
}
