package controller;

import model.Board;
import model.Game;
import model.GameStatus;
import model.Player;

import java.util.List;

public class GameController {
    public Game createGame(int boardSize, List<Player> players) throws Exception {
        Game.GameBuilder gameBuilder = Game.getGameBuilder();
        gameBuilder.setBoard(new Board(boardSize));
        gameBuilder.setPlayers(players);
        test

        return gameBuilder.build();
    }

    public GameStatus getGameStatus(Game game){
        return game.getGameStatus();
    }

    public void displayBoard(Game game){
        game.getBoard().displayBoard();
    }

    public void undoGame(Game game){

    }

    public void nextMove(Game game){
        game.makeNextMove();
    }
}
