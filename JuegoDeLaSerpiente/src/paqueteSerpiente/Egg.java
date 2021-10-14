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
	private boolean posCorrect = true;
	
	//Constructor
	public Egg() {
		
	}

	public Egg(int posX, int posY) {
		super();
		this.posX = posX;
		this.posY = posY;
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

	public void respawn() {
		
		int x = (int) Math.floor(Math.random()* (600 - 20 + 1) + 20);
        int y = (int) Math.floor(Math.random()* (600 - 20 + 1) + 20);
        while (x % 20 != 0) {
            x = (int) Math.floor(Math.random()* (600 - 20 + 1) + 20);
        }
        while (y % 20 != 0) {
            y = (int) Math.floor(Math.random()* (600 - 20 + 1) + 20);
        }
				
//		for(int i = 0; i < frame.getSnk().size(); i++) {
//			
//			if(frame.getSnk().get(i).getPosX() == x && frame.getSnk().get(i).getPosY() == y) {
//				
//				respawn();
//				
//			}
//			
//		}
		
		this.setPosX(x);
		this.setPosY(y);
		
	}
	
	public void drawEgg(Graphics ge) {
		// TODO Auto-generated method stub
		Color color = ge.getColor();
		ge.setColor(Color.orange);
		ge.fillOval(this.posX, this.posY, 20, 20);
		ge.setColor(color);
	}
	
}
