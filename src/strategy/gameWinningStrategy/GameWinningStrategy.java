package strategy.gameWinningStrategy;

import model.Board;
import model.Player;

public interface GameWinningStrategy {
    public boolean checkWinning(Board board, Player player);
}
