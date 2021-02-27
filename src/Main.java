import javax.swing.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Region region = new Region();
        Town town1 = new Town(1,4);
        region.townList.add(town1);
        Town town2 = new Town(2,5);
        region.townList.add(town2);
        region.gimmeTheRegionFax();

        int x = 0;
        int y = 0;
        for(Household h : town1.householdList){
            x += 70;
            y += 70;
            h.graphFood(x, y);
        }
        for(Household h : town2.householdList){
            x += 70;
            y += 70;
            h.graphFood(x, y);
        }
        //to do refactor graphing to be separate class outside of household, to be used on manor as well, if possible
    }
}
