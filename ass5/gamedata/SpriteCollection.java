//208005587 Itay Sharfer
package gamedata;
import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * The SpriteCollection class manages a List of Sprites.
 */
public class SpriteCollection {
    private java.util.List<Sprite> spriteList;

    /**
     * The getSpriteList method is an access method to the spriteColl method of an instance.
     * @return - the spriteColl member of an instance.
     */
    public java.util.List<Sprite> getSpriteList() {
        return this.spriteList;
    }

    /**
     * The addSprtie method adds a sprite to the spriteList member.
     * @param s - the sprite that will be added to spriteList.
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }

    /**
     * The SpriteCollection method is a constructor of a new SpriteCollection instance.
     */
    public SpriteCollection() {
        this.spriteList = new ArrayList<>();
    }

    /**
     * The notifyAllTimePassed method invokes the timePassed method on all the sprites in spriteList.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spriteList = new ArrayList<>(this.spriteList);
        for (Sprite s : spriteList) {
            s.timePassed();
        }
    }

    /**
     * The drawAllOn method invokes the drawOn method on all the sprites in spriteList using DrawSurface object.
     * @param d - a DrawSurface instance the sprites will be drawn upon.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : this.getSpriteList()) {
            s.drawOn(d);
        }
    }
}