package cz.example.foosball.repository;

import cz.example.foosball.model.Game;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Integer> {

	Game findByName(String name);
}
