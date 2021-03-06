package main;

import java.util.ArrayList;
import java.util.List;

public class GameMemory {
    static List<Game> games = new ArrayList<>(); // entire history of all the games that ever took place

    public static void addGame(Game game) { // add a new game when you start it
        games.add(game);
    }

    private static Game getGame(int idGame) { // get the required game (by id) when a player makes a move
        for(int i = 0; i < games.size(); i++) {
            if(games.get(i).getIdGame() == idGame) {
                return games.get(i);
            }
        }
        return null;
    }

    public static boolean isFree(int idGame, int i, int j) { // verify if the clicked tile is free
        Game game = getGame(idGame);
        return game.getTicTacToe().isFree(i, j);
    }

    public static void player1Move(int idGame, int i, int j) { // write in the game the user's move
        Game game = getGame(idGame);
        if(game != null) {
            game.player1Move(i, j);
        }
    }

    public static void player2Move(int idGame, int i, int j) { // write in the game the user's move
        Game game = getGame(idGame);
        if(game != null) {
            game.player2Move(i, j);
        }
    }

    public static String getValues(int idGame) {
        for (Game g : games) {
            if(g.getIdGame() == idGame) {
                return g.getValues();
            }
        }
        return "";
    }
}
