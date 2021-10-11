package paqueteSerpiente;

import javax.swing.*;

public class MyThreadClass extends JFrame implements Runnable {

    @Override
    public void run() {

        while (true) {

            repaint();

            try {

                Thread.sleep(50);

            } catch (InterruptedException e) {

                e.printStackTrace();

            }

        }

    }

}