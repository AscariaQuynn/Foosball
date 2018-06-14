
var app = app || {};

app.playersDataTable = null;
app.gameTablesDataTable = null;
app.gameplaysDataTable = null;

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
    $.get('/rest/player/' + id + '/detail')
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

app.createGameTable = function(values) {
    console.log('create game table request !');
    return $.post('/rest/gameTable', values);
};

app.deleteGameTable = function(id) {
    console.log('delete game table ' + id + ' request !');
    $.ajax({
        url: '/rest/gameTable/' + id,
        type: 'DELETE'
    })
    .done(function(data) {
        app.refreshGameTables();
    })
    .fail(function(xhr, status, error) {
        console.log('deleteGameTable request failed');
        console.log(xhr);
        console.log(status);
        console.log(error);
    });
};

app.gameTableDetail = function(id) {
    console.log('game table detail ' + id + ' request !');
    $.get('/rest/gameTable/' + id)
    .done(function(data) {
        alert(
            'Game Table Detail:\n\n'
            + 'id: ' + data.id + '\n'
            + 'name: ' + data.name + '\n'
        );
    })
    .fail(function(xhr, status, error) {
        console.log('gameTableDetail request failed');
        console.log(xhr);
        console.log(status);
        console.log(error);
    });
}

app.listGameTables = function() {
    console.log('list game tables request !');
    return $.get('/rest/gameTable');
};

app.showGameTables = function(data) {
    var data = data || [];
    // Initialize data table
    if(!app.gameTablesDataTable) {
        console.log('creating new data table');
        app.gameTablesDataTable = $('#gameTablesList').DataTable({
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
                        return '<a class="button" href="javascript:app.gameTableDetail(' + row['id'] + ');">' + 'View' + '</a>'
                        + ' <a class="button" href="javascript:app.deleteGameTable(' + row['id'] + ');">' + 'Delete' + '</a>';
                    }
                }
            ],
            data: data
        });
    } else {
        console.log('data table already exists');
        app.gameTablesDataTable.clear().rows.add(data).draw();
        console.log('retrieved data');
        console.log(data);
    }
};

app.refreshGameTables = function() {
    app.listGameTables().done(function(data) {
        app.showGameTables(data);
    })
    .fail(function(xhr, status, error) {
        console.log('listGameTables request failed');
        console.log(xhr);
        console.log(status);
        console.log(error);
    });
};

app.createGameplay = function(values) {
    console.log('create gameplay request !');
    values[1].value = JSON.parse(values[1].value);
    return $.post('/rest/gameplay/create', values);
};

app.deleteGameplay = function(id) {
    console.log('delete gameplay ' + id + ' request !');
    $.ajax({
        url: '/rest/gameplay/' + id,
        type: 'DELETE'
    })
    .done(function(data) {
        app.refreshGameplays();
    })
    .fail(function(xhr, status, error) {
        console.log('deleteGameplay request failed');
        console.log(xhr);
        console.log(status);
        console.log(error);
    });
};

app.winGameplay = function(id) {
    console.log('win gameplay ' + id + ' request !');
    $.ajax({
        url: '/rest/gameplay/' + id + '/win',
        type: 'POST'
    })
    .done(function(data) {
        app.refreshGameplays();
    })
    .fail(function(xhr, status, error) {
        console.log('winGameplay request failed');console.log(xhr);console.log(status);console.log(error);
    });
};

app.lossGameplay = function(id) {
    console.log('loss gameplay ' + id + ' request !');
    $.ajax({
        url: '/rest/gameplay/' + id + '/loss',
        type: 'POST'
    })
    .done(function(data) {
        app.refreshGameplays();
    })
    .fail(function(xhr, status, error) {
        console.log('lossGameplay request failed');console.log(xhr);console.log(status);console.log(error);
    });
}

app.listGameplays = function() {
    console.log('list gameplays request !');
    return $.get('/rest/gameplay');
};

app.showGameplays = function(data) {
    var data = data || [];
    // Initialize data table
    if(!app.gameplaysDataTable) {
        console.log('creating new data table');
        app.gameplaysDataTable = $('#gameplaysList').DataTable({
            pageLength: 10,
            columns: [
                { data: 'id' },
                { data: 'uuid' },
                { data: 'gameTable.name' },
                { data: 'player.nick' },
                { data: 'status' },
                {
                    data: null,
                    name: 'actions',
                    orderable: false,
                    searchable: false,
                    render: function(data, type, row, meta) {
                        return ''
                            + '<a class="button" href="javascript:app.winGameplay(' + row['id'] + ');">' + 'Win' + '</a>'
                            + '<a class="button" href="javascript:app.lossGameplay(' + row['id'] + ');">' + 'Loss' + '</a>'
                            + '<a class="button" href="javascript:app.deleteGameplay(' + row['id'] + ');">' + 'Delete' + '</a>';
                    }
                }
            ],
            data: data
        });
    } else {
        console.log('data table already exists');
        app.gameplaysDataTable.clear().rows.add(data).draw();
        console.log('retrieved data');
        console.log(data);
    }
};

app.refreshGameplays = function() {
    app.listGameplays().done(function(data) {
        app.showGameplays(data);
    })
    .fail(function(xhr, status, error) {
        console.log('listGameplays request failed');
        console.log(xhr);
        console.log(status);
        console.log(error);
    });
};

$(document).ready(function() {

    app.refreshLoggedIn();
    app.refreshPlayers();
    app.refreshGameTables();
    app.refreshGameplays();

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

    $('#createGameTable').closest('form').on('submit', function(e) {
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
        app.createGameTable(values).done(function(data) {
            console.log('game table created');
            console.log(data);
            $("#createGameTableForm").trigger("reset");
            app.refreshGameTables();
        })
        .fail(function(xhr, status, error) {
            console.log('create game table request failed');
            console.log(xhr);
            console.log(status);
            console.log(error);
            alert('Request failed: ' + status + ' ' + error);
        });
    });

    $('#createGameplay').closest('form').on('submit', function(e) {
        e.preventDefault();
        // Check for empty
        var gameTableId = $(this).find('input[name="gameTableId"]');
        var playerIds = $(this).find('input[name="playerIds"]');
        if(!gameTableId.val()) {
            gameTableId.focus();
            return;
        }
        if(playerIds.val().length < 9) { // cause length 9 is [1,2,3,4]
            playerIds.focus();
            return;
        }
        // Send to server
        var values = $(this).serializeArray();
        console.log('Serialized values');
        console.log(values);
        app.createGameplay(values).done(function(data) {
            console.log('created gameplay');
            console.log(data);
            $("#createGameplayForm").trigger("reset");
            app.refreshGameplays();
        })
        .fail(function(xhr, status, error) {
            console.log('create gameplay request failed');
            console.log(xhr);
            console.log(status);
            console.log(error);
            alert('Request failed: ' + xhr.responseJSON.error);
        });
    });
});
