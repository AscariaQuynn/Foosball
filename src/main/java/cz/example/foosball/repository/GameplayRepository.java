package cz.example.foosball.repository;

import cz.example.foosball.model.Gameplay;
import org.springframework.data.repository.CrudRepository;

public interface GameplayRepository extends CrudRepository<Gameplay, Integer> {

    Iterable<Gameplay> findAllByUuidOrderByIdAsc(String uuid);
}
