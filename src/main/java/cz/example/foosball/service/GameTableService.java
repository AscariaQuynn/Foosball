package cz.example.foosball.service;

import cz.example.foosball.model.GameTable;
import cz.example.foosball.repository.GameTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameTableService {

	@Autowired
	private GameTableRepository gameTableRepository;

	@Autowired
	private PlayerService playerService;

	public GameTable save(GameTable gameTable) {
		return gameTableRepository.save(gameTable);
	}

	public Iterable<GameTable> findAll() {
		return gameTableRepository.findAll();
	}

	public GameTable findOne(int id) {
		return gameTableRepository.findOne(id);
	}

	public void delete(int id) {
		gameTableRepository.delete(id);
	}
}
