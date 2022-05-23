package gamedata;
import shapes.Ball;
import shapes.Point;
import shapes.Rectangle;
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
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter scoreTrackingListener;

    /**
     * The Game method constructs a new Game instance.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.remainingBlocks = new Counter();
        this.remainingBalls = new Counter();
        this.scoreTrackingListener = new Counter();
    }
    /**
     * The addCollidable method adds a Collidable object to the environment of an instance.
     * @param c - the Collidable to be added.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * The getRemainingBlocks method is an access method to the remainingBlocks member of the instance.
     * @return - the remainingBlocks member of the instance.
     */
    public Counter getRemainingBlocks() {
        return this.remainingBlocks;
    }

    /**
     * The getRemainingBalls method is an access method to the remainingBalls member of the instance.
     * @return - the remainingBalls member of the instance.
     */
    public Counter getRemainingBalls() {
        return this.remainingBalls;
    }
    /**
     * The getRemainingBalls method is an access method to the scoreTrackingListener member of the instance.
     * @return - the scoreTrackingListener member of the instance.
     */
    public Counter getScoreTrackingListener() {
        return this.scoreTrackingListener;
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
        BlockRemover blockRemover = new BlockRemover(this, this.remainingBlocks);
        BallRemover ballRemover = new BallRemover(this, this.remainingBalls);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.scoreTrackingListener);
        final int width = 800, height = 600;
        addScoreIndicator();
        //adding the gui borders to the environment of the instance.
        addBorders(height, width, ballRemover);
        //Creating and adding balls to the game
        addBalls(height, width);
        //Adding blocks to sprites and to the environment of the game instance.
        addBlocks(blockRemover, scoreTrackingListener);
    }

    /**
     * The addScoreIndicator method adds a score indicator to the game instance.
     */
    public void addScoreIndicator() {
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.scoreTrackingListener);
        ScoreIndicator scoreIndicator = new ScoreIndicator(this, this.scoreTrackingListener);
        scoreIndicator.addToGame(this);
    }


    /**
     * The run method activates the animation loop of the game.
     */
    public void run() {
        final int noBlocks = 0, noBalls = 0;
        GUI gui = new GUI("Game", 800, 600);
        addPaddle(gui);
        Sleeper sleeper = new Sleeper();
        BlockRemover blockRemover = new BlockRemover(this, this.remainingBlocks);
        BallRemover ballRemover = new BallRemover(this, this.remainingBalls);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.scoreTrackingListener);
        ScoreIndicator scoreIndicator = new ScoreIndicator(this, this.scoreTrackingListener);
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
            blockRemover.setRemainingBlocks(this.remainingBlocks);
            ballRemover.setRemainingBalls(this.remainingBalls);
            scoreTrackingListener.setScore(this.scoreTrackingListener);
            //Checking if there are remaining blocks and balls in the game.
            if (blockRemover.getRemainingBlocks().getValue() == noBlocks
                || ballRemover.getRemainingBalls().getValue() == noBalls) {
                if (blockRemover.getRemainingBlocks().getValue() == noBlocks) {
                    scoreTrackingListener.addWinBonus();
                }
                gui.close();
                return;
            }
        }
    }

    /**
     * The addBorders method creates 4 blocks as the borders of the gui instance.
     * @param height - the height of the gui object of the game instance.
     * @param width  - the width of the gui object of the game instance.
     * @param hl - a HitListener object that will keep count of the number of balls in the game.
     */
    public void addBorders(int height, int width, HitListener hl) {
        final int borderWidth = 20, borderHeight = 20, deathRegionHeight = 1;
        //Creating borders of the GUI instance and adding them to environment.
        Block leftBorder = new Block(new Rectangle(new Point(0, 40), borderWidth, height), Color.DARK_GRAY);
        Block rightBorder = new Block(new Rectangle(new Point(width - borderWidth, 40), borderWidth, height),
                Color.DARK_GRAY);
        Block upBorder = new Block(new Rectangle(new Point(0, 20), width, borderHeight), Color.DARK_GRAY);
        Block deathRegion = new Block(new Rectangle(new Point(0, height), width, deathRegionHeight),
                Color.DARK_GRAY);
        deathRegion.addHitListener(hl);
        //Adding all the borders to the GameEnvironment and SpriteCollection of the game instance
        leftBorder.addToGame(this);
        rightBorder.addToGame(this);
        upBorder.addToGame(this);
        deathRegion.addToGame(this);
    }

    /**
     * The addBlocks method creates blocks and adds them to the SpriteCollection and GameEnvironment of the instance.
     * It also increases the instance's remainingBlocks counter for each added block.
     * @param blocksCounter - a HitListener object that will keep count of the number of blocks in the game.
     * @param scoreTracker - a HitListener object that will keep count of the score throughout the gameplay.
     */
    public void addBlocks(HitListener blocksCounter, HitListener scoreTracker) {
        final int increaseBlockNumber = 1;
        Color color = Color.YELLOW;
        final int first = 0, second = 1, third = 2, fourth = 3, rowAmount = 6, diff = 2, blockWidth = 80,
                blockHeight = 20, startingX = 700;
        int xCoordinate = startingX, yCoordinate = 100, rowBlockAmount = 7;
        for (int i = first; i < rowAmount; i++) {
            for (int j = first; j <= rowBlockAmount; j++) {
                Point newUpperLeft = new Point(xCoordinate, yCoordinate);
                Rectangle newRectangle = new Rectangle(newUpperLeft, blockWidth, blockHeight);
                Block newBlock = new Block(newRectangle, color);
                newBlock.addHitListener(blocksCounter);
                newBlock.addHitListener(scoreTracker);
                newBlock.addToGame(this);
                this.remainingBlocks.increase(increaseBlockNumber);
                xCoordinate -= blockWidth + diff;
            }
            //Setting the x and y coordinates for the next row of blocks
            xCoordinate = startingX;
            yCoordinate += blockHeight + diff;
            //lowering the amount of blocks in each row by 1.
            rowBlockAmount--;
            //Setting the block color for the next row of blocks
            if (i == first) {
                color = Color.RED;
            }
            if (i == second) {
                color = Color.BLUE;
            }
            if (i == third) {
                color = Color.GREEN;
            }
            if (i == fourth) {
                color = Color.MAGENTA;
            }
        }
    }
    /**
     * The addPaddle method creates a paddle instance and adds it to the game instance.
     * @param gui - the gui object the game is taking place on.
     */
    public void addPaddle(GUI gui) {
        final int middle = 2, paddleHeight = 20, paddleWidth = 100, borderHeight = 20;
        int width = gui.getDrawSurface().getWidth(), height = gui.getDrawSurface().getHeight();
        final Point paddleStartPoint =  new Point(width / middle, height - paddleHeight - borderHeight);
        //Creating a keyboard sensor.
        KeyboardSensor keyboard = gui.getKeyboardSensor();
        Rectangle startingPaddle = new Rectangle(paddleStartPoint, paddleWidth, paddleHeight);
        Paddle paddle = new Paddle(keyboard, startingPaddle);
        paddle.addToGame(this);
    }

    /**
     * The addBalls method creates ball instances and adds it to the game instance.
     * @param height - the height of the gui object of the game instance.
     * @param width  - the width of the gui object of the game instance.
     */
    public void addBalls(int height, int width) {
        final int middle = 2, radius = 5;
        //Creating a ball at the center of the GUI instance.
        Ball ball1 = new Ball(width / middle, height / middle, radius, Color.BLACK, environment);
        ball1.setVelocity(5, -5);
        //Adding the ball to the instance's spriteCollection.
        ball1.addToGame(this);
        Ball ball2 = new Ball(width / middle + 10, height / middle, radius, Color.BLACK, environment);
        ball2.setVelocity(7, -3);
        ball2.addToGame(this);
        Ball ball3 = new Ball(width / middle + 20, height / middle, radius, Color.BLACK, environment);
        ball3.setVelocity(3, 7);
        ball3.addToGame(this);
    }

    /**
     * The removeCollidable method removes a collidable object from the environment of the instance.
     * @param c - the collidable object that will be removed.
     */
    public void removeCollidable(Collidable c) {
        this.environment.getCollList().remove(c);
    }

    /**
     * The removeSprtie method removes a given sprite from the sprites list of the instance.
     * @param s - the sprite object that will be removed.
     */
    public void removeSprite(Sprite s) {
        this.sprites.getSpriteList().remove(s);
    }

    /**
     * The getNumberOfBlocks returns the amount of blocks (excluding borders) that are in the game instance.
     * @return - the amount of blocks that are in the game instance.
     */
    public int getNumberOfBlocks() {
        final int numberOfBorders = 4, numberOfBalls = 2;
        return this.environment.getCollList().size() - numberOfBorders - numberOfBalls;
    }

    /**
     * The removeBall method removes a ball instance from the game's environment member's CollList.
     * @param ball - the ball that will be removed.
     */
    public void removeBall(Ball ball) {
        this.environment.getCollList().remove(ball);
    }
}
