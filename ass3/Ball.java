//208005587 Itay Sharfer
import biuoop.DrawSurface;
import java.awt.Color;
/**
 * The Ball class contains attributes and methods used on ball objects.
 * @version 1.01 10 April 2022
 * @author Itay Sharfer
 * @since 03-04-2022
 */
public class Ball implements Sprite {
    private Velocity vel;
    private Point center;
    private int radius;
    private Color color;
    private GameEnvironment env;

    /**
     * The Ball method is a constructor of a new ball.
     * @param center - the center of the ball.
     * @param r - the radius of the ball.
     * @param color - the color of the ball.
     * @param env - the GameEnvironment object the ball is in.
     */
    public Ball(Point center, int r, Color color, GameEnvironment env) {
        this.center = new Point(center.getX(), center.getY());
        this.radius = r;
        this.color = color;
        this.env = env;
    }
    /**
     * The Ball method is a constructor of a new ball.
     * @param x - the desired X-axis coordinate.
     * @param y - the desired Y-axis coordinate.
     * @param r - the radius of the ball.
     * @param color - the color of the ball.
     * @param env - the GameEnvironment object the ball is in.
     */
    public Ball(double x, double y, int r, Color color, GameEnvironment env) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.env = env;
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
    public Color getColor() {
        return this.color;
    }

    /**
     * The getEnv method is an access method to the member env.
     * @return - the instance's env.
     */
    public GameEnvironment getEnv() {
        return this.env;
    }

    /**
     * The setEnv method is a setter method for the member env.
     * @param env - the GameEnvironment object we wish to set.
     */
    public void setEnv(GameEnvironment env) {
        this.env = env;
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
     * The timePassed method notifies the ball object that certain amount of time has passed.
     */
    public void timePassed() {
        this.moveOneStep();
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
        Velocity v;
        CollisionInfo collision = null;
        final int DIFFERENCE = 1;
        Line trajectory = this.computeTrajectory();
        //If there's no collision, the velocity is applied
        if (this.getEnv().getClosestCollision(trajectory) == null) {
            this.center = this.getVelocity().applyToPoint(this.center);

            //In case there's a collision
        } else {
            collision = this.getEnv().getClosestCollision(trajectory);
            //Moving the ball almost to the hit point.
            this.center = new Point(collision.collisionPoint().getX() - DIFFERENCE,
                    collision.collisionPoint().getY() - DIFFERENCE);
            //Notifying the hit object that a collision has occurred and changing the ball velocity.
            v = collision.collisionObject().hit(collision.collisionPoint(), this.getVelocity());
            this.setVelocity(v);
            this.center = this.getVelocity().applyToPoint(this.center);
        }
    }

    /**
     * The computeTrajectory method computes the trajectory a ball moves in.
     * @return - a line object represents the trajectory the ball moves in.
     */
    public Line computeTrajectory() {
        return new Line(this.getX(), this.getY(), this.getX() + this.getVelocity().getDX(),
                this.getY() + this.getVelocity().getDY());
    }

    /**
     * The addToGame method adds the ball instance to the Sprite collection of a game instance.
     * @param g - a game instance
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }
}