//208005587 Itay Sharfer
/**
 * The Ass3Game class creates a game object and plays the game animation.
 */
public class Ass3Game {
    /**
     * The main method drives the entire game creation process.
     * @param args - a String array of command line arguments.
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}
