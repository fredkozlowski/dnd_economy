public class Main {
    public static void main(String[] args){
        //creating a household and populating with farmers
        Household test = new Household(1, 100, 5, 7, 2);
        Farmer a = new Farmer(10);
        Farmer b = new Farmer(11);
        test.addFarmers(a);
        test.addFarmers(b);

        for(int year = 0; year < 2; year++){
            //week loop
            double fieldCoeffSpring = 1.0;
            for(int week = 0; week <= 52; week++) {
                //ghetto display bar, didn't want to introduce dependency this early
                System.out.print("Food Stores ");
                if(test.foodStores <= 0) {
                    test.starvation();
                    System.out.print("Starvation");
                }
                else {
                    for (int j = 0; j <= test.foodStores; j++)
                        System.out.print("*");
                }
                System.out.println();

                //actions
                test.consumeFood();
                if (week == 12)
                    test.plow();
                if (week == 14)
                    fieldCoeffSpring = test.sowSpring();
                if (week == 36)
                    test.harvest(fieldCoeffSpring);
                //renewing labor pool
                for (Farmer temp : test.farmerList) {
                    temp.renewLabor();
                }
            }
        }
    }
}
