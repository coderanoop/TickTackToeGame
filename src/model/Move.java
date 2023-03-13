package model;

public class Move {
    Cell cell;
    Player player;

    public Move(Player player, Cell cell){
        this.player = player;
        this.cell = cell;
    }
}
