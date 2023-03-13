package model;

import java.util.Scanner;

public class Player {
    private String name;
    private char symbol;
    private PlayerType type;

    public Player(String name, char symbol, PlayerType type){
        this.name = name;
        this.symbol = symbol;
        this.type = type;
    }

    Move decideMove(Board board){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the row for move: ");
        int row = sc.nextInt();

        System.out.println("please enter the col for move: ");
        int col = sc.nextInt();

        Cell cell = new Cell(row, col);
        return new Move(this, cell);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public PlayerType getType() {
        return type;
    }

    public void setType(PlayerType type) {
        this.type = type;
    }
}
