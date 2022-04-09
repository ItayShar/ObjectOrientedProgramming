/**
 * The velocity class specifies the change in position on the X and Y axes.
 * @version 1.00 3 April 2022
 * @author Itay Sharfer
 * @since 03-04-2022
 */
public class Velocity {
    private double dx;

    /**
     * The getDX method returns the dx value of the velocity instance.
     * @return - dx value of the velocity instance.
     */
    public double getDX() {
        return this.dx;
    }
    private double dy;

    /**
     * The getDY method returns the dy value of the velocity instance.
     * @return - dy value of the velocity instance.
     */
    public double getDY() {
        return this.dy;
    }

    /**
     * The Velocity method is a constructor of a new velocity instance.
     * @param dx - the X-axis change.
     * @param dy - the Y-axis change.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * The Velocity method is an instance creator using angle and speed variables.
     * @param angle - the angle of movement of the object
     * @param speed - the speed of movement of the object
     * @return - a new Velocity instance with dx and dy values.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        final int DIRECTION_ANGLE = 90, CHANGE_DIRECTION = -1;
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = CHANGE_DIRECTION * speed * Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }
    /**
     * The applyToPoint method changes a point coordinates by the velocity values.
     * @param p - a point that will change.
     * @return - a new point after the coordinates change.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }
}
