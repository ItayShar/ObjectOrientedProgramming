//208005587 Itay Sharfer
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.util.Random;
/**
 * The MultipleBouncingBallsAnimation class generates a window with multiple bouncing ball animations.
 * @version 1.00 3 April 2022
 * @author Itay Sharfer
 * @since 03-04-2022
 */
public class MultipleBouncingBallsAnimation {
    /**
     * The generateRandomPoint method creates a point with random coordinates.
     * @return a point with random coordinates.
     */
    public Point generateRandomPoint() {
        final int START_OF_ARRAY = 0, DIMENSIONS = 200;
        Random rand = new Random();
        return new Point(rand.nextDouble(DIMENSIONS) + 1, rand.nextDouble(DIMENSIONS + 1));
    }

    /**
     * The ballArrayGenerator method generates an array of balls with random center point.
     * @param strArray - an array of strings.
     * @return an array of ball instances.
     */
    public Ball[] ballArrayGenerator(String[] strArray) {
        Ball[] ballArr = new Ball[strArray.length];
        final int START_OF_ARRAY = 0;
        int arg;
        //Creating a ball array with random centers and giving each ball a radius from of the args array.
        for (int i = START_OF_ARRAY; i < strArray.length; i++) {
            arg = Integer.parseInt(strArray[i]);
            ballArr[i] = new Ball(generateRandomPoint(), arg, java.awt.Color.BLACK);
        }
        //Sorting the ball array by size.
        sortBallArrBySize(ballArr);
        //Generating velocities for each ball in the array. based by size;
        generateVelocities(ballArr);
        return ballArr;
    }

    /**
     * The sortBallArrBySize method sorts an array of ball instances based on their size by a descending order.
     * @param ballArr - an array of ball instances
     */
    public void sortBallArrBySize(Ball[] ballArr) {
        Ball temp;
        final int START_OF_ARRAY = 0;
        for (int i = START_OF_ARRAY; i < ballArr.length; i++) {
            for (int j = START_OF_ARRAY; j < ballArr.length - i - 1; j++) {
                if (ballArr[j].getSize() < ballArr[j + 1].getSize()) {
                  temp = ballArr[j];
                    ballArr[j] = ballArr[j + 1];
                    ballArr[j + 1] = temp;
                }
            }
        }
    }

    /**
     * The generateVelocities method generates different velocities to ball instances in an array based on size.
     * @param ballArr - an array of ball instances
     */
    public void generateVelocities(Ball[] ballArr) {
        final int START_OF_ARRAY = 0, BIG_BALL = 50, INCREASE_VELOCITY = 2;
        final double MINIMUM_VELOCITY = 2;
        double changingVelocity = 3;
        for (int i = START_OF_ARRAY; i < ballArr.length; i++) {
            if (i == 0) {
                ballArr[i].setVelocity(MINIMUM_VELOCITY, MINIMUM_VELOCITY);
                continue;
            }
            //All  balls with radius greater than 50 move in the same speed. I chose it to be 1.
            if (ballArr[i].getSize() >= BIG_BALL) {
                ballArr[i].setVelocity(MINIMUM_VELOCITY, MINIMUM_VELOCITY);
            }
            //If the ball is smaller than it's prior on the array, it's velocity is greater.
            if (ballArr[i].getSize() < ballArr[i - 1].getSize()) {
                changingVelocity += INCREASE_VELOCITY;
            }
            ballArr[i].setVelocity(changingVelocity, changingVelocity);
        }
    }

    /**
     * The drawMultipleBouncingBalls method generates a window with the multiple balls bouncing animation.
     * @param ballArr - an array of ball instances.
     */
    public void drawMultipleBouncingBalls(Ball[] ballArr) {
        final int START_OF_ARRAY = 0, WAIT = 50, DIMENSIONS = 200;
        GUI gui = new GUI("Multiple Bouncing Balls Animation", DIMENSIONS, DIMENSIONS);
        Sleeper sleeper = new Sleeper();
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            for (int i = START_OF_ARRAY; i < ballArr.length; i++) {
                ballArr[i].moveOneStep();
                ballArr[i].drawOn(d);
            }
                gui.show(d);
                sleeper.sleepFor(WAIT);  // wait for 50 milliseconds.
            }

    }

    /**
     * The main method drives the entire multiple bouncing balls process.
     * @param args - an array of string parameters from the command line.
     */
    public static void main(String[] args) {
        MultipleBouncingBallsAnimation d = new MultipleBouncingBallsAnimation();
        Ball[] ballArr = d.ballArrayGenerator(args);
        d.drawMultipleBouncingBalls(ballArr);
    }
}
