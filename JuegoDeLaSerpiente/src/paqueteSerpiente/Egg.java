package paqueteSerpiente;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

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
	
	//Metodos
	
	//Se establecen nuevas coordenadas aleatorias al huevo
	public void respawn() {
		
		posX = (int) (Math.random()*18)*20+20;
        posY = (int) (Math.random()*17)*20+40;
        
        this.setPosX(posX);
		this.setPosY(posY);
		
	}
	
	public void drawEgg(Graphics ge) {
		
		//Si el huevo aparece en algun nodo de la serpiente se le establecen nuevas coordenadas 
		for (int i=0; i<Snake.snake.size(); i++) {
			
			if (Snake.snake.get(i).getPosX() == this.posX && Snake.snake.get(i).getPosY() == this.posY) {
				
				respawn();
				
			}
			
			//Se dibuja el huevo
			ge.setColor(Color.orange);
			ge.fillOval(this.posX, this.posY, 20, 20);
			
		}
		
	}
	
}