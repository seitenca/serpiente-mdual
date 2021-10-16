package paqueteSerpiente;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import paqueteSerpiente.Snake.Sentido;

public class FramePrincipal extends JFrame{
	
	public static FramePrincipal framePrincipal;
	
	//Atributos
	private int cell_width = 20;
	private int cell_height = 20;
	private int rows = 30;
	private int cols = 30;
	private int speed = 90;
	private Thread gameThread;
	private Snake snk;
	private Egg egg;
	
	
	//Constructor
	public FramePrincipal(int cell_width, int cell_height, int rows, int cols) {
		
		super();
		this.cell_width = cell_width;
		this.cell_height = cell_height;
		this.rows = rows;
		this.cols = cols;
		this.snk = new Snake(Sentido.R);
		this.egg = new Egg();
		
		//Se establecen las caracteristicas de FramePrincipal
		this.setTitle("Juego de la Serpiente");
		this.setBounds(500,150,600,600);
		this.addKeyListener(snk);
		this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				
				System.exit(0);
				
			}
			
		});
		
		this.setResizable(false);
		this.setVisible(true);
		
	}

	//Getters y Setters
	public static FramePrincipal getFramePrincipal() {
		return framePrincipal;
	}

	public static void setFramePrincipal(FramePrincipal framePrincipal) {
		FramePrincipal.framePrincipal = framePrincipal;
	}

	public int getCell_width() {
		return cell_width;
	}

	public void setCell_width(int cell_width) {
		this.cell_width = cell_width;
	}

	public int getCell_height() {
		return cell_height;
	}

	public void setCell_height(int cell_height) {
		this.cell_height = cell_height;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Thread getGameThread() {
		return gameThread;
	}

	public void setGameThread(Thread gameThread) {
		this.gameThread = gameThread;
	}

	public Snake getSnk() {
		return snk;
	}

	public void setSnk(Snake snk) {
		this.snk = snk;
	}

	public Egg getEgg() {
		return egg;
	}

	public void setEgg(Egg egg) {
		this.egg = egg;
	}
	
	
	
	@Override
	public void paint(Graphics g) {
		
		//Se dibujan las filas, las columnas y el primer huevo
		g.setColor(Color.gray);
		
		for(int i=0;i<rows;i++) {
			
			g.drawLine(0,i*cell_height,cols*cell_width,i*cell_height);
			
		}
		for(int i=0;i<cols;i++) {
			
			g.drawLine(i*cell_width,0,i*cell_width,rows*cell_height);
			
		}
		
		egg.drawEgg(g);
		
	}

}