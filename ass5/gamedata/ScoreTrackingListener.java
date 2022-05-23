package gamedata;
import shapes.Ball;
/**
 * The ScoreTrackingListener class is used for keeping score of a game.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    /**
     * The ScoreTrackingListener method constructs a new ScoreTrackingListener instance.
     * @param scoreCounter - a Counter instance that will be assigned as the currentScore member.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    /**
     * The setScore method sets the currentScore member of an instance to a given counter instance.
     * @param currentScore - a counter instance.
     */
    public void setScore(Counter currentScore) {
        this.currentScore = currentScore;
    }
    /**
     * The getCurrentScore method is an access method to the currentScore member of an instance.
     * @return - the currentScore member of an instance.
     */
    public Counter getCurrentScore() {
        return this.currentScore;
    }
    /**
     * The hitEvent method adds points when a hit occurs.
     * @param beingHit - the block instance that is being hit.
     * @param hitter - the ball instance that collided with the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        final int increasePoints = 5;
        this.currentScore.increase(increasePoints);
    }

    /**
     * The addWinBonus method adds points to the value when the game is won.
     */
    public void addWinBonus() {
        final int winningBonus = 100;
        this.currentScore.increase(winningBonus);
    }
}
