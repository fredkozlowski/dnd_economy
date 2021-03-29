import java.util.ArrayList;

public class Region { //implemented weighted graph as adjacency matrix
    ArrayList<Village> villageList;
    double[][] distances;
    public Region(ArrayList<Village> villageList){
        this.villageList = villageList; //currently must be immutable
        this.distances = new double[villageList.size()][villageList.size()];
    }

    public void populateDistances(int villageCount, ArrayList<Integer> distanceList){
        for(int i = 0; i < villageList.size(); i++){
            distances[villageCount][i] = distanceList.get(i);
        }
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
