package paqueteSerpiente;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Snake {
	
	//Atributos
	private Sentido snt;
	private ArrayList<Node> snake = new ArrayList<Node>();
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
	
	public void goForward() {
		
		advance();
		
	}
	
	private void removeNodeBack() {
		
		snake.set(-1, null);
		
	}
	
	public void advance() {
		
		Node node = null;
		switch(snt) {
		
		case U:
			node = new Node(snake.get(0).getPosY()-1,snake.get(0).getPosX());
			break;
			
		case D:
			node = new Node(snake.get(0).getPosY()+1,snake.get(0).getPosX());
			break;
			
		case L:
			node = new Node(snake.get(0).getPosY(),snake.get(0).getPosX()-1);
			break;
			
		case R:
			node = new Node(snake.get(0).getPosY(),snake.get(0).getPosX()+1);
			break;
		
		}
		
		addNodeToArray(node);
				
	}
	
	public void drawSnake(Graphics g) {
		
		if(snake.get(0)==null) {
			
			return;
			
		}
		
		goForward();
		removeNodeBack();
		
		Color color = g.getColor();
		g.setColor(Color.green);
		for (int i = 0; i < snake.size(); i++) {
			g.fillRect(snake.get(i).getPosX()*FramePrincipal.cell_width,snake.get(i).getPosY()*FramePrincipal.cell_height,
					FramePrincipal.cell_width,FramePrincipal.cell_height);
			g.setColor(color);
		}
					
	}
	
}
