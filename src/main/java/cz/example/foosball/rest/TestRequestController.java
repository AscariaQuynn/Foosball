package cz.example.foosball.rest;

import cz.example.foosball.model.Player;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRequestController {

	@RequestMapping("/testRequest")
	public Player getPlayer() {
		Player player = new Player();
		player.setNick("Jarda");
		return player;
	}
}
