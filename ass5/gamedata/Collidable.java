package gamedata;
import shapes.Point;
import shapes.Rectangle;
import shapes.Velocity;
import shapes.Ball;

/**
 * The Collidable interface is used by objects that can be collided with.
 */
public interface Collidable {
    /**
     *The getCollisionRectangle method returns the collision rectangle.
     * @return - the rectangle object that has been collided.
     */
    Rectangle getCollisionRectangle();

    /**
     * The hit method notifies an object it has been collided and calculates the new velocity expected after the hit.
     * @param collisionPoint - the point of collision.
     * @param currentVelocity - the velocity before the collision.
     * @param hitter - the ball that was collided with the Collidable object.
     * @return - the new velocity after the collision.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
