package strategy.gameWinningStrategy;

import model.*;

import java.util.Map;
import java.util.HashMap;

public class SimpleGameWinningStrategy implements GameWinningStrategy{
    @Override
    public boolean checkWinning(Board board, Player player) {
        return this.checkRowColWise(board, player) || this.checkDiagonalWise(board, player);
    }

    private boolean checkRowColWise(Board board, Player player){
        Map<Integer, Map<Character, Integer>> rowMap = new HashMap<>();
        Map<Integer, Map<Character, Integer>> colMap = new HashMap<>();
        for(int i=0; i<board.getCells().size(); i++){
            rowMap.put(i, new HashMap<>());
            for(int j=0; j<board.getCells().size(); j++){
                colMap.put(j, new HashMap<>());
                Cell cell = board.getCells().get(i).get(j);
                if(cell.getCellState().equals(CellState.FILLED)){
                    Player cellPlayer = cell.getPlayer();
                    int rowWiseCount = rowMap.get(i).getOrDefault(cellPlayer.getSymbol(), 0) + 1;
                    int collWiseCount = colMap.get(i).getOrDefault(cellPlayer.getSymbol(), 0) + 1;
                    rowMap.get(i).put(cellPlayer.getSymbol(), rowWiseCount);
                    colMap.get(i).put(cellPlayer.getSymbol(), collWiseCount);

                    if(
                            rowMap.get(i).getOrDefault(player.getSymbol(), 0) == board.getCells().size()
                                    || colMap.get(j).getOrDefault(player.getSymbol(), 0) == board.getCells().size()
                    ){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkDiagonalWise(Board board, Player player){
        Map<Character, Integer> d1Map = new HashMap<>();
        Map<Character, Integer> d2Map = new HashMap<>();
        for(int i=0; i<board.getCells().size(); i++){
            Cell cell = board.getCells().get(i).get(i);
            if(cell.getCellState().equals(CellState.FILLED)){
                Player cellPlayer = cell.getPlayer();
                int count = d1Map.getOrDefault(cellPlayer.getSymbol(), 0) + 1;
                d1Map.put(cellPlayer.getSymbol(), count);

                if(d1Map.getOrDefault(player.getSymbol(), 0) == board.getCells().size()){
                    return true;
                }
            }
        }

        for(int i=0; i<board.getCells().size(); i++){
            Cell cell = board.getCells().get(i).get(board.getCells().size()-i-1);
            if(cell.getCellState().equals(CellState.FILLED)){
                Player cellPlayer = cell.getPlayer();
                int count = d2Map.getOrDefault(cellPlayer.getSymbol(), 0) + 1;
                d2Map.put(cellPlayer.getSymbol(), count);

                if(d2Map.getOrDefault(player.getSymbol(), 0) == board.getCells().size()){
                    return true;
                }
            }
        }

        return  false;
    }
}
