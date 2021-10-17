package paqueteSerpiente;

import java.awt.*;

public class Egg {

    //Atributos
    private int posX;
    private int posY;

    //Constructor
    public Egg() {
        respawn();
    }

    //Getters y Setters
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

    //Métodos
    //Se establecen nuevas coordenadas aleatorias al huevo
    public void respawn() {
        posX = (int) (Math.random() * 18) * 20 + 20;
        posY = (int) (Math.random() * 17) * 20 + 40;

        this.setPosX(posX);
        this.setPosY(posY);
    }

    public void drawEgg(Graphics ge) {
        //Si el huevo aparece en algún nodo de la serpiente se le establecen nuevas coordenadas
        for (int i = 0; i < Snake.snake.size(); i++) {
            if (Snake.snake.get(i).getPosX() == this.posX && Snake.snake.get(i).getPosY() == this.posY) {
                respawn();
            }
            //Se dibuja el huevo
            ge.setColor(Color.orange);
            ge.fillOval(this.posX, this.posY, 20, 20);
        }
    }
}