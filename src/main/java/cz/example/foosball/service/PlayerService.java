package cz.example.foosball.service;

import cz.example.foosball.model.Player;
import cz.example.foosball.model.PlayerDetail;
import cz.example.foosball.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
public class PlayerService {

	@Autowired
	private PlayerRepository playerRepository;

	public Player save(Player player) {
		return playerRepository.save(player);
	}

	public Iterable<Player> findAll() {
		return playerRepository.findAll();
	}

	public Player findOne(int id) {
		return playerRepository.findOne(id);
	}

	public PlayerDetail findOneDetail(int id) {
		Iterator<PlayerDetail> iterator = playerRepository.findOneDetail(id).iterator();
		return iterator.hasNext() ? iterator.next() : null;
	}

	public void delete(int id) {
		playerRepository.delete(id);
	}
}
