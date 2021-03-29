import java.util.ArrayList;

public class Merchant extends Person{

    ArrayList<Crops> inventory;
    int location; //id of current location
    int wallet; //What's in your wallet?

    public Merchant(int id){
        super(id);
        this.inventory = new ArrayList<>();
        this.location = 1; //where the merchant spawns
        this.wallet = 100; //need to decide what currency to use
    }

    public void trade(Person tradingWith, Crops object){ //probably extend later past just crops
        return;
    }

    public int travel(int idToVisit) { //returns length of time it takes to get there
        return 0;
    }
}
