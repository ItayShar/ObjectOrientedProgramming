package gamedata;
import biuoop.DrawSurface;
/**
 * The ScoreIndicator class is used to display score on the gui.
 */
public class ScoreIndicator implements Sprite {
    private Game game;
    private Counter scoreCounter;
    /**
     * The ScoreIndicator method constructs a new ScoreIndicator instance.
     * @param game - a game instance the score indicator will be used on.
     * @param scoreCounter - a Counter instance that will be assigned as member.
     */
    public ScoreIndicator(Game game, Counter scoreCounter) {
        this.game = game;
        this.scoreCounter = scoreCounter;
    }
    /**
     * The drawOn method draws the instance on a given DrawSurface object.
     * @param d -  a DrawSurface instance the sprite will be drawn upon.
     */
    public void drawOn(DrawSurface d) {
        final int textXLocation = 375, textYLocation = 18, textSize = 15;
        d.drawText(textXLocation, textYLocation, this.toString(), textSize);
    }
    /**
     * The toString method converts the instance data into a string form.
     * @return - a string form of the instance.
     */
    public String toString() {
        return "Score: " + this.scoreCounter.getValue();
    }

    /**
     * The timePassed method sets the game instance's score value to the scoreCounter.
     */
    public void timePassed() {
        this.scoreCounter.setValue(this.game.getScoreTrackingListener().getValue());
    }

    /**
     * The addToGame method adds the instance to a given game instance.
     * @param g - a game instance that contains a SpriteCollection object.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }
}
