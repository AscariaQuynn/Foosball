package cz.example.foosball.game;

import org.joda.time.DateTime;

import java.util.UUID;

public class UuidGameplay {

    private final String uuid;

    private final DateTime dateTime;

    public UuidGameplay(UUID uuid, DateTime dateTime) {
        this.uuid = uuid.toString();
        this.dateTime = dateTime;
    }

    public String getUuid() {
        return uuid;
    }

    public DateTime getDateTime() {
        return dateTime;
    }
}
