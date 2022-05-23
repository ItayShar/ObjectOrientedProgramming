package shapes;
/**
 * The Line class contains attributes and methods used on lines.
 * @version 1.01 10 April 2022
 * @author Itay Sharfer
 * @since 03-04-2022
 */
public class Line {
    private Point start;
    /**
     * The setStart method sets a line starting point to a given point.
     * @param start - the point desired to be set as starting point of the line.
     */
    public void setStart(Point start) {
        this.start = new Point(start.getX(), start.getY());
    }

    /**
     * The Start method returns a line starting point.
     * @return - the line starting point.
     */
    public Point start() {
        return this.start;
    }
    //The member end represents the end point of a line.
    private Point end;

    /**
     * The setEnd method sets a line starting point to a given point.
     * @param end - the point desired to be set as ending point of the line.
     */
    public void setEnd(Point end) {
        this.end = new Point(end.getX(), end.getY());
    }

    /**
     * The end method returns a line ending point.
     * @return - the line ending point.
     */
    public Point end() {
        return this.end;
    }

    /**
     * The Line method is a constructor of a new line. It uses 2 points in order to set start and end points.
     * @param start - the starting point of the line.
     * @param end - the ending point of the line.
     */
    public Line(Point start, Point end) {
        this.start = new Point(start.getX(), start.getY());
        this.end = new Point(end.getX(), end.getY());
    }

    /**
     * The Line method is a constructor of a new line. It uses 4 coordinates values for setting start and end points.
     * @param x1 - the X-axis coordinate of the starting point.
     * @param y1 - the Y-axis coordinate of the starting point.
     * @param x2 - the X-axis coordinate of the ending point.
     * @param y2 - the Y-axis coordinate of the ending point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * The length method calculates the length of a line, from start to end.
     * @return - the length of the line.
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * The middle method calculates the coordinates of the middle of a line.
     * @return - the middle point of the line.
     */
    public Point middle() {
        double midX = (this.start.getX() + this.end.getX()) / 2;
        double midY = (this.start.getY() + this.end.getY()) / 2;
        Point mid = new Point(midX, midY);
        return mid;
    }

    /**
     * The isIntersecting method checks if 2 lines are intersecting, and returns true or false accordingly.
     * @param other - a line that is checked if this line is intersecting with.
     * @return true - if the lines are intersecting, false - if the lines are not intersecting.
     */
    public boolean isIntersecting(Line other) {
        Point potentialInterPoint;
        //A line is intersecting with itself on infinite number of points.
        if (this.equals(other)) {
            return true;
        }
        //if 2 lines are overlapping, they intersect on infinite number of points.
        if (this.isOverlapping(other)) {
            return true;
        }
        if (this.isParallelTo(other)) {
            return false;
        }
        //at this point, we covered all the cases in which the segments can be disjoint.
        //from here, the segments "this" and "other" may not intersect, but the lines they belong to will intersect.
        //therefore, we can calculate the intersection point of the 2 lines.
            potentialInterPoint = this.intersectionPointWith(other);
            //Here we're checking if the intersection point of the lines is part of both segments.
            if (this.isInSegment(potentialInterPoint) && other.isInSegment(potentialInterPoint)) {
                return true;
            }
        //If none of the above if-statements have executed, it means the lines are intersecting but the segments are not
        return false;

    }

    /**
     * The intersectionWith method calculates the intersection point of 2 lines
     * The method will return null if the lines are not intersecting or if they are overlapping (in which case the lines
     * intersect in an infinite amount of points.
     * @param other - a line that is checked if this line is intersecting with.
     * @return intersectionPoint - if the lines are intersecting, or null - if the lines are not intersecting
     * or intersecting in multiple points.
     */
    public Point intersectionWith(Line other) {
        Point intersectionPoint;
        //Checks if the lines are intersecting.
        if (!this.isIntersecting(other)) {
            return null;
        }
        //Checks if the lines are overlapping. According to an answer from the forum written by an instructor, the
        //function should return null if the lines are overlapping.
        //The function equals is a special case of the overlapping function, and therefore handled the same.
        if (this.isOverlapping(other) || this.equals(other)) {
            return null;

            //If all if-statements above have not been executed, the lines are intersecting at a single point.
        } else {
            intersectionPoint = this.intersectionPointWith(other);
            return intersectionPoint;
        }
    }

