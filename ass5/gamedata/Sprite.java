//208005587 Itay Sharfer
package gamedata;
import biuoop.DrawSurface;
/**
 * The Sprite interface used by objects that are drawn on screen.
 */
public interface Sprite {
    /**
     * The drawOn method draws a Sprite onto a DrawSurface instance.
     * @param d -  a DrawSurface instance the sprite will be drawn upon.
     */
    void drawOn(DrawSurface d);

    /**
     * The timePassed method notifies a sprite a certain amount of time has passed.
     */
    void timePassed();

    /**
     * The addToGame method adds a Sprite object to a SpriteCollection of a game instance.
     * @param g - a game instance that contains a SpriteCollection object.
     */
    void addToGame(Game g);
}