package paqueteSerpiente;

public class Player {
    int id;
    double record;
    String name;

    public Player(String name, double record) {
        this.record = record;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getRecord() {
        return record;
    }

    public void setRecord(double record) {
        this.record = record;
    }

    public String getName() {
        return name;
    }

}
