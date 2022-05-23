package gamedata;
import shapes.Velocity;
import shapes.Ball;
import shapes.Point;
import shapes.Line;
import shapes.Rectangle;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * The Block class contains attributes and methods used on blocks.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle blockShape;
    private Color color;
    private List<HitListener> hitListeners;

    /**
     * The getCollisionRectangle method returns the rectangle that has been.
     * @return - the blockShape member of the block instance.
     */
    public Rectangle getCollisionRectangle() {
        return this.blockShape;
    }

    /**
     * The setBlockShape method is a setter of the member blockshape.
     * @param rec - the rectangle to be set as blockshape.
     */
    public void setBlockShape(Rectangle rec) {
        this.blockShape = new Rectangle(rec.getUpperLeft(), rec.getWidth(), rec.getHeight());
    }

    /**
     * The Block method is a constructor of block instances.
     * @param rec - a rectangle object.
     * @param color - color of the Block.
     */
    public Block(Rectangle rec, Color color) {
        this.setBlockShape(rec);
        this.setColor(color);
        this.hitListeners = new ArrayList<>();
    }

    /**
     * The setColor method sets the color of the block.
     * @param color - a color object.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * The drawOn method draws a block onto a DrawSurface instance.
     * @param surface - the DrawSurface the block will be drawn upon.
     */
    public void drawOn(DrawSurface surface) {
        int x = (int) this.getCollisionRectangle().getUpperLeft().getX(),
                y = (int) this.getCollisionRectangle().getUpperLeft().getY();
        int width = (int) this.getCollisionRectangle().getWidth(),
                height = (int) this.getCollisionRectangle().getHeight();
        surface.setColor(this.color);
        surface.fillRectangle(x, y, width, height);
    }

    /**
     * The timePassed method notifies the block object that certain amount of time has passed.
     */
    public void timePassed() {
        //right now, does nothing.
    }

    /**
     * The hit method calculates a velocity after an impact with the block instance.
     * @param collisionPoint - the point of collision.
     * @param currentVelocity - the velocity before the collision.
     * @param hitter - the ball instance that collided with the block instance.
     * @return - the velocity after the collision.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        final int first = 0, changeDirection = -1, up = 0, left = 1, bottom = 2, right = 3;
        Velocity newVelocity = null;
        Line[] blockLines = this.getCollisionRectangle().generateRectangleLines();
        for (int i = first; i < blockLines.length; i++) {
            if (blockLines[i].isContaining(collisionPoint)) {
                //If one of the horizontal lines has been hit, the DY of currentVelocity should change.
                if (i == up || i == bottom) {
                    newVelocity = new Velocity(currentVelocity.getDX(), currentVelocity.getDY() * changeDirection);
                }
                //If one of the vertical lines has been hit, the DX of currentVelocity should change.
                if (i == left || i == right) {
                    newVelocity = new Velocity(currentVelocity.getDX() * changeDirection, currentVelocity.getDY());
                }
            }
        }
        //Notifying all the registered listeners a hit was occurred.
        this.notifyHit(hitter);
        //If no lines have been hit, velocity should not change.
        if (newVelocity == null) {
            return currentVelocity;
        } else {
            return newVelocity;
        }
    }

    /**
     * The addToGame method adds the block instance to the Sprite collection and to the gameEnvironment of
     * a game instance.
     * @param g - a game instance.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * The removeFromGame method removes the instance from a game object.
     * @param game - a game object that the block instance will be removed from.
     */
    public void removeFromGame(Game game) {
        final int decreaseBlocksAmount = 1;
        game.removeCollidable(this);
        game.removeSprite(this);
        game.getRemainingBlocks().decrease(decreaseBlocksAmount);
    }

    /**
     * The addHitListener method adds a Hitlistener object to the HitListner list of the instance.
     * @param hl - a HitListener object that will be added to the HitListner list of the instance.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * The removeHitListener method removes a Hitlistener object to the HitListener list of the instance.
     * @param hl - a HitListener object that will be removed to the HitListener list of the instance.
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * The notifyHit method notifies all the registered listeners that a hit has occurred.
     * @param hitter - a ball instance that hits the block instance.
     */
    private void notifyHit(Ball hitter) {
        //Generating a copy of the hitListeners list.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        //Notifying all hitListeners in the list.
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
