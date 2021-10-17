package paqueteSerpiente;

import java.sql.*;
import java.util.ArrayList;

public class Main {
    public static Menu menuPanel;
    public static FramePrincipal framePrincipal;
    public static ArrayList<Player> partidasArrayList = new ArrayList<>();
    public static ArrayList<Player> playerArrayList = new ArrayList<>();
    public static Player player;
    public static int difficulty = 1;
    public static boolean threadIsRunning = false;
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
        connection = DriverManager.getConnection("jdbc:mysql://localhost/serpiente?serverTimezone=UTC", "alex", "alex");
        Class.forName("com.mysql.cj.jdbc.Driver");

        Statement stmt = connection.createStatement();
        String query = "select * from players";
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            Player player = new Player(rs.getString(2), rs.getInt(3));
            player.setId(rs.getInt(1));
            playerArrayList.add(player);
        }

        Statement stmt2 = connection.createStatement();
        String query2 = "select * from games";
        ResultSet rs2 = stmt2.executeQuery(query2);

        while (rs2.next()) {
            partidasArrayList.add(new Player(rs2.getString(2), rs2.getInt(3)));
        }
    }

    // Function to add players to DB
    public static void addPlayerDB(Player player) throws SQLException, ClassNotFoundException, NullPointerException {
        int next_id = 0;
        Statement stmt = connection.createStatement();

        String update = "insert into players(player_id, player_name, points) values (? , ?, ?)";
        PreparedStatement ps = connection.prepareStatement(update);

        ResultSet rs = stmt.executeQuery("select player_id from players order by player_id desc limit 1");
        if (rs.next()) {
            next_id = rs.getInt(1);
        }
        next_id = next_id + 1;

        ps.setInt(1, next_id);
        ps.setString(2, player.getName());
        ps.setDouble(3, player.getRecord());
        ps.executeUpdate();
    }

    // Function to add game to DB
    public static void addGameDB(double points, String player_name) throws SQLException, ClassNotFoundException, NullPointerException {
        int next_id = 0;
        Statement stmt = connection.createStatement();

        String update = "insert into games(game_id, player_name, points) values (? , ?, ?)";
        PreparedStatement ps = connection.prepareStatement(update);

        ResultSet rs = stmt.executeQuery("select game_id from games order by game_id desc limit 1");
        if (rs.next()) {
            next_id = rs.getInt(1);
        }
        next_id = next_id + 1;

        ps.setInt(1, next_id);
        ps.setString(2, player_name);
        ps.setDouble(3, points);
        ps.executeUpdate();
    }

    // Function to update all stats in database
    public static void updatePlayerStatsDB() throws SQLException, NullPointerException {
        String resetStats = "update players set points = ? where player_id = ?";
        PreparedStatement ps = connection.prepareStatement(resetStats);
        ps.setDouble(1, Main.player.getId());
        ps.setDouble(2, Main.player.getRecord());
        ps.executeUpdate();
    }
}
