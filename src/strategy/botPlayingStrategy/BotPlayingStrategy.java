package strategy.botPlayingStrategy;

import model.Board;
import model.Move;
import model.Player;

public interface BotPlayingStrategy {
    public Move decideMove(Player player, Board board);
}
