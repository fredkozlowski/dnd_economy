/*
Farmer class, may refactor to remove into person interface later
For now, should contain all actions such as consuming food and producing food
 */

public class Farmer {
    int id; //id may be concatenated from household id? just an idea
    int labor; //unit tbd, amount of labor one farmer can do in one week, use man hours??

    /* need to be able to auto generate ids for agents
    public farmer(){
        this.id =
    }*/

    public Farmer(int id){
        this.id = id;
        this.labor = 10; //placeholder value
    }

    public void renewLabor(){
        labor = 10;
    }

}
