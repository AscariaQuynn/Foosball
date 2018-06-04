package cz.example.foosball.service;

import cz.example.foosball.game.JoinGame;
import cz.example.foosball.model.Game;
import cz.example.foosball.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class GameService {

	@Autowired
	private GameRepository gameRepository;

	@Autowired
	private PlayerService playerService;

	public Game save(Game game) {
		return gameRepository.save(game);
	}

	public Iterable<Game> findAll() {
		return gameRepository.findAll();
	}

	public Game findOne(int id) {
		return gameRepository.findOne(id);
	}

	public void delete(int id) {
		gameRepository.delete(id);
	}

	public void joinGame(JoinGame joinGame) {
		Game game = findOne(joinGame.getGameId());
		game.setPlayers(new ArrayList<>(Arrays.asList(
			playerService.findOne(joinGame.getPlayer1Id()),
			playerService.findOne(joinGame.getPlayer2Id())
		)));
		Game savedGame = save(game);
	}
}
