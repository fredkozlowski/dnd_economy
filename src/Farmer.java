/*
Farmer class, may refactor to remove into person interface later
For now, should contain all actions such as consuming food and producing food
 */

public class Farmer extends Person {
    int labor; //unit tbd, amount of labor one farmer can do in one week, use man hours??
    public Farmer(int id){
        super(id);
        this.labor = 10; //placeholder value
    }

    public void renewLabor(){
        labor = 10;
    }
}
