package model;

import factory.GameWinningStrategyFactory;
import strategy.gameWinningStrategy.GameWinningStrategy;

import java.util.*;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private Player winner;
    private GameStatus gameStatus;
    private int nextPlayerIdx;
    private GameWinningStrategy gameWinningStrategy;

    private Game(){

    }

    public static GameBuilder getGameBuilder(){
        return new Game.GameBuilder();
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getNextPlayerIdx() {
        return nextPlayerIdx;
    }

    public void setNextPlayerIdx(int nextPlayerIdx) {
        this.nextPlayerIdx = nextPlayerIdx;
    }

    public GameWinningStrategy getGameWinningStrategy() {
        return gameWinningStrategy;
    }

    public void setGameWinningStrategy(GameWinningStrategy gameWinningStrategy) {
        this.gameWinningStrategy = gameWinningStrategy;
    }

    public Player getNextPlayer(){
        return this.getPlayers().get(this.getNextPlayerIdx());
    }

    public void makeNextMove(){
        Player player = this.getNextPlayer();
        Move move = player.decideMove(this.board);
        int row = move.cell.getRow();
        int coll = move.cell.getCol();
        Cell cell = this.board.getCells().get(row).get(coll);
        cell.setCellState(CellState.FILLED);
        cell.setPlayer(player);

        Move finalMove = new Move(player, cell);
        this.moves.add(finalMove);

        if(this.getGameWinningStrategy().checkWinning(this.getBoard(), player)){
            this.setWinner(player);
            this.setGameStatus(GameStatus.END);
        }else if(this.getBoard().isFull()) {
            this.setGameStatus(GameStatus.DRAW);
        }

        this.setNextPlayerIdx((this.getNextPlayerIdx() + 1) % this.getPlayers().size());
    }

    public static class GameBuilder{
        private Board board;
        private List<Player> players;
        private List<Move> moves;
        private Player winner;
        private int nextPlayerIdx;
        private GameWinningStrategy gameWinningStrategy;

        private GameBuilder(){
            this.setGameWinningStrategy(GameWinningStrategyFactory.getGameWinningStrategy());
            this.setNextPlayerIdx(0);
            this.setMoves(new ArrayList<>());
            this.setPlayers(new ArrayList<>());
        }

        public Game build() throws Exception {
            if(!this.validate()){
                throw new Exception("invalid game data");
            }

            Game game = new Game();
            game.setBoard(board);
            game.setPlayers(players);
            game.setMoves(moves);
            game.setNextPlayerIdx(nextPlayerIdx);
            game.setGameStatus(GameStatus.ONGOING);
            game.setGameWinningStrategy(gameWinningStrategy);

            return game;
        }

        private boolean validate() throws Exception {
            if(this.board.equals(null)){
                throw new Exception("Game board can not be null!");
            }

            if(this.board.getCells().size() - 1 != this.players.size()){
                throw new Exception("Invalid number of players!");
            }

            return true;
        }

        public GameBuilder setBoard(Board board) {
            this.board = board;
            return this;
        }

        public GameBuilder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public GameBuilder setMoves(List<Move> moves) {
            this.moves = moves;
            return this;
        }

        public GameBuilder setWinner(Player winner) {
            this.winner = winner;
            return this;
        }

        public GameBuilder setNextPlayerIdx(int nextPlayerIdx) {
            this.nextPlayerIdx = nextPlayerIdx;
            return this;
        }

        public GameBuilder setGameWinningStrategy(GameWinningStrategy gameWinningStrategy) {
            this.gameWinningStrategy = gameWinningStrategy;
            return this;
        }
    }
}
