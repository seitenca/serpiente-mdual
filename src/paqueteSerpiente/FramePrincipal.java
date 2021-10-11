package paqueteSerpiente;

import paqueteSerpiente.Snake.Sentido;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FramePrincipal extends JFrame {

    //Atributos
    static int cell_width = 20;
    static int cell_height = 20;
    int rows = 30;
    int cols = 30;
    int speed = 3;
    boolean pause = false;
    int boardEdge = 150;
    Thread gameThread;
    Snake snk = new Snake(Sentido.U);
    Egg egg;

    //Constructor
    public FramePrincipal() {

    }

    public FramePrincipal(int rows, int cols, int speed, boolean pause, int boardEdge, Thread gameThread, Snake snk,
                          Egg egg) throws HeadlessException {
        super();
        this.rows = rows;
        this.cols = cols;
        this.speed = speed;
        this.pause = pause;
        this.boardEdge = boardEdge;
        this.gameThread = gameThread;
        this.snk = snk;
        this.egg = egg;
    }

//	//Getters y Setters
//	public int getCell_width() {
//		return cell_width;
//	}
//	public void setCell_width(int cell_with) {
//		this.cell_width = cell_with;
//	}
//	public int getCell_height() {
//		return cell_height;
//	}
//	public void setCell_height(int cell_height) {
//		this.cell_height = cell_height;
//	}
//	public int getRows() {
//		return rows;
//	}
//	public void setRows(int rows) {
//		this.rows = rows;
//	}
//	public int getCols() {
//		return cols;
//	}
//	public void setCols(int cols) {
//		this.cols = cols;
//	}
//	public int getSpeed() {
//		return speed;
//	}
//	public void setSpeed(int speed) {
//		this.speed = speed;
//	}
//	public Thread getHiloJuego() {
//		return hiloJuego;
//	}
//	public void setHiloJuego(Thread hiloJuego) {
//		this.hiloJuego = hiloJuego;
//	}
//	public Snake getSnk() {
//		return snk;
//	}
//	public void setSnk(Snake snk) {
//		this.snk = snk;
//	}
//	public Egg getEgg() {
//		return egg;
//	}
//	public void setEgg(Egg egg) {
//		this.egg = egg;
//	}

    public static void main(String[] args) {

        new FramePrincipal().launch();
    }

    @Override
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.gray);

        for (int i = 0; i < rows; i++) {
            g.drawLine(0, i * cell_height, cols * cell_width, i * cell_height);
        }
        for (int i = 0; i < cols; i++) {
            g.drawLine(i * cell_width, 0, i * cell_width, rows * cell_height);
        }

        g.setColor(color);
        new Snake(Sentido.U).drawSnake(g);
    }

    public void launch() {

        this.setTitle("Juego de la Serpiente");
        this.setBounds(700, 300, rows * cell_height, cols * cell_width);
        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

        });

        this.setResizable(false);
        this.setVisible(true);

        new Thread(new MyThreadClass()).start();

    }

}

