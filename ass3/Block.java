//208005587 Itay Sharfer

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The Block class contains attributes and methods used on blocks.
 */
public class Block implements Collidable, Sprite {
    private Rectangle blockShape;
    private Color color;

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
     * @return - the velocity after the collision.
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        final int FIRST = 0, CHANGE_DIRECTION = -1, UP = 0, LEFT = 1, BOTTOM = 2, RIGHT = 3;
        Line[] blockLines = this.getCollisionRectangle().generateRectangleLines();
        for (int i = FIRST; i < blockLines.length; i++) {
            if (blockLines[i].isContaining(collisionPoint)) {
                //If one of the horizontal lines has been hit, the DY of currentVelocity should change.
                if (i == UP || i == BOTTOM) {
                    return new Velocity(currentVelocity.getDX(), currentVelocity.getDY() * CHANGE_DIRECTION);
                }
                //If one of the vertical lines has been hit, the DX of currentVelocity should change.
                if (i == LEFT || i == RIGHT) {
                    return new Velocity(currentVelocity.getDX() * CHANGE_DIRECTION, currentVelocity.getDY());
                }
            }
        }
        //If no lines have been hit, velocity should not change.
        return currentVelocity;
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
}
