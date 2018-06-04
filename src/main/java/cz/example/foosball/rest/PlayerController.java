package cz.example.foosball.rest;

import cz.example.foosball.model.Player;
import cz.example.foosball.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PlayerController {

	public static final String MAPPING_BASE = "/rest/player";

	@Autowired
	private PlayerService playerService;

	@RequestMapping(value = MAPPING_BASE, method = RequestMethod.POST)
	public Player save(Player player) {
		return playerService.save(player);
	}

	@RequestMapping(value = MAPPING_BASE, method = RequestMethod.GET)
	public Iterable<Player> findAll() {
		return playerService.findAll();
	}

	@RequestMapping(value = MAPPING_BASE + "/{id}", method = RequestMethod.GET)
	public Player findOne(@PathVariable("id") int id) {
		return playerService.findOne(id);
	}

	@RequestMapping(value = MAPPING_BASE + "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") int id) {
		playerService.delete(id);
	}
}