    /**
     * The equals method checks if 2 lines have the same starting and ending points.
     * @param other - a line.
     * @return - true - if the lines are equal, false - if the lines are not equal.
     */
    public boolean equals(Line other) {
        //Checks if the start and end points are the same and checks if the start and end points order is reversed.
        return (this.start.equals(other.start()) && this.end.equals(other.end()))
                || (this.start.equals(other.end()) && this.end.equals(other.start()));
    }


    /**
     * The isParallelTo method checks if 2 lines are parallel. Note that a line is not parallel to itself.
     * @param other - a line.
     * @return true- if the lines are parallel, false - if the lines are not parallel.
     */
    public boolean isParallelTo(Line other) {
        //if both lines are vertical, checks if the lines don't share the same X-axis coordinate
        if (this.isVertical() && other.isVertical() && this.start.getX() != other.start().getX()) {
            return true;

            //if this if-statement is executed, it means both lines are not vertical. checks if the slope of the 2 lines
            //equal and if the y-axis interception is not the same (making both lines the same line).
        } else {
            return !other.isVertical() && this.slope() == other.slope()
                    && this.yIntersection() != other.yIntersection();
        }
    }

    /**
     * The slope method calculates the slope of a line. The slope method is excuted on non-vertical lines only.
     * @return the slope of the line.
     */
    public double slope() {
            return (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
    }
    /**
     * The yIntersection method calculates the y-Axis intersection of a line.
     * The yIntersection method and executed on non-vertical lines only.
     * @return the slope of the line.
     */
    public double yIntersection() {
        double yAxisIntersection = this.start.getY() - (this.slope() * this.start.getX());
        return yAxisIntersection;
    }

    /**
     * The sameAs method checks if 2 given lines segments are parts of the same line.
     * @param other - a line.
     * @return true - if both lines segments are parts of the same line, and false otherwise.
     */
    @SuppressWarnings("checkstyle:SimplifyBooleanReturn")
    public boolean sameAs(Line other) {
        //Checks if the lines are the same line.
        if (this.equals(other)) {
            return true;
          //Checks if the line is vertical. If so, checks if the x parameter of both lines' starting points are equal.
        } else if (this.isVertical() && other.isVertical() && this.start.getX() == other.start().getX()) {
            return true;
          //In all other cases, checks the slopes and y interceptions of the 2 lines.
        } else {
            return this.slope() == other.slope() && this.yIntersection() == other.yIntersection();
        }
    }
    /**
     * The isVertical method checks if a line is vertical (parallel to the Y-axis).
     * @return true  - if the line is vertical and false if it isn't vertical.
     */
    public boolean isVertical() {
        //if both start and end points X coordinate is equal, it means the line is vertical.
        return this.start.getX() == this.end.getX();
    }
    /**
     * The isOverlaping method checks if a line overlaps with another line.
     * @param other - the line that is checked.
     * @return true  - if this line overlaps with other line, and false otherwise.
     */
    public boolean isOverlapping(Line other) {
        //checks if both lines are equal. A line is overlapping with itself.
        if (this.equals(other)) {
            return true;

            //checks if both segments are the same line.
        } else if (this.sameAs(other)) {
            //checks if the lines are vertical, note that if the lines are
            //the same they are both vertical or both non-vertical.
            if (this.isVertical()) {
                //checks that the Y coordinate of the starting point of other is between this line
                //start and end point Y coordinates.
                if (this.start().getY() < other.start().getY() && other.start().getY() < this.end().getY()) {
                    return true;
                }
                //checks the same as last if statement but in this case the points order is reversed.
                if (this.end().getY() < other.start().getY() && other.start().getY() < this.start().getY()) {
                    return true;
                }

                //Here we're dealing with lines of the same segment that are non-vertical
            } else {
                //checks that the X coordinate of the starting point of other is between this line
                //start and end point X coordinates.
                if (this.start().getX() < other.start().getX() && other.start().getX() < this.end().getX()) {
                    return true;
                }
                //checks the same as last if statement but in this case the points order is reversed.
                if (this.end().getX() < other.start().getX() && other.start().getX() < this.start().getX()) {
                    return true;
                }
            }

        }
        //If none of the above is executed, the lines are not overlapping.
        return false;
    }

    /**
     * The intersectionPointWith calculates the intersection point of 2 lines (not the segments).
     * this method will be executed only if lines (and not the segments) are intersecting.
     * @param other - a line
     * @return the point of intersection.
     */
    public Point intersectionPointWith(Line other) {
        double interX, interY;
        if (this.isVertical()) {
            //All points on a vertical line has the same X value.
            interX = this.start().getX();
            //Please note that Y intersection is calculated correctly since if this line is vertical the other line
            //is not vertical (and if so they are the same/ overlapping and this method will not be executed) and
            //therefore the other's slope is defined.
            interY = other.slope() * interX + other.yIntersection();
            return new Point(interX, interY);
        }
        if (other.isVertical()) {
            //All points on a vertical line has the same X value.
            interX = other.start().getX();
            //Please note that Y intersection is calculated correctly since if this line is vertical the other line
            //is not vertical (and if so they are the same/ overlapping and this method will not be executed) and
            //therefore the this' slope is defined.
            interY = this.slope() * interX + this.yIntersection();

            //Dealing with all other cases (both lines are not vertical and therefore both lines' slopes are defined)
        } else {
            interX = (other.yIntersection() - this.yIntersection()) / (this.slope() - other.slope());
            interY = (this.slope() * interX) + this.yIntersection();
        }
        return new Point(interX, interY);
    }

    /**
     * The isInSegment method checks if a point is part of a segment.
     * @param other - a point.
     * @return true  - if "other" point is part of "this" segment, and false otherwise.
     */
    public boolean isInSegment(Point other) {
        if (this.isVertical()) {
            //checks if the point Y value is between start and end points Y values.
            if (this.start().getY() <= other.getY() && other.getY() <= this.end().getY()) {
                return true;
            }
            //checks the same as last if statement but points order is reversed.
            if (this.end().getY() <= other.getY() && other.getY() <= this.start().getY()) {
                return true;
            }

            // Check for non-vertical lines
        } else {
            if (this.start().getX() <= other.getX() && other.getX() <= this.end().getX()) {
                return true;
            }
            //checks the same as last if statement but points order is reversed.
            if (this.end().getX() <= other.getX() && other.getX() <= this.start().getX()) {
                return true;
            }
        }
        //If none of the above has executed, point "other" is not in "this" segment.
        return false;
    }

    /**
     * The closestIntersectionToStartOfLine method.
     * @param rect - a rectangle object.
     * @return - if there's no intersection - null. else, returns the closest intersection point to this line start.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        final int first = 0;
        //Creating a list of all intersection points of the line object with the rectangle object.
        java.util.List<Point> pointList = rect.intersectionPoints(this);
        //In case there are no intersection points, the list is empty and the method will return null.
        if (pointList.isEmpty()) {
            return null;
        }
        //Taking the first point on pointList as a starting closest intersection point.
        // It exists because pointList is not empty as checked above.
        Point closestIntersection = pointList.get(first);
        //Checking all the points in the list.
        for (Point p : pointList) {
            //Checking if the distance from the point p to the line start is less than the distance from the current
            //closest intersection point to the start of the line.
            if (p.distance(this.start()) < closestIntersection.distance(this.start())) {
                closestIntersection = p;
            }
        }
        return closestIntersection;
    }

    /**
     * The isContaining method checks if a point is a part of a line object.
     * @param p - the point checked.
     * @return - true if the point is in the line and false otherwise.
     */
    public boolean isContaining(Point p) {
        if (this.isVertical()) {
            //Checks if p is on the line object when it's vertical.
            if (this.start().getX() == p.getX()) {
                //Checks if p is between the start and end points of the line instance.
                if (this.start().getY() <= p.getY() && p.getY() <= this.end().getY()) {
                    return true;
                }
                //Checks the same as last if, but order is reversed.
                if (this.end().getY() <= p.getY() && p.getY() <= this.start().getY()) {
                    return true;
                }
                //If we get to this part of the code, it means the line is vertical and p is on it, but it is not
                //between the start and end points and therefore is not part of the line instance.
                return false;
            }
        }
            //Checks if p is part of the line when it's not vertical.
            if (p.getY() == this.slope() * p.getX() + this.yIntersection()) {
                //Checks if p is between the start and end points of the line instance.
                if (this.start().getX() <= p.getX() && p.getX() <= this.end().getX()) {
                    return true;
                }
                //Checks the same as last if, but order is reversed.
                if (this.end().getX() <= p.getX() && p.getX() <= this.start().getX()) {
                    return true;
                }
                //If we get to this part of the code, it means p is on the non-vertical line, but it is not
                //between the start and end points and therefore is not part of the line instance.
                return false;
            }
            //If we got to this part of the code, it means p is not part of the line in general.
            return false;
        }
    }
