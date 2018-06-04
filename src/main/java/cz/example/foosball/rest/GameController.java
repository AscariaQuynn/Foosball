package cz.example.foosball.rest;

import cz.example.foosball.game.JoinGame;
import cz.example.foosball.model.Game;
import cz.example.foosball.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

	public static final String MAPPING_BASE = "/rest/game";

	@Autowired
	private GameService gameService;

	@RequestMapping(value = MAPPING_BASE, method = RequestMethod.POST)
	public Game save(Game game) {
		return gameService.save(game);
	}

	@RequestMapping(value = MAPPING_BASE, method = RequestMethod.GET)
	public Iterable<Game> findAll() {
		return gameService.findAll();
	}

	@RequestMapping(value = MAPPING_BASE + "/{id}", method = RequestMethod.GET)
	public Game findOne(@PathVariable("id") int id) {
		return gameService.findOne(id);
	}

	@RequestMapping(value = MAPPING_BASE + "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") int id) {
		gameService.delete(id);
	}

	@RequestMapping(value = MAPPING_BASE + "/join", method = RequestMethod.POST)
	public void join(JoinGame joinGame) {
		gameService.joinGame(joinGame);
	}
}
