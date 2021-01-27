public class Field {
    Crops type;
    int size;
    double fieldUsage;
    double fertility;

    public Field(int size){
        this.size = size;
    }

    public Field(Crops type, int size){
        this.type = type;
        this.size = size;
    }
}