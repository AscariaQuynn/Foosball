package cz.example.foosball.repository;

import cz.example.foosball.model.Player;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Integer> {

	Player findByNick(String nick);
}
