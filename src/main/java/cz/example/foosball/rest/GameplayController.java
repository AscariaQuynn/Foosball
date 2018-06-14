package cz.example.foosball.rest;

import cz.example.foosball.game.CreateGameplay;
import cz.example.foosball.game.UuidGameplay;
import cz.example.foosball.model.Gameplay;
import cz.example.foosball.service.GameplayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameplayController {

    public static final String MAPPING_BASE = "/rest/gameplay";

    @Autowired
    private GameplayService gameplayService;

    /**
     * Creates new gameplay. Returns gameplay's UUID for easy identification of all players in exactly same game.
     * @param createGameplay
     * @return
     */
    @RequestMapping(value = MAPPING_BASE + "/create", method = RequestMethod.POST)
    public UuidGameplay create(CreateGameplay createGameplay) {
        return gameplayService.createGameplay(createGameplay);
    }

    @RequestMapping(value = MAPPING_BASE, method = RequestMethod.GET)
    public Iterable<Gameplay> findAll() {
        return gameplayService.findAll();
    }

    @RequestMapping(value = MAPPING_BASE + "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        gameplayService.delete(id);
    }

    @RequestMapping(value = MAPPING_BASE + "/{id}/win", method = RequestMethod.POST)
    public void win(@PathVariable("id") int id) {
        gameplayService.win(id);
    }

    @RequestMapping(value = MAPPING_BASE + "/{id}/loss", method = RequestMethod.POST)
    public void loss(@PathVariable("id") int id) {
        gameplayService.loss(id);
    }
}
