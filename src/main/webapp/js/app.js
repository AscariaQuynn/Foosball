
var app = app || {};

app.playersDataTable = null;
app.gamesDataTable = null;

app.login = function() {
    $.post('/login')
        .done(function() {
            app.refreshLoggedIn();
        })
        .fail(function(xhr, status, error) {
            console.log('login request failed');
            console.log(xhr);
            console.log(status);
            console.log(error);
        });
}

app.logout = function() {
    $.post('/logout')
        .done(function() {
            app.refreshLoggedIn();
        })
        .fail(function(xhr, status, error) {
            console.log('logout request failed');
            console.log(xhr);
            console.log(status);
            console.log(error);
        });
};

app.refreshLoggedIn = function() {
    $.get('/rest/user/simpleUser')
        .done(function(simpleUser) {
            console.log('authentication simpleUser');
            console.log(simpleUser);
            $('#user').text(simpleUser.name);
            $('#isAuthenticated').text(simpleUser.isAuthenticated ? 'Yes' : 'No');

            $('.authenticated').toggle(simpleUser.isAuthenticated);
            $('.unauthenticated').toggle(!simpleUser.isAuthenticated);
        })
        .fail(function(xhr, status, error) {
            console.log('simpleUser request failed');
            console.log(xhr);
            console.log(status);
            console.log(error);
        });
};

app.testRequest = function() {
    console.log('test request !');
    return $.get('/testRequest');
};

app.userRequest = function() {
    console.log('user request !');
    return $.get('/rest/user');
};

app.createPlayer = function(values) {
    console.log('create player request !');
    return $.post('/rest/player', values);
};

app.deletePlayer = function(id) {
    console.log('delete player ' + id + ' request !');
    $.ajax({
        url: '/rest/player/' + id,
        type: 'DELETE'
    })
    .done(function(data) {
        app.refreshPlayers();
    })
    .fail(function(xhr, status, error) {
        console.log('deletePlayer request failed');
        console.log(xhr);
        console.log(status);
        console.log(error);
    });
};

app.playerDetail = function(id) {
    console.log('player detail ' + id + ' request !');
    $.get('/rest/player/' + id)
    .done(function(data) {
        alert(
            'Player Detail:\n\n'
            + 'id: ' + data.id + '\n'
            + 'nick: ' + data.nick + '\n'
            + 'wins: ' + data.wins + '\n'
            + 'loses: ' + data.loses + '\n'
        );
    })
    .fail(function(xhr, status, error) {
        console.log('playerDetail request failed');
        console.log(xhr);
        console.log(status);
        console.log(error);
    });
}

app.listPlayers = function() {
    console.log('list players request !');
    return $.get('/rest/player');
};

app.showPlayers = function(data) {
    var data = data || [];
    // Initialize data table
    if(!app.playersDataTable) {
        console.log('creating new data table');
        app.playersDataTable = $('#playersList').DataTable({
            pageLength: 10,
            columns: [
                { data: 'id' },
                { data: 'nick' },
                { data: 'wins' },
                { data: 'loses' },
                {
                    data: null,
                    name: 'actions',
                    orderable: false,
                    searchable: false,
                    render: function(data, type, row, meta) {
                        return '<a class="button" href="javascript:app.playerDetail(' + row['id'] + ');">' + 'View' + '</a>'
                        + ' <a class="button" href="javascript:app.deletePlayer(' + row['id'] + ');">' + 'Delete' + '</a>';
                    }
                }
            ],
            data: data
        });
    } else {
        console.log('data table already exists');
        app.playersDataTable.clear().rows.add(data).draw();
        console.log('retrieved data');
        console.log(data);
    }
};

app.refreshPlayers = function() {
    app.listPlayers().done(function(data) {
        app.showPlayers(data);
    })
    .fail(function(xhr, status, error) {
        console.log('listPlayers request failed');
        console.log(xhr);
        console.log(status);
        console.log(error);
    });
}

app.createGame = function(values) {
    console.log('create game request !');
    return $.post('/rest/game', values);
};

app.deleteGame = function(id) {
    console.log('delete game ' + id + ' request !');
    $.ajax({
        url: '/rest/game/' + id,
        type: 'DELETE'
    })
    .done(function(data) {
        app.refreshGames();
    })
    .fail(function(xhr, status, error) {
        console.log('deleteGame request failed');
        console.log(xhr);
        console.log(status);
        console.log(error);
    });
};

app.gameDetail = function(id) {
    console.log('game detail ' + id + ' request !');
    $.get('/rest/game/' + id)
    .done(function(data) {
        alert(
            'Game Detail:\n\n'
            + 'id: ' + data.id + '\n'
            + 'name: ' + data.name + '\n'
        );
    })
    .fail(function(xhr, status, error) {
        console.log('gameDetail request failed');
        console.log(xhr);
        console.log(status);
        console.log(error);
    });
}

