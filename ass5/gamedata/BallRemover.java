//208005587 Itay Sharfer
package gamedata;
import shapes.Ball;

/**
 *
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;

    /**
     * The BallRemover method constructs a new BallRemover instance.
     * @param game - the game instance that the BallRemover will be removing blocks from.
     * @param remainingBalls - the amount of remaining balls.
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * The setRemainingBalls method sets the remainingBalls counter to a given value.
     * @param remainingBalls - the value that the counter will be set to.
     */
    public void setRemainingBalls(Counter remainingBalls) {
        this.remainingBalls = remainingBalls;
    }

    /**
     * The getRemainingBalls method is an access method to the remainingBalls counter of an instance.
     * @return - The remainingBalls counter of an instance.
     */
    public Counter getRemainingBalls() {
        return this.remainingBalls;
    }

    /**
     * The hitEvent method removes a ball that got to death region from the game and decreases the remainingBalls
     * counter by 1.
     * @param beingHit - the block instance that is being hit.
     * @param hitter - the ball instance that collided with the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        final int decreaseBallsAmount = 1;
        hitter.removeFromGame(this.game);
        this.game.getRemainingBalls().decrease(decreaseBallsAmount);
    }


}
