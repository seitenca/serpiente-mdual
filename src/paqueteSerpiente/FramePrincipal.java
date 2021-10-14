package paqueteSerpiente;

import paqueteSerpiente.Snake.Sentido;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FramePrincipal extends JFrame {

    //Atributos
    private int cell_width = 20;
    private int cell_height = 20;
    private int rows = 30;
    private int cols = 30;
    private int speed;
    private boolean pause = false;
    private int boardEdge = 150;
    private int puntos = 0;
    private Thread gameThread;
    private Snake snk = new Snake(Sentido.U);
    private Egg egg;

    //Constructor
    public FramePrincipal(int speed) {
        this.speed = speed;
        this.launch();
    }

    //Setters y Getters
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

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public int getBoardEdge() {
        return boardEdge;
    }

    public void setBoardEdge(int boardEdge) {
        this.boardEdge = boardEdge;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
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
        Color color = g.getColor();
        g.setColor(Color.gray);

        for (int i = 0; i < rows; i++) {
            g.drawLine(0, i * cell_height, cols * cell_width, i * cell_height);
        }
        for (int i = 0; i < cols; i++) {
            g.drawLine(i * cell_width, 0, i * cell_width, rows * cell_height);
        }

        g.setColor(color);
    }

    public void launch() {
        this.setTitle("Juego de la Serpiente");
        this.setBounds(700, 300, rows * cell_height, cols * cell_width);
        this.setResizable(false);
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Main.framePrincipal.dispose();
                Main.menuPanel = new Menu();
                //System.exit(0);
            }
        });
        new Thread(new MyThreadClass()).start();
    }

}

