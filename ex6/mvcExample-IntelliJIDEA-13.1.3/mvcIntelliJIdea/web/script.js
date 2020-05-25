
const tds = []; // instead of a matrix, a liniar array is used
let currentGame;
var init; // disable initialization by overwriting the variable
var winner = false;


function fill(values) {
    console.log(values);
    if (values !== '') {
        const val = values.split(',');
        let cont = 0;
        for (let i = 0; i < 3; i++) {
            for (let j = 0; j < 3; j++) {
                const td = document.getElementById("" + i + j); // to mimic matrix behaviour, we name our elements like "lineNumbercolumnNumber"
                $(td).text(val[cont]);
                cont++;
            }
        }
        console.log(val);
        if (val[cont] === 'none') {
            $('#win').text('');
        } else {
            winner = true;
            if (val[cont] !== 'draw') {
                console.log('winner');
                $('#new').click(function () { // change the listener on the "New Game" button
                    window.location.replace("http://localhost:8080/");
                });
                $('#win').text('Player ' + val[cont] + ' won!');
            } else {
                console.log('draw');
                $('#new').click(function () { // change the listener on the "New Game" button
                    window.location.replace("http://localhost:8080/");
                });
                $('#win').text('Draw!');
            }
        }
    }
}

function refreshTable(game) { // get the information when a new player comes (called by the refresher and init)
    var param = {game: game};
    $.get("table", $.param(param), function (responseText) {
        fill(responseText); // this would render the X and 0 characters when a user clicks on a tile
    });
}

function init(game, player) {
    console.log("init: ", game, player);
    for(let i = 0; i < 3; i++){
        const tr = document.createElement("tr");
        for(let j = 0; j < 3; j++){
            const td = document.createElement("td"); // create the tic tac toe board
            tds.push(td);
            $(td).attr('id', "" + i + j); // set the id-s of the tiles as 'ij' indexes
            $(td).width(200);
            $(td).height(200);
            $(td).prop('align', 'center');
            tr.append(td);
            $(td).click(function () { // add onclick listeners to all the tiles on the board
                if(ready && !winner) {
                    var param = {i: i, j: j, game: game, player: player};
                    console.log(param);
                    $.post("table", $.param(param), function (responseText) {
                        console.log(responseText);
                        fill(responseText);
                    });
                }
            });
        }
        $("#table").append(tr);
        init = true;
    }
    refreshTable(game);
    checkReady();
}

var ready;

function checkReady() { // check if a second user has logged in so that the game can start
    $.get("init", function (responseText) {
        if (responseText === 'ready') {
            console.log(responseText);
            $('#wait').text('Ready');
            ready = true;
        }
    });
}


$(document).ready(function() {
    ready = false;
    const button = document.getElementById("new");
    $(button).click(function () {
        $.post("init", function (resposeText) {
            const resp = resposeText.split(","); // split the (.csv) formatted response into game board and player
            const game = resp[0];
            const player = resp[1];
            console.log("player: " + resposeText);
            init(game, player);

            setInterval(function () { // create a timeout-based refresher
                console.log('refresh');
                if (!ready) {
                    checkReady(); // observer
                }
                refreshTable(game);
            }, 1000);
        });
    });
});