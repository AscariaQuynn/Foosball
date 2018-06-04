package cz.example.foosball.rest;

import cz.example.foosball.game.Player;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRequestController {

	@RequestMapping("/user")
	public Player getPlayer() {
		Player player = Player.Bu(.)
	}
}
