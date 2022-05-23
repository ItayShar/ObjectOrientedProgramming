//208005587 Itay Sharfer
package gamedata;
import shapes.Ball;
import shapes.Rectangle;
import shapes.Velocity;
import shapes.Point;
import shapes.Line;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * The paddle class contains attributes and methods used on a paddle instance.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle paddleRec;

    /**
     * The paddle method is a constructor of a new Paddle isntance.
     * @param keyboard - A keyboardSensor instance that will be used to control the paddle.
     * @param paddleRec - A Rectangle object that will be the starting position of the paddle.
     */
    public Paddle(KeyboardSensor keyboard, Rectangle paddleRec) {
        this.keyboard = keyboard;
        this.paddleRec = paddleRec;
    }

    /**
     * The moveLeft method moves the paddle to the left side.
     */
    public void moveLeft() {
        final int move = -10;
            Point newUpperLeft = this.getCollisionRectangle().getUpperLeft();
            newUpperLeft.setX(newUpperLeft.getX() + move);
            this.getCollisionRectangle().setUpperLeft(newUpperLeft);
    }

    /**
     * The moveRight method moves the paddle to the right side.
     */
    public void moveRight() {
        final int move = 10;
        Point newUpperLeft = this.getCollisionRectangle().getUpperLeft();
        newUpperLeft.setX(newUpperLeft.getX() + move);
        this.getCollisionRectangle().setUpperLeft(newUpperLeft);
    }

    /**
     * The timePassed method checks if a keyboard button was pressed and executes a corresponding move method.
     */
    public void timePassed() {
        //making sure the paddle will not leave the screen after the movement before executing it.
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY) && this.paddleRec.getUpperLeft().getX() - 20 > 0) {
            this.moveLeft();
        }
        //making sure the paddle will not leave the screen after the movement before executing it.
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY) && this.paddleRec.getUpperRight().getX() + 20 < 800) {
            this.moveRight();
        }
    }

    /**
     * The drawOn method draws the paddle instance on a DrawSurface instance.
     * @param d -  a DrawSurface instance the paddle instance will be drawn upon.
     */
    public void drawOn(DrawSurface d) {
        int x = (int) this.getCollisionRectangle().getUpperLeft().getX(),
                y = (int) this.getCollisionRectangle().getUpperLeft().getY();
        int width = (int) this.getCollisionRectangle().getWidth(),
                height = (int) this.getCollisionRectangle().getHeight();
        d.setColor(Color.BLACK);
        d.fillRectangle(x, y, width, height);
    }

    /**
     * The getCollisionRectangle method is an access method to the instance's paddleRec.
     * @return - The instance's paddleRec.
     */
    public Rectangle getCollisionRectangle() {
        return this.paddleRec;
    }

    /**
     * The hit method calculates a velocity after an impact with the paddle instance.
     * @param collisionPoint - the point of collision.
     * @param currentVelocity - the velocity before the collision.
     * @param hitter - the ball that has collided with the paddle instance.
     * @return - the velocity after the collision.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        final int changeDirection = -1;
        Line[] paddleLines = this.getCollisionRectangle().generateRectangleLines();
        for (Line l : paddleLines) {
            if (l.isContaining(collisionPoint)) {
                //If the line that was hit is vertical, the DX pf currentVelocity should change.
                if (l.isVertical()) {
                    return new Velocity(currentVelocity.getDX() * changeDirection, currentVelocity.getDY());
                    //If the line is not vertical, it must be horizontal and therefore the DY of currentVelocity should
                    // be changed.
                } else {
                    return new Velocity(currentVelocity.getDX(), currentVelocity.getDY() * changeDirection);
                }
            }
        }
        //If no lines have been hit, velocity should not change.
        return currentVelocity;
    }

    /**
     * The addToGame method adds the Paddle instance to a game instance's SpriteCollection and GameEnvironment.
     * @param g - a game instance
     */
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}