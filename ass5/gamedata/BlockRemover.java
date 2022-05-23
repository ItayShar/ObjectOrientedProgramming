//208005587 Itay Sharfer
package gamedata;
import shapes.Ball;

/**
 * The BlockRemover class is used for removing blocks from a game instance and keeping count of the number of
 * remaining blocks in the game.
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * The BlockRemover method constructs a new BlockRemover instance.
     * @param game - the game instance that the BlockRemover will be removing blocks from.
     * @param removedBlocks - the amount of removed blocks.
     */
    public BlockRemover(Game game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = new Counter(game.getNumberOfBlocks() - removedBlocks.getValue());
    }

    /**
     * The setRemainingBlocks method sets the remainingBlocks counter to a given value.
     * @param remainingBlocks - the value that the counter will be set to.
     */
    public void setRemainingBlocks(Counter remainingBlocks) {
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * The getRemainingBlocks is an access method to the remainingBlocks counter of the instance.
     * @return - the remainingBlocks counter of the instance.
     */
    public Counter getRemainingBlocks() {
        return this.remainingBlocks;
    }
    /**
     * The hitEvent method removes a block that was hit from the game and decreases the remainingBlocks counter by 1.
     * @param beingHit - the block instance that was hit.
     * @param hitter - the ball instance that collided with the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        final int decreaseByOne = 1;
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.game);
        this.remainingBlocks.decrease(decreaseByOne);
    }
}