/**
 * The Point class contains attributes and methods used on points.
 * @version 1.00 3 April 2022
 * @author Itay Sharfer
 * @since 03-04-2022
 */
public class Point {
    //the member x represents the X axis value of a point.
    private double x;
    /**
     * The getX method returns the X-axis coordinate of a point.
     * @return - The X-axis coordinate of a point.
     */
    public double getX() {
        return this.x;
    }
    /**
     * The setX method changes the X-axis coordinate of a point to a given parameter.
     * @param x - the desired X-axis coordinate.
     */
    public void setX(double x) {
        this.x = x;
    }
    //the member y represents the Y axis value of a point.
    private double y;
    /**
     * The getY method returns the Y-axis coordinate of a point.
     * @return - The Y-axis coordinate of a point.
     */
    public double getY() {
        return this.y;
    }
    /**
     * The getX method changes the Y-axis coordinate of a point to a given parameter.
     * @param y - the desired Y-axis coordinate.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * The Point method is a constructor of a new point. it uses 2 parameters in order to set coordinates.
     * @param x - the desired X-axis coordinate.
     * @param y - the desired Y-axis coordinate.
     */
    public Point(double x, double y) {
        setX(x);
        setY(y);
    }

    /**
     * The distance method calculates the distance between 2 points.
     * @param other - a point.
     * @return - the distance between this point and other point.
     */
    public double distance(Point other) {
        return Math.sqrt((this.getX() - other.getX()) * (this.getX() - other.getX())
                + (this.y - other.getY()) * (this.y - other.getY()));
    }

    /**
     * The equals method checks if 2 points have the same X and Y coordinates.
     * @param other - a point.
     * @return - the distance between this point and other point.
     */
    public boolean equals(Point other) {
        return this.getX() == other.getX() && this.getY() == other.getY();
    }
}
