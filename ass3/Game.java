import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * The Game class holds sprites and collidables and generates the game's animation.
 * @version 1.00 10/04/2022
 * @author Itay Sharfer
 * @since 10/04/2022
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;

    /**
     * The Game method is a constructor of a new Game instance.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
    }

    /**
     * The addCollidable method adds a Collidable object to the environment of an instance.
     * @param c - the Collidable to be added.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * The addSprite method adds a Collidable object to the sprites of an instance.
     * @param s - the Sprite to be added.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * The initialize method creates Blocks, Borders and balls objects and adds them to the game instance.
     */
    public void initialize() {
        final int WIDTH = 800, HEIGHT = 600;
        //adding the gui borders to the environment of the instance.
        addBorders(HEIGHT, WIDTH);
        //Creating and adding balls to the game
        addBalls(HEIGHT, WIDTH);
        //Adding blocks to sprites and to the environment of the game instance.
        addBlocks(HEIGHT, WIDTH);
    }


    /**
     * The run method activates the animation loop of the game.
     */
    public void run() {
        GUI gui = new GUI("Game", 800, 600);
        addPaddle(gui);
        Sleeper sleeper = new Sleeper();
        //...
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * The addBorders method creates 4 blocks as the borders of the gui instance.
     * @param height - the height of the gui object of the game instance.
     * @param width  - the width of the gui object of the game instance.
     */
    public void addBorders(int height, int width) {
        final int BORDER_WIDTH = 20, BORDER_HEIGHT = 20;
        //Creating borders of the GUI instance and adding them to environment.
        Block border1 = new Block(new Rectangle(new Point(0, 0), BORDER_WIDTH, height), Color.DARK_GRAY);
        Block border2 = new Block(new Rectangle(new Point(width - BORDER_WIDTH, 0), BORDER_WIDTH, height),
                Color.DARK_GRAY);
        Block border3 = new Block(new Rectangle(new Point(0, 0), width, BORDER_HEIGHT), Color.DARK_GRAY);
        Block border4 = new Block(new Rectangle(new Point(0, height - BORDER_HEIGHT), width, BORDER_HEIGHT),
                Color.DARK_GRAY);
        //Adding all the borders to the GameEnvironment and SpriteCollection of the game instance
        border1.addToGame(this);
        border2.addToGame(this);
        border3.addToGame(this);
        border4.addToGame(this);
    }

    /**
     * The addBlocks method creates blocks and adds them to the SpriteCollection and GameEnvironment of the instance.
     * @param height - the height of the gui object of the game instance.
     * @param width  - the width of the gui object of the game instance.
     */
    public void addBlocks(int height, int width) {
        Color color = Color.YELLOW;
        final int FIRST = 0, SECOND = 1, THIRD = 2, FOURTH = 3, FIFTH = 5, ROW_AMOUNT = 5, DIFF = 2, BLOCK_WIDTH = 80,
                BLOCK_HEIGHT = 20, STARTING_X = 700;
        int xCoordinate = STARTING_X, yCoordinate = 100, rowBlockAmount = 7;
        for (int i = FIRST; i < ROW_AMOUNT; i++) {
            for (int j = FIRST; j <= rowBlockAmount; j++) {
                Point newUpperLeft = new Point(xCoordinate, yCoordinate);
                Rectangle newRectangle = new Rectangle(newUpperLeft, BLOCK_WIDTH, BLOCK_HEIGHT);
                Block newBlock = new Block(newRectangle, color);
                newBlock.addToGame(this);
                xCoordinate -= BLOCK_WIDTH + DIFF;
            }
            //Setting the x and y coordinates for the next row of blocks
            xCoordinate = STARTING_X;
            yCoordinate += BLOCK_HEIGHT + DIFF;
            //lowering the amount of blocks in each row by 1.
            rowBlockAmount--;
            //Setting the block color for the next row of blocks
            if (i == FIRST) {
                color = Color.RED;
            }
            if (i == SECOND) {
                color = Color.BLUE;
            }
            if (i == THIRD) {
                color = Color.GREEN;
            }
            if (i == FOURTH) {
                color = Color.MAGENTA;
            }
        }
    }
    /**
     * The addPaddle method creates a paddle instance and adds it to the game instance.
     * @param gui - the gui object the game is taking place on.
     */
    public void addPaddle(GUI gui) {
        final int MIDDLE = 2, PADDLE_HEIGHT = 20, PADDLE_WIDTH = 100, BORDER_HEIGHT = 20;
        int width = gui.getDrawSurface().getWidth(), height = gui.getDrawSurface().getHeight();
        final Point PADDLE_START_POINT =  new Point(width / MIDDLE, height - PADDLE_HEIGHT - BORDER_HEIGHT);
        //Creating a keyboard sensor.
        KeyboardSensor keyboard = gui.getKeyboardSensor();
        Rectangle startingPaddle = new Rectangle(PADDLE_START_POINT, PADDLE_WIDTH, PADDLE_HEIGHT);
        Paddle paddle = new Paddle(keyboard, startingPaddle);
        paddle.addToGame(this);
    }

    /**
     * The addBalls method creates ball instances and adds it to the game instance.
     * @param height - the height of the gui object of the game instance.
     * @param width  - the width of the gui object of the game instance.
     */
    public void addBalls(int height, int width) {
        final int MIDDLE = 2, RADIUS = 5;
        //Creating a ball at the center of the GUI instance.
        Ball ball1 = new Ball(width / MIDDLE, height / MIDDLE, RADIUS, Color.BLACK, environment);
        ball1.setVelocity(5, -5);
        //Adding the ball to the instance's spriteCollection.
        ball1.addToGame(this);
        Ball ball2 = new Ball(width / MIDDLE + 10, height / MIDDLE, RADIUS, Color.BLACK, environment);
        ball2.setVelocity(7, -3);
        ball2.addToGame(this);
    }
}