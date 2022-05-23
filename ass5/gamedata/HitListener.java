//208005587 Itay Sharfer
package gamedata;
import shapes.Ball;

/**
 * The HitListener interface contains methods used by instances that listen to hits in the game.
 */
public interface HitListener {
    /**
     * The hitEvent method is used when the beingHit instance is hit.
     * @param beingHit - the block instance that is being hit.
     * @param hitter - the ball instance that collided with the block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
