package cz.example.foosball.repository;

import cz.example.foosball.model.Player;
import cz.example.foosball.model.PlayerDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PlayerRepository extends CrudRepository<Player, Integer> {

	Player findByNick(String nick);

    // variant #2
    @Query(value = "select new cz.example.foosball.model.PlayerDetail(" +
            " p.id," +
            " p.nick," +
            " (select count(*) from Gameplay g where g.player = p and g.status = 2) as wins," +
            " (select count(*) from Gameplay g where g.player = p and g.status = 3) as loses" +
            " )" +
            " from Player p" +
            " where p.id = :id")
    Iterable<PlayerDetail> findOneDetail(@Param("id") int id);
}
