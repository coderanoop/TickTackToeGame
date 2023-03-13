package model;

import factory.BotPlayingStrategyFactory;
import strategy.botPlayingStrategy.BotPlayingStrategy;

public class Bot extends Player{
    private BotDifficultyLevel botDifficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;

    public Bot(String name, char symbol, BotDifficultyLevel difficultyLevel) {
        super(name, symbol, PlayerType.BOT);
        this.botDifficultyLevel = difficultyLevel;
        this.botPlayingStrategy = BotPlayingStrategyFactory.getPlayingStrategy(this.botDifficultyLevel);
    }

    @Override
    Move decideMove(Board board) {
        return this.botPlayingStrategy.decideMove(this, board);
    }
}
