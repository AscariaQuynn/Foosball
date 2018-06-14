package cz.example.foosball.game;

import java.util.List;

public class CreateGameplay {

	private int gameTableId;

	private List<Integer> playerIds;

	public int getGameTableId() {
		return gameTableId;
	}

	public void setGameTableId(int gameTableId) {
		this.gameTableId = gameTableId;
	}

	public List<Integer> getPlayerIds() {
		return playerIds;
	}

	public void setPlayerIds(List<Integer> playerIds) {
		this.playerIds = playerIds;
	}
}
