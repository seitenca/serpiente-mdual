package paqueteSerpiente;

import java.sql.*;
import java.util.ArrayList;

public class Main {
    public static Menu menuPanel;
    public static FramePrincipal framePrincipal;
    public static ArrayList<Player> playerArrayList = new ArrayList<>();
    public static Player player;
    private static Connection connection;

    public static void main(String[] args) {
        try {
            menuPanel = new Menu();
            importDB();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void importDB() throws SQLException, ClassNotFoundException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost/battle?serverTimezone=UTC", "root", "root");
        Class.forName("com.mysql.cj.jdbc.Driver");

        Statement stmnt = connection.createStatement();

        String query = "select * from players";
        ResultSet rs = stmnt.executeQuery(query);

        while (rs.next()) {
            Player player = new Player(rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));
            playerArrayList.add(player);
        }
    }

    // Function to add players to DB
    public static void addPlayerDB(Player player) throws SQLException, ClassNotFoundException, NullPointerException {
        int next_id = 0;
        Statement stmnt = connection.createStatement();

        String update = "insert into players(player_id, player_name, points, wins, losses) values (? , ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(update);

        ResultSet rs = stmnt.executeQuery("select player_id from players order by player_id desc limit 1");
        if (rs.next()) {
            next_id = rs.getInt(1);
        }
        next_id = next_id + 1;

        ps.setInt(1, next_id);
        ps.setString(2, player.getName());
        ps.setInt(3, player.getPoints());
        ps.setInt(4, player.getWins());
        ps.setInt(5, player.getLoses());
        ps.executeUpdate();
    }

    // Function to update all stats in database
    public static void updatePlayerStatsDB() throws SQLException, NullPointerException {
        String resetStats = "update players set points = ?, wins = ?, losses = ? where player_id = ?";
        PreparedStatement ps = connection.prepareStatement(resetStats);
        ps.setInt(1, Main.player.getPoints());
        ps.setInt(2, Main.player.getWins());
        ps.setInt(3, Main.player.getLoses());
        ps.executeUpdate();
    }
}
