package cz.example.foosball.service;

import cz.example.foosball.game.CreateGameplay;
import cz.example.foosball.game.UuidGameplay;
import cz.example.foosball.model.GameTable;
import cz.example.foosball.model.Gameplay;
import cz.example.foosball.model.Player;
import cz.example.foosball.model.Status;
import cz.example.foosball.repository.GameplayRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class GameplayService {

    @Autowired
    private GameplayRepository gameplayRepository;

    @Autowired
    private GameTableService gameTableService;

    @Autowired
    private PlayerService playerService;

    @Transactional
    public UuidGameplay createGameplay(CreateGameplay createGameplay) {
        UuidGameplay uuidGameplay = new UuidGameplay(UUID.randomUUID(), DateTime.now());
        GameTable gameTable = gameTableService.findOne(createGameplay.getGameTableId());
        Assert.notNull(gameTable, "GameTable with id " + createGameplay.getGameTableId() + " was not found.");
        List<Gameplay> gameplayList = new ArrayList<>();
        List<Integer> playerIds = createGameplay.getPlayerIds();
        Assert.isTrue(playerIds.size() == 4, "Gameplay should have exactly 4 players.");
        for(Integer playerId : playerIds) {
            Player player = playerService.findOne(playerId);
            Assert.notNull(player, "Player with id " + playerId + " was not found.");
            Gameplay gameplay = new Gameplay();
            gameplay.setUuid(uuidGameplay.getUuid());
            gameplay.setGameTable(gameTable);
            gameplay.setPlayer(player);
            gameplay.setStatus(Status.NONE);
            gameplayList.add(gameplay);
        }
        gameplayRepository.save(gameplayList);
        return uuidGameplay;
    }

    public Iterable<Gameplay> findAll() {
        return gameplayRepository.findAll();
    }

    public Iterable<Gameplay> findAllByUuidOrderByIdAsc(String uuid) {
        return gameplayRepository.findAllByUuidOrderByIdAsc(uuid);
    }

    @Transactional
    public void delete(int id) {
        Gameplay gameplay = gameplayRepository.findOne(id);
        Iterable<Gameplay> gameplays = gameplayRepository.findAllByUuidOrderByIdAsc(gameplay.getUuid());
        gameplayRepository.delete(gameplays);
    }

    public void win(int id) {
        changeStatusOfGameplay(id, Status.WIN, Status.LOSS);
    }

    public void loss(int id) {
        changeStatusOfGameplay(id, Status.LOSS, Status.WIN);
    }

    /**
     * This method is somewhat created fast because i think it is not important for this project.
     * @param id
     * @param trueStatus
     * @param falseStatus
     */
    @Transactional
    private void changeStatusOfGameplay(int id, Status trueStatus, Status falseStatus) {
        Iterable<Gameplay> gameplays = gameplayRepository.findAllByUuidOrderByIdAsc(gameplayRepository.findOne(id).getUuid());
        // Detect position of trueStatus player - 0 & 1 are first team, 2 & 3 are second team
        int i = 0;
        for(Gameplay gameplay : gameplays) {
            if(gameplay.getId() == id) {
                break;
            }
            i++;
        }
        // Set trueStatus team
        int j = 0;
        for(Gameplay gameplay : gameplays) {
            gameplay.setStatus((i <= 1 && j <= 1) || (i > 1 && j > 1) ? trueStatus : falseStatus);
            gameplayRepository.save(gameplay);
            j++;
        }
    }
}
