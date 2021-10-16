package paqueteSerpiente;

public class Main {
	
	//Se ejecuta el juego
	public static void main(String[] args) {
		
		FramePrincipal.framePrincipal = new FramePrincipal(20,20,30,30);
		new Thread(new MyThreadClass()).start();
		
	}	
	
}
