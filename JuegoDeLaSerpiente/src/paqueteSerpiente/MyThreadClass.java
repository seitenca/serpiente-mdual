package paqueteSerpiente;

import javax.swing.*;

@SuppressWarnings("serial")
public class MyThreadClass extends JFrame implements Runnable {

	@Override
	public void run() {
		
		//Se va ejecutando cada movimiento
		while (true) {
			
            try {
            	
                Thread.sleep(FramePrincipal.framePrincipal.getSpeed());
                
            } catch (InterruptedException e) {
            	
                e.printStackTrace();
                
            }
            
            FramePrincipal.framePrincipal.getSnk().move();
            FramePrincipal.framePrincipal.repaint();
            
        }
		
	}

}