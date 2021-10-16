package paqueteSerpiente;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Snake implements KeyListener{
	
	//Atributos
	static Sentido snt;
	static ArrayList<Node> snake = new ArrayList<Node>();
	private int posX = 20;
	private int posY = 40;
	private Node node = new Node(posX, posY);
	
	//Constructor
	public Snake(Sentido snt) {
		
		this.snt = snt;
		
	}
	
	//Setters y Getters
	public static Sentido getSnt() {
		return snt;
	}

	public static void setSnt(Sentido snt) {
		Snake.snt = snt;
	}

	public static ArrayList<Node> getSnake() {
		return snake;
	}

	public static void setSnake(ArrayList<Node> snake) {
		Snake.snake = snake;
	}

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

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	//Metodos
	
	//Se anyade un nuevo nodo a la serpiente
	public void addNodeToArray() {
		
		snake.add(new Node(node.getLastPosX(),node.getLastPosY()));
	
	}
	
	//Establece el sentido de la serpiente en funcion de la tecla que recibe
	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			
			if(getSnt() != Sentido.D) {
				
				setSnt(Sentido.U);
			}
			
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			
			if(getSnt() != Sentido.R) {
				
				setSnt(Sentido.L);
				
			}
			
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			
			if(getSnt() != Sentido.U) {
				
				setSnt(Sentido.D);
				
			}
			
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			
			if(getSnt() != Sentido.L) {
				
				setSnt(Sentido.R);
				
			}
			
		}
		
	}
	
	public enum Sentido {
		
		U,L,D,R
		
	}
	
	//Indica el movimiento a realizar en funcion del sentido que tenga asignado la serpiente
	public void move() {
		
		switch(getSnt()) {
		
		case U:
			movement(0, -1);
			break;
			
		case L:
        	movement(-1, 0);
        	break;
        	
		case D:
        	movement(0, 1);
        	break;
        	
		case R:
        	movement(1, 0);
        	break;
        	
        }
		
	}
	
	//Indica las nuevas coordenadas de la serpiente segun lo recibido del metodo move()
	public void movement(int x, int y) {
		
		posX += x*20;
		posY += y*20;
		
		drawSnake(FramePrincipal.framePrincipal.getGraphics());
		
	}
		
	public void drawSnake(Graphics gs){
		
		//Si no hay nodos en la serpiente se le anyade uno nuevo
		if (snake.size() == 0) {
			
			snake.add(new Node(posX,posY));
			
		}
		
		//Se establecen las coordenadas correspondientes a cada nodo para dar movimiento
		for (int i=0;i<snake.size();i++) {
			
			snake.get(i).setLastPosX(snake.get(i).getPosX());
			snake.get(i).setLastPosY(snake.get(i).getPosY());
			
			if (i > 0) {
				
				snake.get(i).setPosX(snake.get(i-1).getLastPosX());
				snake.get(i).setPosY(snake.get(i-1).getLastPosY());
				
			} else {
				
				snake.get(i).setPosX(node.getPosX());
				snake.get(i).setPosY(node.getPosY());
				
				//Si la serpiente come un huevo, se dibuja un nuevo huevo y se anyade un nuevo nodo a la serpiente 
				if (FramePrincipal.framePrincipal.getEgg().getPosX() == node.getPosX() && 
					FramePrincipal.framePrincipal.getEgg().getPosY() == node.getPosY()) {
						
						FramePrincipal.framePrincipal.getEgg().respawn();
						FramePrincipal.framePrincipal.getEgg().drawEgg(gs);
			    		
						addNodeToArray();
						
						//Por cada huevo se suma 1 punto
						//Facil (speed=140) -> Multiplicar punto por 0,5
						//Normal (speed=90) -> Multiplicar punto por 1
						//Dificil (speed=40) -> Multiplicar punto por 1,5
						
				}
				
				//Si la serpiente choca consigo misma se acaba la partida
				for (int j = 1; j < snake.size(); j++) {
					
					if (j > 1 && snake.get(i).getPosX() == snake.get(j).getPosX() && snake.get(i).getPosY() == snake.get(j).getPosY()) {
						
						System.exit(0);
						
					}
					
				}
				
				//Si la serpiente choca con los limites del tablero se acaba la partida
				if (snake.get(i).getPosX() < 20 || snake.get(i).getPosY() < 40 || 
					snake.get(i).getPosX() > FramePrincipal.framePrincipal.getCols()*FramePrincipal.framePrincipal.getCell_width() - 40 || 
					snake.get(i).getPosY() > FramePrincipal.framePrincipal.getRows()*FramePrincipal.framePrincipal.getCell_height() - 40) {
					
					System.exit(0);
					
				}
				
			}
			
			//Se pinta la serpiente
			gs.setColor(Color.green);
			gs.fillRect(snake.get(i).getPosX(), snake.get(i).getPosY(), 20, 20);
			
			//Se despinta el ultimo nodo de la serpiente
			if (i == snake.size() - 1) {
				
    	        gs.setColor(FramePrincipal.framePrincipal.getBackground());
    	        gs.fillRect(snake.get(i).getLastPosX(), snake.get(i).getLastPosY(), 20, 20);
    	        
			}
			
		}
		
		//Se actualiza la posicion de node
		node.setPosX(posX);
		node.setPosY(posY);
    }

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}