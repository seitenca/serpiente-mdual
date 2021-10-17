package paqueteSerpiente;

import javax.swing.*;

public class MyThreadClass extends JFrame implements Runnable {

    @Override
    public void run() {
        //Se va ejecutando cada movimiento
        while (true) {
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