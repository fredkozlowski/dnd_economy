import java.util.ArrayList;

public class Region {
    ArrayList<Town> townList;
    public Region(){
        this.townList = new ArrayList<>();
    }

    public void gimmeTheRegionFax(){
        for(Town town : townList) {
            town.AnnualTownFoodConsumption();
            System.out.println();
            System.out.println("Town " + town.id + " Stats After: ");
            System.out.println();
            town.gimmeTheTownFax();
        }
    }
}
