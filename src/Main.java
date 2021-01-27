import javax.swing.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        //Testing out town stuff
        Town town = new Town(1,4);
        town.AnnualTownFoodConsumption();
        System.out.println();
        System.out.println("Town Stats After: ");
        System.out.println();
        town.gimmeTheTownFax();

        int x = 0;
        int y = 0;

        for(Household h : town.householdList){
            x += 150;
            y += 150;
            h.graphFood(x, y);
        }
        //to do refactor graphing to be separate class outside of household, to be used on manor as well, if possible
    }
}
