//208005587 Itay Sharfer

/**
 * The CollisionInfo class holds information about collision.
 */
public class CollisionInfo {

    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * The collisionPoint method is an access method to the class member collisionPoint.
     * @return - the instance's collisionPoint.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * The collisionObject method is an access method to the class member collisionObject.
     * @return - the instance's collisionObject.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }

    /**
     * The CollisionInfo method is a constractor of CollisionInfo instance.
     * @param collisionPoint - the collision point.
     * @param collisionObject - the collision object.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = new Point(collisionPoint.getX(), collisionPoint.getY());
        this.collisionObject = collisionObject;
    }
}