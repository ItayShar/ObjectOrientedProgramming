import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * The BouncingBallAnimation executes a bouncing ball animation.
 * @version 1.00 3 April 2022
 * @author Itay Sharfer
 * @since 03-04-2022
 */
public class BouncingBallAnimation {
    /**
     * @param start - the starting center point of the ball.
     * @param dx - the change in x value for each step.
     * @param dy - the change in y value for each step.
     */
    static private void drawAnimation(Point start, double dx, double dy) {
        final int DIMENSIONS = 200, WAIT = 50;
        GUI gui = new GUI("Bouncing Ball Animation", DIMENSIONS, DIMENSIONS);
        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball(start.getX(), start.getY(), 30, java.awt.Color.BLACK);
        ball.setVelocity(dx, dy);
        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(WAIT);
        }
    }
    /**
     * The main method drives the entire animation process.
     * @param args - an array of string parameters from the command line.
     */
    public static void main(String[] args) {
        double x = Double.parseDouble(args[0]);
        double y = Double.parseDouble(args[1]);
        Point startPoint = new Point(x, y);
        drawAnimation(startPoint, Double.parseDouble(args[2]), Double.parseDouble(args[3]));
    }
}
