package main;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@WebServlet(name = "InitiateGame", urlPatterns = {"/init"})
public class InitiateGame extends Servlet{
    int currentPlayer = 0; // global player counter
    int currentGameId = 1; // global game counter
    Game currentGame;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String resp = "";
        if(currentPlayer % 2 == 1) { // every second player triggers the game to start (also, notification
            resp = "wait";
        } else {
            resp = "ready";
        }
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        currentPlayer++;
        String resp = "" + currentGameId + "," + currentPlayer;
        if (currentPlayer % 2 == 1) { // odd player will have to wait (second player is not existent)
            currentGame = new Game(currentGameId, currentPlayer, -1);
        } else {
            currentGame.setPlayer2(currentPlayer);
            GameMemory.addGame(currentGame);
            currentGameId++;
        }

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(resp);
    }
}
