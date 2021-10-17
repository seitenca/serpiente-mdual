package paqueteSerpiente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Ranking extends JFrame {
    JPanel panel = new JPanel();

    public Ranking() {
        super("Ranking - Juego de la Serpiente");
        this.setPreferredSize(new Dimension(520, 520));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        String[] columnNames = {"Nombre", "Puntos"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        addToRanking(model);

        // Sort the table
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);
        List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
        sortKeys.add(new RowSorter.SortKey(1, SortOrder.DESCENDING));
        sorter.setSortKeys(sortKeys);

        JButton button = new JButton("Salir");
        JTable table = new JTable(model);
        this.add(button);

        JScrollPane scrollPane = new JScrollPane(table);
        table.setRowSorter(sorter);
        this.add(scrollPane);

        // Button event to close the panel
        button.addActionListener(actionEvent -> {
            this.dispose();
            new Menu().setVisible(true);
        });
    }

    // Function that loads the players to the table
    public void addToRanking(DefaultTableModel model) {
        for (Player i : Main.partidasArrayList) {
            Object[] row1 = {
                    i.getName(),
                    String.valueOf(i.getRecord())
            };
            model.addRow(row1);
        }
    }
}