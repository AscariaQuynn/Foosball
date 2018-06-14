package cz.example.foosball.repository;

import cz.example.foosball.model.GameTable;
import org.springframework.data.repository.CrudRepository;

public interface GameTableRepository extends CrudRepository<GameTable, Integer> {

	GameTable findByName(String name);
}
