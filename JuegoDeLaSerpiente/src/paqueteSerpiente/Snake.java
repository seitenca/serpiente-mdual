package paqueteSerpiente;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Snake {
	
	//Atributos
	private Sentido snt;
	ArrayList<Node> snake = new ArrayList<Node>();
	private boolean eatenEgg = false;
	private boolean colision = false;
	
	//Constructor
	public Snake(Sentido snt) {
		super();
		this.snt = snt;
	}
	
	//Setters y Getters
	public Sentido getSnt() {
		return snt;
	}

	public void setSnt(Sentido snt) {
		this.snt = snt;
	}

	public ArrayList<Node> getSnake() {
		return snake;
	}

	public void setSnake(ArrayList<Node> snake) {
		this.snake = snake;
	}

	public boolean isEatenEgg() {
		return eatenEgg;
	}

	public void setEatenEgg(boolean eatenEgg) {
		this.eatenEgg = eatenEgg;
	}

	public boolean isColision() {
		return colision;
	}

	public void setColision(boolean colision) {
		this.colision = colision;
	}

	//Funciones
	public void addNodeToArray(Node node) {
		snake.add(0,node);
	}
		
	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		switch(key) {
		
		case KeyEvent.VK_UP :
			if(getSnt() != Sentido.D) {
				setSnt(Sentido.U);
			}
			break;
		
		case KeyEvent.VK_DOWN :
			if(getSnt() != Sentido.U) {
				setSnt(Sentido.D);
			}
			break;
			
		case KeyEvent.VK_LEFT :
			if(getSnt() != Sentido.R) {
				setSnt(Sentido.L);
			}
			break;
			
		case KeyEvent.VK_RIGHT :
			if(getSnt() != Sentido.L) {
				setSnt(Sentido.R);
			}
			break;
		
		}
		
	}
	
	public enum Sentido {
		
		U,D,L,R
		
	}
	
	private void removeNodeBack() {
		
		snake.set(snake.size()-1, null);
		
	}
	
	public void advance() {
		
		Node node = null;
		switch(snt) {
		
		case U:
			node = new Node(snake.get(0).getPosX(),snake.get(0).getPosY()-1);
			break;
			
		case D:
			node = new Node(snake.get(0).getPosX(),snake.get(0).getPosY()+1);
			break;
			
		case L:
			node = new Node(snake.get(0).getPosX()-1,snake.get(0).getPosY());
			break;
			
		case R:
			node = new Node(snake.get(0).getPosX()+1,snake.get(0).getPosY());
			break;
		
		}
		
		for (int i = 0; i < snake.size(); i++) {
			
			if((node.getPosX() == snake.get(i).getPosX() && node.getPosY() == snake.get(i).getPosY())||(node.getPosX() < 1 && node.getPosY() < 1)||(node.getPosX() > 30 && node.getPosY() > 30) ) {
				
				//Game Over
				
			}
			
		}
			
//		if(node.getPosX() == getEgg().getPosX() && node.getPosY() == FramePrincipal.egg.getPosY()) {
//			
//			addNodeToArray(node);
//			
//		}else{
//			
//			addNodeToArray(node);
//			removeNodeBack();
//		}
		
	}
	
	public void drawSnake(Graphics gs) {
				
		if(snake.size() == 0) {
		
			addNodeToArray(new Node(100,100));
			
		}
		
		advance();
		
		for (int i = 0; i < snake.size(); i++) {
			Color color = gs.getColor();
			gs.setColor(color.green);
			gs.fillRect(snake.get(i).getPosX(), snake.get(i).getPosY(), 20, 20);
		
		}
				
	}
	
}
