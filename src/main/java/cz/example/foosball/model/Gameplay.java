package cz.example.foosball.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "gameplays")
public class Gameplay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gameplayId")
    private int id;

    @Column(nullable = false)
    private String uuid;

    @ManyToOne // no definition of cascade operation, because gameTable can live well without connection with gameplay
    @JoinColumn(name = "gameTableId", nullable = false)
    private GameTable gameTable;

    @ManyToOne // no definition of cascade operation, because player can live well without connection with gameplay
    @JoinColumn(name = "playerId", nullable = false)
    private Player player;

    @Column(nullable = false)
    private Status status;

    public Gameplay() {
    }

    public Gameplay(int id, String uuid, GameTable gameTable, Player player, Status status) {
        setId(id);
        setUuid(uuid);
        setGameTable(gameTable);
        setPlayer(player);
        setStatus(status);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public GameTable getGameTable() {
        return gameTable;
    }

    public void setGameTable(GameTable gameTable) {
        this.gameTable = gameTable;
        if(!gameTable.getGameplays().contains(this)) {
            gameTable.addGameplay(this);
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
        if(!player.getGameplays().contains(this)) {
            player.addGameplay(this);
        }
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
