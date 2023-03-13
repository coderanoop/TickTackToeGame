package factory;

import model.BotDifficultyLevel;
import strategy.botPlayingStrategy.BotPlayingStrategy;
import strategy.botPlayingStrategy.EasyBotPlayingStrategy;
import strategy.botPlayingStrategy.HardBotPlayingStrategy;
import strategy.botPlayingStrategy.MediumBotPlayingStrategy;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getPlayingStrategy(BotDifficultyLevel difficultyLevel){
        return switch (difficultyLevel){
            case MEDIUM -> new MediumBotPlayingStrategy();
            case HARD -> new HardBotPlayingStrategy();
            default -> new EasyBotPlayingStrategy();
        };
    }
}
