package paqueteSerpiente;

import javax.swing.*;

public class MyThreadClass extends JFrame implements Runnable {

    @Override
    public void run() {
        //Se va ejecutando cada movimiento mientras la serpiente no choque
        while (!Main.framePrincipal.isCollision()) {
            try {
                Thread.sleep(Main.framePrincipal.getSpeed());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Main.framePrincipal.getSnk().move();
            Main.framePrincipal.repaint();
        }
    }
}