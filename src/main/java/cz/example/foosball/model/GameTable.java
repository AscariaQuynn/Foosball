package cz.example.foosball.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "gameTables")
public class GameTable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gameTableId")
	private int id;

	@Column(nullable = false)
	private String name;

	@JsonIgnore
	@OneToMany(mappedBy = "gameTable", fetch = FetchType.LAZY)
	private Set<Gameplay> gameplays = new HashSet<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Gameplay> getGameplays() {
		return gameplays;
	}

	public void setGameplays(Set<Gameplay> gameplays) {
		this.gameplays = gameplays;
		for(Gameplay gameplay : gameplays) {
			if(gameplay.getGameTable() != this) {
				gameplay.setGameTable(this);
			}
		}
	}

	public void addGameplay(Gameplay gameplay) {
		gameplays.add(gameplay);
		if(gameplay.getGameTable() != this) {
			gameplay.setGameTable(this);
		}
	}
}
