import javax.swing.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        //Testing out town stuff
        Town town = new Town(1,5);
        town.AnnualTownFoodConsumption();
        System.out.println();
        System.out.println("Town Stats After: ");
        System.out.println();
        town.gimmeTheTownFax();

        town.householdList.get(0).graphFood();

    }
}
