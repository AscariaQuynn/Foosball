package cz.example.foosball.model;

import java.io.Serializable;

public class PlayerDetail implements Serializable {

    private int id;

    private String nick;

    private int wins;

    private int loses;

    public PlayerDetail() {
    }

    public PlayerDetail(int id, String nick, long wins, long loses) {
        setId(id);
        setNick(nick);
        setWins((int)wins);
        setLoses((int)loses);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
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
}
