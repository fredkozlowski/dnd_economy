import Graphing.DisplayMerchantGraph;

import javax.swing.*;
import java.util.ArrayList;

public class Merchant extends Person{

    ArrayList<Integer> inventory;
    int location; //id of current location
    int wallet; //What's in your wallet?
    ArrayList<Integer> walletDataset;
    double prevPrice;

    public Merchant(int id){
        super(id);
        this.inventory = new ArrayList<>();
        this.location = 1; //where the merchant spawns
        this.wallet = 100; //need to decide what currency to use
        this.walletDataset = new ArrayList<>();
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

    public void updateWallet(){
        walletDataset.add(wallet);
    }

    public void graph(int x, int y){
        //generates a bar graph object, and the following functions are display parameters
        DisplayMerchantGraph graph = new DisplayMerchantGraph("plot of merchant wallet", walletDataset);
        graph.setSize( 600, 400);
        graph.setLocation(x, y);
        graph.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        graph.setVisible(true);
    }
}
