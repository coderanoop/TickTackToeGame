import controller.GameController;
import model.*;

import java.util.*;

public class TickTacToeGame {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        GameController gameController = new GameController();
        int boardSize = 0;
        int playerCount = 0;
        while(playerCount < 2 || playerCount > 4){
            System.out.println("Enter the player count (any number from 2 to 4): ");
            playerCount = sc.nextInt();
        }


        boardSize = playerCount + 1;
        int maxBotAllowed = playerCount - 1;


        int botCount = -1;
        while(botCount < 0 || botCount > maxBotAllowed){
            System.out.println("How many bots do you want to add (enter 0 if bot not allowed)? (upto "+maxBotAllowed+" bots allowed): ");
            botCount = sc.nextInt();
        }

        playerCount -= botCount;

        List<Player> players = new ArrayList<>();
        for (int i=1; i <= playerCount; i++){
            System.out.println("Enter player "+i+" name: ");
            String playerName = sc.next();

            System.out.println("Enter player "+i+" symbol (only single char allowed): ");
            String playerSymbol = sc.next();
            players.add(new Player(playerName, playerSymbol.charAt(0), PlayerType.HUMAN));
        }

        for (int i=1; i <= botCount; i++){
            System.out.println("Enter bot "+i+" name: ");
            String botName = sc.next();

            System.out.println("Enter bot "+i+" symbol (only single char allowed): ");
            String botSymbol = sc.next();
            players.add(new Bot(botName, botSymbol.charAt(0), BotDifficultyLevel.EASY));
        }

        Game game = gameController.createGame(boardSize, players);


        while (gameController.getGameStatus(game).equals(GameStatus.ONGOING)){
            System.out.println("This is the current board: ");
            gameController.displayBoard(game);

            System.out.println("Do you want to undo game? (Y/N)");
            String wantUndo = sc.next();

            if(wantUndo.toLowerCase().charAt(0) == 'y'){
                gameController.undoGame(game);
            }else{
                gameController.nextMove(game);
            }
        }

        System.out.println("Game has ended:");
        if(game.getGameStatus().equals(GameStatus.DRAW)){
            System.out.println("Game is DRAW");
        }else{
            System.out.println(game.getWinner().getName() + " won the game!");
        }
    }
}
