package paqueteSerpiente;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class Menu extends JFrame {

    //Constructor de menu
    public Menu() {
        super("Menu - Juego de la Serpiente");
        JButton jugarButton = new JButton();
        JButton rankingButton = new JButton();
        JButton salirButton = new JButton();
        JTextField textField1 = new JTextField();
        String[] difficulties = {"Facil", "Normal", "Dificil"};
        JComboBox<String> comboBox = new JComboBox<>(difficulties);
        JPanel menu = new JPanel();
        JLabel nombreLabel = new JLabel();
        JLabel velocidadLabel = new JLabel();
        JLabel avisoLabel = new JLabel();

        this.setPreferredSize(new Dimension(400, 300));
        this.setContentPane(menu);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        avisoLabel.setVisible(false);
        menu.setLayout(new GridBagLayout());

        jugarButton.setText("Jugar");
        rankingButton.setText("Ranking");
        salirButton.setText("Salir");
        textField1.setText("");
        nombreLabel.setText("Nombre:");
        velocidadLabel.setText("Dificultad:");
        avisoLabel.setForeground(Color.RED);
        avisoLabel.setText("Es obligatorio poner nombre*");

        //Posicionar elementos en menu
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu.add(jugarButton, gbc);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu.add(rankingButton, gbc);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu.add(salirButton, gbc);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu.add(textField1, gbc);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menu.add(comboBox, gbc);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        menu.add(nombreLabel, gbc);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        menu.add(velocidadLabel, gbc);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        menu.add(avisoLabel, gbc);

        jugarButton.addActionListener(actionEvent -> {
            //Se asigna la dificultad en funcion de la eleccion del jugador
            Object selectedItem = comboBox.getSelectedItem();
            if ("Facil".equals(selectedItem)) {
                Main.difficulty = 0;
            } else if ("Normal".equals(selectedItem)) {
                Main.difficulty = 1;
            } else if ("Dificil".equals(selectedItem)) {
                Main.difficulty = 2;
            }
            //Se comprueba que el jugador introduce un nombre
            if (textField1.getText().isBlank()) {
                avisoLabel.setVisible(true);
            } else {
                this.dispose();
                boolean flag = false;
                //Se comprueba que haya algun jugador y si no existe el usuario
                if (Main.playerArrayList != null) {
                    for (Player p : Main.playerArrayList) {
                        if (p.getName().equalsIgnoreCase(textField1.getText())) {
                            Main.player = p;
                            flag = true;
                        }
                    }
                }
                //Si no encuentra ningun jugador o el introducido no existe se anyade el nuevo jugador
                if (!flag) {
                    try {
                        Player player = new Player(textField1.getText(), 0);
                        Main.player = player;
                        Main.playerArrayList.add(player);
                        Main.addPlayerDB(Main.player);
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                //Se ejecuta el Frame Principal
                Main.framePrincipal = new FramePrincipal(20, 20, 30, 30);
                //Se ejecuta el hilo 
                new Thread(new MyThreadClass()).start();

            }
        });

        rankingButton.addActionListener(actionEvent -> {
            this.dispose();
            new Ranking().setVisible(true);
        });

        salirButton.addActionListener(actionEvent -> System.exit(0));
    }
}
