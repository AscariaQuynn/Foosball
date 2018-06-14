package cz.example.foosball;

import cz.example.foosball.game.CreateGameplay;
import cz.example.foosball.game.UuidGameplay;
import cz.example.foosball.model.GameTable;
import cz.example.foosball.model.Gameplay;
import cz.example.foosball.model.Player;
import cz.example.foosball.service.GameTableService;
import cz.example.foosball.service.GameplayService;
import cz.example.foosball.service.PlayerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Iterator;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FoosballWebApplication.class)
public class GameplayServiceTest {

    @Autowired
    private GameTableService gameTableService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private GameplayService gameplayService;

    @Test
    public void testCreateGameTable() {
        GameTable mockedGameTable = getGameTableMock();
        GameTable savedGameTable = gameTableService.save(mockedGameTable);
        Assert.assertNotNull(savedGameTable.getId());
        Assert.assertEquals(mockedGameTable.getName(), savedGameTable.getName());
    }

    @Test
    public void testCreatePlayer() {
        Player mockedPlayer = getPlayer1Mock();
        Player savedPlayer = playerService.save(mockedPlayer);
        Assert.assertNotNull(savedPlayer.getId());
        Assert.assertEquals(mockedPlayer.getNick(), savedPlayer.getNick());
    }

    /**
     * Tries to create gameplay without existing game table or players,
     * so it should fail.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateGameplayWithoutAllFail() {
        gameplayService.createGameplay(getCreateGameplayEmptyMock());
    }

    /**
     * Tries to create gameplay again, with gameTable, but it is not enough,
     * so it should fail.
     */
    @Test(expected = NullPointerException.class)
    public void testCreateGameplayWithoutPlayersFail() {
        // Create empty object ready to be mocked
        CreateGameplay createGameplay = getCreateGameplayEmptyMock();
        // Create game table for connection
        GameTable gameTable = gameTableService.save(getGameTableMock());
        createGameplay.setGameTableId(gameTable.getId());
        // Try to create game, but without players it should fail too
        gameplayService.createGameplay(createGameplay);
    }

    /**
     * This time, we are creating gameplay again, with gameTable,
     * with insufficient number of players, so it should fail too.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateGameplayWithSinglePlayerFail() {
        // Create empty object ready to be mocked
        CreateGameplay createGameplay = getCreateGameplayEmptyMock();
        // Create game table for connection
        GameTable gameTable = gameTableService.save(getGameTableMock());
        createGameplay.setGameTableId(gameTable.getId());
        // Create player for connection
        Player player1 = playerService.save(getPlayer1Mock());
        Player player2 = playerService.save(getPlayer2Mock());
        createGameplay.setPlayerIds(Arrays.asList(
                player1.getId(),
                player2.getId()
        ));
        // Try to create game, but without players it should fail too
        gameplayService.createGameplay(createGameplay);
    }

    /**
     * Last but one, we are creating gameplay again, with gameTable,
     * with too many players, so it should fail also.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateGameplayWithTooManyPlayersFail() {
        // Create empty object ready to be mocked
        CreateGameplay createGameplay = getCreateGameplayEmptyMock();
        // Create game table for connection
        GameTable gameTable = gameTableService.save(getGameTableMock());
        createGameplay.setGameTableId(gameTable.getId());
        // Create player for connection
        Player player1 = playerService.save(getPlayer1Mock());
        Player player2 = playerService.save(getPlayer2Mock());
        Player player3 = playerService.save(getPlayer3Mock());
        Player player4 = playerService.save(getPlayer4Mock());
        Player player5 = playerService.save(getPlayer5Mock());
        createGameplay.setPlayerIds(Arrays.asList(
                player1.getId(),
                player2.getId(),
                player3.getId(),
                player4.getId(),
                player5.getId()
        ));
        // Try to create game, but with too many players, so it should fail
        gameplayService.createGameplay(createGameplay);
    }

    /**
     * Final test should simply pass as there are no otner variants of passing,
     * only 1 gametable and 4 players.
     */
    @Test
    public void testCreateGameplayPass() {
        // Create empty object ready to be mocked
        CreateGameplay createGameplay = getCreateGameplayEmptyMock();
        // Create game table for connection
        GameTable gameTable = gameTableService.save(getGameTableMock());
        createGameplay.setGameTableId(gameTable.getId());
        // Create player for connection
        Player player1 = playerService.save(getPlayer1Mock());
        Player player2 = playerService.save(getPlayer2Mock());
        Player player3 = playerService.save(getPlayer3Mock());
        Player player4 = playerService.save(getPlayer4Mock());
        createGameplay.setPlayerIds(Arrays.asList(
                player1.getId(),
                player2.getId(),
                player3.getId(),
                player4.getId()
        ));
        // Try to create game, this time it should pass
        UuidGameplay uuid = gameplayService.createGameplay(createGameplay);
        // Test gameplays against player ids, gameplays should keep same order
        // as the player ids was in
        Iterator<Gameplay> gIterator = gameplayService.findAllByUuidOrderByIdAsc(uuid.getUuid()).iterator();
        Iterator<Integer> pIterator = createGameplay.getPlayerIds().iterator();
        for(;gIterator.hasNext() && pIterator.hasNext();) {
            Gameplay gameplay = gIterator.next();
            int playerId = pIterator.next();
            // match game table
            Assert.assertEquals(gameplay.getGameTable().getId(), createGameplay.getGameTableId());
            // Match player id
            Assert.assertEquals(gameplay.getPlayer().getId(), playerId);
        }
    }


    private CreateGameplay getCreateGameplayEmptyMock() {
        CreateGameplay createGameplay = new CreateGameplay();
        return createGameplay;
    }

    private GameTable getGameTableMock() {
        GameTable gameTable = new GameTable();
        gameTable.setName("New Game Table");
        return gameTable;
    }

    private Player getPlayer1Mock() {
        Player player = new Player();
        player.setNick("Player 1");
        return player;
    }

    private Player getPlayer2Mock() {
        Player player = new Player();
        player.setNick("Player 2");
        return player;
    }

    private Player getPlayer3Mock() {
        Player player = new Player();
        player.setNick("Player 3");
        return player;
    }

    private Player getPlayer4Mock() {
        Player player = new Player();
        player.setNick("Player 4");
        return player;
    }

    private Player getPlayer5Mock() {
        Player player = new Player();
        player.setNick("Player 5");
        return player;
    }
}
