package paqueteSerpiente;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class Menu extends JFrame {
    public Menu() {
        super("Menu - Juego de la Serpiente");

        JButton jugarButton = new JButton();
        JButton rankingButton = new JButton();
        JButton salirButton = new JButton();
        JTextField textField1 = new JTextField();
        JSlider slider1 = new JSlider();
        JPanel menu = new JPanel();
        JLabel nombreLabel = new JLabel();
        JLabel velocidadLabel = new JLabel();
        JLabel avisoLavel = new JLabel();

        this.setPreferredSize(new Dimension(400, 300));
        this.setContentPane(menu);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        avisoLavel.setVisible(false);

        menu.setLayout(new GridBagLayout());

        slider1.setValue(3);
        slider1.setMinimum(1);
        slider1.setMaximum(10);
        slider1.setPaintLabels(false);
        slider1.setPaintTicks(false);
        slider1.setValueIsAdjusting(false);
        jugarButton.setText("Jugar");
        rankingButton.setText("Ranking");
        salirButton.setText("Salir");
        textField1.setText("");
        nombreLabel.setText("Nombre:");
        velocidadLabel.setText("Velocidad:");
        avisoLavel.setForeground(Color.RED);
        avisoLavel.setText("Es obligatorio poner nombre*");

        // GridBagConstraints
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
        menu.add(slider1, gbc);
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
        menu.add(avisoLavel, gbc);

        jugarButton.addActionListener(actionEvent -> {
            if (textField1.getText().isBlank()) {
                avisoLavel.setVisible(true);
            } else {
                this.dispose();
                boolean flag = false;
                if (Main.playerArrayList != null) {
                    for (Player p : Main.playerArrayList) {
                        if (p.getName().equalsIgnoreCase(textField1.getText())) {
                            Main.player = p;
                            flag = true;
                        }
                    }
                }
                if (!flag) {
                    try {
                        Player player = new Player(textField1.getText(), 0, 0, 0);
                        Main.player = player;
                        Main.playerArrayList.add(player);
                        Main.addPlayerDB(Main.player);
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                Main.framePrincipal = new FramePrincipal(slider1.getValue());
                Snake snk = new Snake(Snake.Sentido.D);
                snk.drawSnake(Main.framePrincipal.getGraphics());
            }
        });

        rankingButton.addActionListener(actionEvent -> {
            this.dispose();
            new Ranking().setVisible(true);
        });

        salirButton.addActionListener(actionEvent -> System.exit(0));
    }
}
