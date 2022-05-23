//208005587 Itay Sharfer
import gamedata.Game;
/**
 * The Ass5Game class is used to test and show the game in assignment 5.
 */
public class Ass5Game {
    /**
     * The main method is used to drive the test process.
     * @param args - a string array of cmd argumants.
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}
