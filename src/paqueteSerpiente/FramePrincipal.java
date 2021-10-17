package paqueteSerpiente;

import paqueteSerpiente.Snake.Sentido;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FramePrincipal extends JFrame {
    public static JDialog jDialog;
    public static JLabel labelDialog;
    //Atributos
    private final int cell_width;
    private final int cell_height;
    private final int rows;
    private final int cols;
    private int speed;
    private final Snake snk;
    private final Egg egg;

    //Constructor
    public FramePrincipal(int cell_width, int cell_height, int rows, int cols) {
        super();
        this.cell_width = cell_width;
        this.cell_height = cell_height;
        this.rows = rows;
        this.cols = cols;
        this.snk = new Snake(Sentido.R);
        this.egg = new Egg();
        // Establecer velocidad dependiendo la dificultad
        switch (Main.difficulty) {
            case 0 -> this.speed = 150;
            case 1 -> this.speed = 90;
            case 2 -> this.speed = 30;
        }
        // Se establecen las características de FramePrincipal
        this.setTitle("Juego de la Serpiente");
        this.setBounds(500, 150, 600, 600);
        this.addKeyListener(snk);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setResizable(false);
        this.setVisible(true);
        // creation of the win/lost dialog
        JButton button1 = new JButton("Return to Menu");
        JButton button2 = new JButton("Play again");
        labelDialog = new JLabel();
        jDialog = new JDialog(this, "", true);
        jDialog.add(new JLabel());
        jDialog.setLocationRelativeTo(null);
        jDialog.setLayout(new FlowLayout());
        jDialog.setSize(250, 100);
        jDialog.add(button1);
        jDialog.add(button2);
        jDialog.add(labelDialog);

        // Button event to return to menu
        button1.addActionListener(e -> {
            // Reiniciamos puntos a 0;
            Snake.puntos = 0;
            jDialog = null;
            this.dispose();
            Main.menuPanel = new Menu();
        });
        // Button event to play again
        button2.addActionListener(e -> {
            // Reiniciamos puntos a 0;
            Snake.puntos = 0;
            jDialog = null;
            this.dispose();
            Main.framePrincipal = new FramePrincipal(20, 20, 30, 30);
        });
        // If the player close the dialog do the button1 event
        jDialog.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                button1.doClick();
            }
        });
    }

    //Getters y Setters
    public int getCell_width() {
        return cell_width;
    }

    public int getCell_height() {
        return cell_height;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int getSpeed() {
        return speed;
    }

    public Snake getSnk() {
        return snk;
    }

    public Egg getEgg() {
        return egg;
    }

    @Override
    public void paint(Graphics g) {
        //Se dibujan las filas, las columnas y el primer huevo
        g.setColor(Color.gray);
        for (int i = 0; i < rows; i++) {
            g.drawLine(0, i * cell_height, cols * cell_width, i * cell_height);
        }
        for (int i = 0; i < cols; i++) {
            g.drawLine(i * cell_width, 0, i * cell_width, rows * cell_height);
        }
        egg.drawEgg(g);
    }
}