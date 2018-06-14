# Foosball

- Clone Repository
- Setup Configuration in IntellijIDEA
    - Edit Configurations -> Add New Configuration -> Maven
    - In Opened Window -> Add to "Command line" -> tomcat7:run
    - Hit OK
- Hit Play
- Open http://localhost:8080, you should see app, try to use it
- On http://localhost:8080/console is H2 DB console, JDBC URL is jdbc:h2:~/app_db, i dont know why there are no visible app's tables


### 14.6.2018

- Database model was changed to allow for tracking wins and loses, it uses "extended linking table" between players and game tables with additional information
- Test of Gameplay creation was implemented
- Gameplay was implemented, try to solve how to use it by yourself :)

### 4.6.2018

There is no gameplay, but you can assign 2 players to a gameTable. To see
the join, try to pause debugger at GameService.java:44

This is my longest project for interview, 2 days, also its 23:29 and
i think i end it here. If you are interested, i can continue on this
project. Lets say phase 1 is completed. Phase 2 is on demand... :)
