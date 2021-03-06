package paqueteSerpiente;

public class Node {
    //Atributos
    private int posX;
    private int posY;
    private int lastPosX;
    private int lastPosY;

    //Constructor
    public Node(int posX, int posY) {
        super();
        this.posX = posX;
        this.posY = posY;
    }

    //Setters y Getters
    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getLastPosX() {
        return lastPosX;
    }

    public void setLastPosX(int lastPosX) {
        this.lastPosX = lastPosX;
    }

    public int getLastPosY() {
        return lastPosY;
    }

    public void setLastPosY(int lastPosY) {
        this.lastPosY = lastPosY;
    }

}