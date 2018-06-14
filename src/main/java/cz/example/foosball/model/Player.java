package cz.example.foosball.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Formula;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "players")
public class Player implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "playerId")
	private int id;

	@Column(nullable = false)
	private String nick;

	@JsonIgnore
	@OneToMany(mappedBy = "player", fetch = FetchType.LAZY)
	private Set<Gameplay> gameplays = new HashSet<>();

	// variant #1
	@Formula("(select count(*) from gameplays g where g.player_id = player_id and g.status = 2)")
	private int wins;

	// variant #1
	@Formula("(select count(*) from gameplays g where g.player_id = player_id and g.status = 3)")
	private int loses;

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

	public Set<Gameplay> getGameplays() {
		return gameplays;
	}

	public void setGameplays(Set<Gameplay> gameplays) {
		this.gameplays = gameplays;
		for(Gameplay gameplay : gameplays) {
			if(gameplay.getPlayer() != this) {
				gameplay.setPlayer(this);
			}
		}
	}

	public void addGameplay(Gameplay gameplay) {
		gameplays.add(gameplay);
		if(gameplay.getPlayer() != this) {
			gameplay.setPlayer(this);
		}
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
