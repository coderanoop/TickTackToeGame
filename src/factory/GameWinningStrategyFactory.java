package factory;

import model.BotDifficultyLevel;
import strategy.botPlayingStrategy.BotPlayingStrategy;
import strategy.botPlayingStrategy.EasyBotPlayingStrategy;
import strategy.botPlayingStrategy.HardBotPlayingStrategy;
import strategy.botPlayingStrategy.MediumBotPlayingStrategy;
import strategy.gameWinningStrategy.GameWinningStrategy;
import strategy.gameWinningStrategy.SimpleGameWinningStrategy;

public class GameWinningStrategyFactory {
    public static GameWinningStrategy getGameWinningStrategy(){
        return new SimpleGameWinningStrategy();
    }
}
