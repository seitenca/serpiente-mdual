package paqueteSerpiente;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Node {
	
	//Atributos
	private int posX;
	private int posY;
	private int lastPosX;
	private int lastPosY;
	//Constructor
	public Node(int posX, int posY) {
		super();
		this.posX = posX;
		this.posY = posY;
	}
	
	//Setters y Getters
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

	public int getLastPosX() {
		return lastPosX;
	}

	public void setLastPosX(int lastPosX) {
		this.lastPosX = lastPosX;
	}

	public int getLastPosY() {
		return lastPosY;
	}

	public void setLastPosY(int lastPosY) {
		this.lastPosY = lastPosY;
	}
	
//	public void drawNode(Graphics gn) {
//		// TODO Auto-generated method stub
//		Color color = gn.getColor();
//		gn.setColor(Color.green);
//		gn.fillRect(this.posX, this.posY, 20, 20);
//		gn.setColor(color);
//	}
	
}