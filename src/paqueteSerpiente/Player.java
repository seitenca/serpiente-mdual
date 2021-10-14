package paqueteSerpiente;

public class Player {
    int points, wins, loses;
    String name;

    public Player(String name, int points, int wins, int loses) {
        this.points = points;
        this.wins = wins;
        this.loses = loses;
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLoses() {
        return loses;
    }

    public void setLoses(int loses) {
        this.loses = loses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
