//208005587 Itay Sharfer

import java.util.ArrayList;

/**
 * The GameEnvironment class is a collection of collidable objects. It contains methods on those objects
 * @version 1.00 10 April 2022
 *  @author Itay Sharfer
 *  @since 10-04-2022
 */
public class GameEnvironment {
    private java.util.List<Collidable> collList;

    /**
     * The getCollList is an access method to the GameEnvironment object's collidable list.
     *
     * @return - the GameEnvironment object's collidable list
     */
    public java.util.List<Collidable> getCollList() {
        return this.collList;
    }

    /**
     * the GameEnvironment method is a constructor of a new GameEnvironment instance.
     */
    public GameEnvironment() {
        this.collList = new ArrayList<Collidable>();
    }

    /**
     * The addCollidable method adds the given collidable to the environment.
     *
     * @param c - the collidable to be added.
     */
    public void addCollidable(Collidable c) {
        this.getCollList().add(c);
    }


    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.

    /**
     * The getClosestCollision method checks if an object moving through a line instance will hit any Collidables
     * stored in the GameEnvironment instance. If so, it will return a collisionInfo object of the closest collision.
     * If no collision will be made, null is returned.
     *
     * @param trajectory - the trajectory the object will move in.
     * @return - null if no collision will be made, CollisionInfo object of the closest collision otherwise.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Collidable closestCollidable = null;
        final int FIRST = 0;
        int index = 0;
        java.util.List<Point> allIntersections = new ArrayList<>();
        //Iterating through every collidable in collList and checking if it intersects with trajectory.
        for (Collidable c : this.getCollList()) {
            if (trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle()) != null) {
                //adding all the closest intersection point to the allIntersections list.
                allIntersections.add(trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle()));
            }
        }
        //Checking if there's an intersection point in allIntersections.
        if (allIntersections.isEmpty()) {
            return null;
        }
        //Setting the first point in the list as the closest point.
        double distance = trajectory.start().distance(allIntersections.get(FIRST));
        // We have a list of the closest intersection point of each collidable. Now we will find the index of the
        // closest one To trajectory's start.
        for (Point p : allIntersections) {
            if (trajectory.start().distance(p) < distance) {
                index = allIntersections.indexOf(p);
                distance = trajectory.start().distance(p);
            }
        }
        Point collisionPoint = allIntersections.get(index);
        for (Collidable c : this.getCollList()) {
            for (Line l : c.getCollisionRectangle().generateRectangleLines()) {
                if (l.isContaining(collisionPoint)) {
                    closestCollidable = c;
                }
            }
        }
        //Now that we have the index of the closest intersection, we can return it and it's object as the members of a
        //CollisionInfo instance
        return new CollisionInfo(collisionPoint, closestCollidable);
    }
}