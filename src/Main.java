public class Main {
    public static void main(String[] args) {

        Region region = new Region();
        Village village1 = new Village(1,4);
        region.villageList.add(village1);
        Village village2 = new Village(2,5);
        region.villageList.add(village2);
        region.gimmeTheRegionFax();

        int x = 30;
        int y = 30;
        /*for(Household h : village1.householdList){
            x += 70;
            y += 70;
            h.graphFood(x, y);
        }
        for(Household h : village2.householdList){
            x += 70;
            y += 70;
            h.graphFood(x, y);
        }*/
        village1.graphFood(x, y);
        village2.graphFood(x + 30, y + 30);
        //to do refactor graphing to be separate class outside of household, to be used on manor as well, if possible
    }
}
