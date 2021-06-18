import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Village village1 = new Village(1,4);
        Village village2 = new Village(2,5);
        ArrayList<Village> villageList = new ArrayList<>();
        villageList.add(village1);
        villageList.add(village2);
        Region region = new Region(villageList);
        region.gimmeTheRegionFax();

        int x = 30;
        int y = 30;

        village1.graphFood(x, y);
        village2.graphFood(x + 30, y + 30);

        /* this structure is just for testing to ensure buy / sell */

        Merchant merchant = new Merchant(111);
        for(int i = 0; i <= 52; i++) {
            if(i == 30){
                merchant.trade(village1.manor, Crops.Barley);
            }
            if(i == 40){
                village2.manor.foodStores -= village2.manor.foodStores - 3; //simulating starvation to force a trade for testing
                merchant.trade(village2.manor, Crops.Barley);
            }
            merchant.updateWallet();
        }
        merchant.graph(x + 60, y + 60);

    }
}
