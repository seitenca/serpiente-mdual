package paqueteSerpiente;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class Snake implements KeyListener {

    protected static final ArrayList<Node> snake = new ArrayList<>();
    //Atributos
    public static double puntos = 0;
    private static Sentido snt;
    private int posX = 20;
    private int posY = 40;
    private final Node node = new Node(posX, posY);

    //Constructor
    public Snake(Sentido snt) {
        Snake.snt = snt;
    }

    //Setters y Getters
    public static Sentido getSnt() {
        return snt;
    }

    public static void setSnt(Sentido snt) {
        Snake.snt = snt;
    }

    //Métodos
    //Se añade un nuevo nodo a la serpiente
    public void addNodeToArray() {
        snake.add(new Node(node.getLastPosX(), node.getLastPosY()));
    }

    //Establece el sentido de la serpiente en función de la tecla que recibe
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (getSnt() != Sentido.D) {
                setSnt(Sentido.U);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (getSnt() != Sentido.R) {
                setSnt(Sentido.L);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (getSnt() != Sentido.U) {
                setSnt(Sentido.D);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (getSnt() != Sentido.L) {
                setSnt(Sentido.R);
            }
        }
    }

    //Indica el movimiento a realizar en función del sentido que tenga asignado la serpiente
    public void move() {
        switch (getSnt()) {
            case U -> movement(0, -1);
            case L -> movement(-1, 0);
            case D -> movement(0, 1);
            case R -> movement(1, 0);
        }
    }

    //Indica las nuevas coordenadas de la serpiente según lo recibido del método move()
    public void movement(int x, int y) {
        posX += x * 20;
        posY += y * 20;
        drawSnake(Main.framePrincipal.getGraphics());
    }

    public void drawSnake(Graphics gs) {
        //Si no hay nodos en la serpiente se le añade uno nuevo
        if (snake.size() == 0) {
            snake.add(new Node(posX, posY));
        }
        //Se establecen las coordenadas correspondientes a cada nodo para dar movimiento
        for (int i = 0; i < snake.size(); i++) {
            snake.get(i).setLastPosX(snake.get(i).getPosX());
            snake.get(i).setLastPosY(snake.get(i).getPosY());
            if (i > 0) {
                snake.get(i).setPosX(snake.get(i - 1).getLastPosX());
                snake.get(i).setPosY(snake.get(i - 1).getLastPosY());
            } else {
                snake.get(i).setPosX(node.getPosX());
                snake.get(i).setPosY(node.getPosY());
                //Si la serpiente come un huevo, se dibuja un nuevo huevo y se añade un nuevo nodo a la serpiente
                if (Main.framePrincipal.getEgg().getPosX() == node.getPosX() &&
                        Main.framePrincipal.getEgg().getPosY() == node.getPosY()) {
                    Main.framePrincipal.getEgg().respawn();
                    Main.framePrincipal.getEgg().drawEgg(gs);

                    addNodeToArray();
                    //Por cada huevo se suma 1 punto
                    switch (Main.difficulty) {
                        //Fácil (speed=140) ≥ Multiplicar punto por 0,5
                        case 0 -> puntos = puntos + 1 * 0.5;
                        //Normal (speed=90) ≥ Multiplicar punto por 1
                        case 1 -> puntos = puntos + 1;
                        //Difícil (speed=40) ≥ Multiplicar punto por 1,5
                        case 2 -> puntos = puntos + 1 * 1.5;
                    }
                }
                //Si la serpiente choca consigo misma se acaba la partida
                for (int j = 1; j < snake.size(); j++) {
                    if (j > 1 && snake.get(i).getPosX() == snake.get(j).getPosX() && snake.get(i).getPosY() == snake.get(j).getPosY()) {
                        gameOver();
                    }
                }
                //Si la serpiente choca con los límites del tablero se acaba la partida
                if (snake.get(i).getPosX() < 20 || snake.get(i).getPosY() < 40 ||
                        snake.get(i).getPosX() > Main.framePrincipal.getCols() * Main.framePrincipal.getCell_width() - 40 ||
                        snake.get(i).getPosY() > Main.framePrincipal.getRows() * Main.framePrincipal.getCell_height() - 40) {
                    gameOver();
                }
            }
            if (Main.framePrincipal.isActive()) {
                //Se pinta la serpiente
                gs.setColor(Color.green);
                gs.fillRect(snake.get(i).getPosX(), snake.get(i).getPosY(), 20, 20);
                //Se despinta el último nodo de la serpiente
                if (i == snake.size() - 1) {
                    gs.setColor(Main.framePrincipal.getBackground());
                    gs.fillRect(snake.get(i).getLastPosX(), snake.get(i).getLastPosY(), 20, 20);
                }
            }
        }
        //Se actualiza la posición de node
        node.setPosX(posX);
        node.setPosY(posY);
    }

    public void gameOver() {
        if (Snake.puntos > Main.player.getRecord()) {
            FramePrincipal.labelDialog.setText(Snake.puntos + " pts(record)");
        } else {
            FramePrincipal.labelDialog.setText(Snake.puntos + " pts");
        }
        try {
            Main.addGameDB(Snake.puntos, Main.player.getName());
            Main.partidasArrayList.add(new Player(Main.player.getName(), Snake.puntos));
            if (Snake.puntos > Main.player.getRecord()) {
                Main.player.setRecord(Snake.puntos);
                Main.updatePlayerStatsDB();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (FramePrincipal.jDialog != null) {
            FramePrincipal.jDialog.setVisible(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public enum Sentido {
        U, L, D, R
    }

}