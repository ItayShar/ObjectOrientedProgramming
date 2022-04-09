//208005587 Itay Sharfer
import biuoop.DrawSurface;
import java.awt.Color;
/**
 * The Ball class contains attributes and methods used on balls.
 * @version 1.00 3 April 2022
 * @author Itay Sharfer
 * @since 03-04-2022
 */
public class Ball {
    private Velocity vel;
    private Point center;
    private int radius;
    private Color color;

    /**
     * The Ball method is a constructor of a new ball.
     * @param center - the center of the ball.
     * @param r - the radius of the ball.
     * @param color - the color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = new Point(center.getX(), center.getY());
        this.radius = r;
        this.color = color;
    }
    /**
     * The Ball method is a constructor of a new ball.
     * @param x - the desired X-axis coordinate.
     * @param y - the desired Y-axis coordinate.
     * @param r - the radius of the ball.
     * @param color - the color of the ball.
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
    }

    /**
     * The getX method returns the X-axis coordinate of the center of the ball.
     * @return - The X-axis coordinate of the center of the ball.
     */
    public int getX() {
        return (int) this.center.getX();
    }
    /**
     * The getY method returns the Y-axis coordinate of the center of the ball.
     * @return - The Y-axis coordinate of the center of the ball.
     */
    public int getY() {
        return (int) this.center.getY();

    }
    /**
     * The getSize method returns the radius of the ball.
     * @return - the radius of the ball.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * The getSize method returns the color of the ball.
     * @return - the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * The drawOn method draws the ball on a given DrawSurface instance.
     * @param surface - the surface which the circle will be drawn upon.
     */
    public void drawOn(DrawSurface surface) {
        //Choosing the color of the drawing to be the color of the instance.
        surface.setColor(this.getColor());
        //Drawing the ball.
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
    }
    /**
     * The setVelocity method sets a velocity instance to an object.
     * @param v - the velocity wished to be set.
     */
    public void setVelocity(Velocity v) {
        this.vel = new Velocity(v.getDX(), v.getDY());
    }

     /**
     * The setVelocity method sets a velocity instance to an object.
     * @param dx - the x-axis change.
     * @param dy - the y-axis change.
     */
    public void setVelocity(double dx, double dy) {
        this.vel = new Velocity(dx, dy);
    }

    /**
     * The getVelocity method returns the velocity of a ball.
     * @return - the velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.vel;
    }

    /**
     * The moveOneStep method changes the location of a ball by its velocity.
     */
    public void moveOneStep() {
        //we're choosing the dimensions of the window to be 200 because the declaration in the exercise
        // is moveOneStep() and therefore i cannot add the drawsurface in order to get the dimensions.
        final int DIMENTIONS = 200, POSITIVE = 0;
        //Checking if the ball hits the right border.
        if (this.getVelocity().getDX() > POSITIVE) {
            if ((int) this.center.getX() + this.getSize() >= DIMENTIONS) {
                this.switchHorizontal();
            }
        }
        //Checking if the ball hits the left border.
        if (this.getVelocity().getDX() < POSITIVE) {
            if ((int) this.center.getX() - this.getSize() <= 0) {
                this.switchHorizontal();
            }
        }
        //Checking if the ball hits the bottom border.
        if (this.getVelocity().getDY() > POSITIVE) {
            if ((int) this.center.getY() + this.getSize() >= DIMENTIONS) {
                this.switchVertical();
            }
        }
        //Checking if the ball hits the top border.
        if (this.getVelocity().getDY() < POSITIVE) {
            if ((int) this.center.getY() - this.getSize() <= 0) {
                this.switchVertical();
            }
        }
            this.center = this.getVelocity().applyToPoint(this.center);
    }

    private void switchVertical() {
        final int SWITCH_DIRECTION = -1;
        double changeDirection = this.getVelocity().getDY() * SWITCH_DIRECTION;
        double dx = this.getVelocity().getDX();
        this.setVelocity(dx, changeDirection);
    }

    private void switchHorizontal() {
        final int SWITCH_DIRECTION = -1;
        double changeDirection = this.getVelocity().getDX() * SWITCH_DIRECTION;
        double dy = this.getVelocity().getDY();
        this.setVelocity(changeDirection, dy);
    }
}