//208005587 Itay Sharfer
package shapes;
/**
 * The Rectangle class contains attributes and methods used on rectangle objects.
 * @version 1.00 10 April 2022
 * @author Itay Sharfer
 * @since 10-04-2022
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * @param upperLeft - the upper left point of the rectangle.
     * @param width     - the width of the rectangle.
     * @param height    - the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = new Point(upperLeft.getX(), upperLeft.getY());
        this.width = width;
        this.height = height;
    }

    // Return a (possibly empty) List of intersection points
    // with the specified line.

    /**
     * The intersectionPoints method generates a list of intersectio points between the rectangle to a line.
     *
     * @param line - a line object.
     * @return - a list of intersection points of the rectangle object with the line object.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> interPoints = new java.util.ArrayList<>();
        //Creating an array containing all the lines of the rectangle.
        Line[] recLines = this.generateRectangleLines();
        for (Line l : recLines) {
                //Checking if the lines are intersecting
                if (l.isIntersecting(line)) {
                    //Adding the intersection point to the interPoints list.
                    interPoints.add(l.intersectionWith(line));
                }

        }
        return interPoints;
    }

    /**
     * The getWidth method returns the width of a rectangle object.
     *
     * @return - width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * The getHeight method returns the height of a rectangle object.
     *
     * @return - height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * The getUpperLeft method returns the upper left point of a rectangle object.
     *
     * @return - upper left point of a rectangle object.
     */
    public Point getUpperLeft() {
        return new Point(this.upperLeft.getX(), this.upperLeft.getY());
    }

    /**
     * The setUpperLeft method sets a given point as the rectangle instance upper left point.
     * @param newUpperLeft
     */
    public void setUpperLeft(Point newUpperLeft) {
        this.upperLeft.setX(newUpperLeft.getX());
        this.upperLeft.setY(newUpperLeft.getY());
    }
    /**
     * The getUpperRight method generates the upper right point of the rectangle as a point object.
     * @return - the upper right point of the rectangle
     */
    public Point getUpperRight() {
        return new Point(this.getUpperLeft().getX() + this.getWidth(), this.getUpperLeft().getY());
    }

    /**
     * The getBottomLeft method generates the bottom left point of the rectangle as a point object.
     * @return - the bottom left point of the rectangle
     */
    public Point getBottomLeft() {
        return new Point(this.getUpperLeft().getX(), this.getUpperLeft().getY() + this.getHeight());
    }

    /**
     * The getBottomRight method generates the bottom right point of the rectangle as a point object.
     * @return - the bottom right point of the rectangle
     */
    public Point getBottomRight() {
        return new Point(this.getUpperLeft().getX() + this.getWidth(),
                this.getUpperLeft().getY() + this.getHeight());
    }

    /**
     * The generateRectangleLines method generates an array contains the 4 different lines of the rectangle.
     * The array order is the following: first element is the upper line, second is the left line, third is the
     * bottom line, and forth is the right line.
     * @return - an array contains the 4 different lines of the rectangle.
     */
    public Line[] generateRectangleLines() {
        final int amountOfLines = 4, up = 0, left = 1, bottom = 2, right = 3;
        Line[] recLines = new Line[amountOfLines];
        //Creating the upper line
        recLines[up] = new Line(this.getUpperLeft(), getUpperRight());
        //Creating the left line
        recLines[left] = new Line(this.getUpperLeft(), this.getBottomLeft());
        //Creating the bottom line
        recLines[bottom] = new Line(this.getBottomLeft(), this.getBottomRight());
        //Creating the right line
        recLines[right] = new Line(this.getUpperRight(), this.getBottomRight());
        return recLines;
    }
}