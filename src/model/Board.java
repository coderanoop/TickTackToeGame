package model;

import java.util.*;

public class Board {
    private List<List<Cell>> cells;

    public Board(int size){
        this.cells = new ArrayList<>();

        for(int i=0; i<size; i++){
            this.cells.add(new ArrayList<>());
            for(int j=0; j<size; j++){
                this.cells.get(i).add(new Cell(i,j));
            }
        }
    }

    public List<List<Cell>> getCells() {
        return cells;
    }

    public void displayBoard(){
        for(int i=0; i<cells.size(); i++){
            System.out.println("--------------");
            System.out.print("|");
            for(int j=0; j<cells.size(); j++){
                if(cells.get(i).get(j).getCellState().equals(CellState.EMPTY)){
                    System.out.print("   |");
                }else if(cells.get(i).get(j).getCellState().equals(CellState.BLOCKED)) {
                    System.out.print("XXX|");
                }else{
                    System.out.print(" " + cells.get(i).get(j).getPlayer().getSymbol() + " |");
                }
            }
            System.out.println();
        }
        System.out.println("--------------");
    }

    public boolean isFull(){
        for(int i=0; i<cells.size(); i++){
            for(int j=0; j<cells.size(); j++){
                if(cells.get(i).get(j).getCellState().equals(CellState.EMPTY)){
                    return false;
                }
            }
        }

        return true;
    }
}
