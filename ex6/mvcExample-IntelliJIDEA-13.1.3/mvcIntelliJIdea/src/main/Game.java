package main;


public class Game {
    private int idGame;
    private int player1;
    private int player2;
    private TicTacToe ticTacToe;
    private int currentPlayer;

    public Game(int idGame, int player1, int player2) {
        this.idGame = idGame;
        this.player1 = player1;
        this.player2 = player2;
        this.ticTacToe = new TicTacToe(); // create new game engine
        this.currentPlayer = player1;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    private boolean isMyTurn(int player) {
        return currentPlayer == player;
    }

    public int getIdGame() {
        return idGame;
    }

    public void setIdGame(int idGame) {
        this.idGame = idGame;
    }

    public int getPlayer1() {
        return player1;
    }

    public void setPlayer1(int player1) {
        this.player1 = player1;
    }

    public int getPlayer2() {
        return player2;
    }

    public void setPlayer2(int player2) {
        this.player2 = player2;
    }

    public TicTacToe getTicTacToe() {
        return ticTacToe;
    }

    public void setTicTacToe(TicTacToe ticTacToe) {
        this.ticTacToe = ticTacToe;
    }

    public void player1Move(int i, int j) { // the first player is the first one that accesses the game lobby and they will play as "X"
        if(isMyTurn(player1)) {
            ticTacToe.setValue(i, j, "X");
            currentPlayer = player2;
        }
    }

    public void player2Move(int i, int j) { // the second player is the second one that accesses the game lobby and they will play as "0"
        if(isMyTurn(player2)) {
            ticTacToe.setValue(i, j, "0");
            currentPlayer = player1;
        }
    }

    public String getValues() { // check if game ended
        String verify = ticTacToe.verifyWinner(); // the function returns the symbol to identify the winner
        if(verify.equals("X") || verify.equals("0")) {
            return ticTacToe.getValues() + "," + verify;
        }
        if(ticTacToe.verifyDraw()) { // draw. no winner
            return ticTacToe.getValues() + "," + "draw";
        }
        return ticTacToe.getValues() + "," + "none";
    }
}
