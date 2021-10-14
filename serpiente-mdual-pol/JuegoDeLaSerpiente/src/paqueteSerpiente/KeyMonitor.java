package paqueteSerpiente;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyMonitor extends KeyAdapter{
	
	public void keyReleased(KeyEvent evt) {
        if (evt.getKeyChar() == 'p') {
        	try {
				FramePrincipal.framePrincipal.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	if (evt.getKeyChar() == 'r') {
            	
        		FramePrincipal.framePrincipal.notify();
            	
        	}
        	
        }
        	
        if (evt.getKeyChar() == 'p') {
        	
        	FramePrincipal.framePrincipal.dispose();
        	
        }
        
        switch(evt.getKeyChar()) {
		
		case 'p':
			try {
				FramePrincipal.framePrincipal.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case 'r':
			FramePrincipal.framePrincipal.notify();
			break;
			
		case 'e':
			FramePrincipal.framePrincipal.dispose();
			break;
			
//		case 'a':
//			FramePrincipal.snk.setSnt(L);
//			break;
		
		}
        
    }

//	p == pause
//	r == reanude
//	esc == exit
	
}
