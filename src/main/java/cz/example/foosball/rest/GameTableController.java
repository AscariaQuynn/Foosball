package cz.example.foosball.rest;

import cz.example.foosball.model.GameTable;
import cz.example.foosball.service.GameTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameTableController {

	public static final String MAPPING_BASE = "/rest/gameTable";

	@Autowired
	private GameTableService gameTableService;

	@RequestMapping(value = MAPPING_BASE, method = RequestMethod.POST)
	public GameTable save(GameTable gameTable) {
		return gameTableService.save(gameTable);
	}

	@RequestMapping(value = MAPPING_BASE, method = RequestMethod.GET)
	public Iterable<GameTable> findAll() {
		return gameTableService.findAll();
	}

	@RequestMapping(value = MAPPING_BASE + "/{id}", method = RequestMethod.GET)
	public GameTable findOne(@PathVariable("id") int id) {
		return gameTableService.findOne(id);
	}

	@RequestMapping(value = MAPPING_BASE + "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") int id) {
		gameTableService.delete(id);
	}
}
