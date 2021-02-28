import java.util.ArrayList;

public class Region {
    ArrayList<Village> villageList;
    public Region(){
        this.villageList = new ArrayList<>();
    }

    public void gimmeTheRegionFax(){
        for(Village village : villageList) {
            village.AnnualTownFoodConsumption();
            System.out.println();
            System.out.println("Town " + village.id + " Stats After: ");
            System.out.println();
            village.gimmeTheTownFax();
        }
    }
}