app.listGames = function() {
    console.log('list games request !');
    return $.get('/rest/game');
};

app.showGames = function(data) {
    var data = data || [];
    // Initialize data table
    if(!app.gamesDataTable) {
        console.log('creating new data table');
        app.gamesDataTable = $('#gamesList').DataTable({
            pageLength: 10,
            columns: [
                { data: 'id' },
                { data: 'name' },
                {
                    data: null,
                    name: 'actions',
                    orderable: false,
                    searchable: false,
                    render: function(data, type, row, meta) {
                        return '<a class="button" href="javascript:app.gameDetail(' + row['id'] + ');">' + 'View' + '</a>'
                        + ' <a class="button" href="javascript:app.deleteGame(' + row['id'] + ');">' + 'Delete' + '</a>';
                    }
                }
            ],
            data: data
        });
    } else {
        console.log('data table already exists');
        app.gamesDataTable.clear().rows.add(data).draw();
        console.log('retrieved data');
        console.log(data);
    }
};

app.refreshGames = function() {
    app.listGames().done(function(data) {
        app.showGames(data);
    })
    .fail(function(xhr, status, error) {
        console.log('listGames request failed');
        console.log(xhr);
        console.log(status);
        console.log(error);
    });
}

app.joinGame = function(values) {
    console.log('join game request !');
    return $.post('/rest/game/join', values);
};

$(document).ready(function() {

    app.refreshLoggedIn();
    app.refreshPlayers();
    app.refreshGames();

    $('#testRequest').on('click', function() {
        app.testRequest().done(function(data) {
            console.log('test request completed');
            console.log(data);
        })
        .fail(function(xhr, status, error) {
            console.log('test request failed');
            console.log(xhr);
            console.log(status);
            console.log(error);
            alert('Request failed: ' + status + ' ' + error);
        });
    });

    $('#userRequest').on('click', function() {
        app.userRequest().done(function(data) {
            console.log('user request completed');
            console.log(data);
        })
        .fail(function(xhr, status, error) {
            console.log('user request failed');
            console.log(xhr);
            console.log(status);
            console.log(error);
            alert('Request failed: ' + status + ' ' + error);
        });
    });

    $('#createPlayer').closest('form').on('submit', function(e) {
        e.preventDefault();
        // Check for empty
        var nick = $(this).find('input[name="nick"]');
        if(!nick.val()) {
            nick.focus();
            return;
        }
        // Send to server
        var values = $(this).serializeArray();
        console.log('Serialized values');
        console.log(values);
        app.createPlayer(values).done(function(data) {
            console.log('player created');
            console.log(data);
            $("#createPlayerForm").trigger("reset");
            app.refreshPlayers();
        })
        .fail(function(xhr, status, error) {
            console.log('create player request failed');
            console.log(xhr);
            console.log(status);
            console.log(error);
            alert('Request failed: ' + status + ' ' + error);
        });
    });

    $('#createGame').closest('form').on('submit', function(e) {
        e.preventDefault();
        // Check for empty
        var name = $(this).find('input[name="name"]');
        if(!name.val()) {
            name.focus();
            return;
        }
        // Send to server
        var values = $(this).serializeArray();
        console.log('Serialized values');
        console.log(values);
        app.createGame(values).done(function(data) {
            console.log('game created');
            console.log(data);
            $("#createGameForm").trigger("reset");
            app.refreshGames();
        })
        .fail(function(xhr, status, error) {
            console.log('create game request failed');
            console.log(xhr);
            console.log(status);
            console.log(error);
            alert('Request failed: ' + status + ' ' + error);
        });
    });

    $('#joinGame').closest('form').on('submit', function(e) {
        e.preventDefault();
        // Check for empty
        var player1Id = $(this).find('input[name="player1Id"]');
        var player2Id = $(this).find('input[name="player2Id"]');
        var gameId = $(this).find('input[name="gameId"]');
        if(!player1Id.val()) {
            player1Id.focus();
            return;
        }
        if(!player2Id.val()) {
            player2Id.focus();
            return;
        }
        if(!gameId.val()) {
            gameId.focus();
            return;
        }
        // Send to server
        var values = $(this).serializeArray();
        console.log('Serialized values');
        console.log(values);
        app.joinGame(values).done(function(data) {
            console.log('joined game');
            console.log(data);
            $("#joinGameForm").trigger("reset");
            app.refreshGames();
        })
        .fail(function(xhr, status, error) {
            console.log('join game request failed');
            console.log(xhr);
            console.log(status);
            console.log(error);
            alert('Request failed: ' + status + ' ' + error);
        });
    });
});
