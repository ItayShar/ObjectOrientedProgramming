import biuoop.GUI;
import biuoop.DrawSurface;
import java.util.Random;
import java.awt.Color;

/**
 * The AbstractArtDrawing class is used to create a random drawing of lines and points.
 * @version 1.00 3 April 2022
 * @author Itay Sharfer
 * @since 03-04-2022
 */
public class AbstractArtDrawing {
    /**
     * The generateRandomLine method generates a line with random start and end points coordinates.
     * @param d - the surface which the line will be drawn upon, used for raffling coordinates.
     * @return rLine - the randomly generated line.
     */
    public Line generateRandomLine(DrawSurface d) {
        int height = d.getHeight(), width = d.getWidth();
        Random rand = new Random();
        //Generating 4 random numbers
        double x1 = rand.nextDouble(width) + 1;
        double y1 = rand.nextDouble(height) + 1;
        double x2 = rand.nextDouble(width) + 1;
        double y2 = rand.nextDouble(height) + 1;
        //Returning a new line with the randomly generated coordinates.
        return new Line(x1, y1, x2, y2);
    }
    /**
     * The drawThisLine method draws a given line on the screen.
     * @param l - the line we wish to draw.
     * @param d - the screen the line l will be drawn upon.
     */
   public void drawThisLine(Line l, DrawSurface d) {
       d.setColor(Color.BLACK);
       //Using the DrawSurface method drawLine with l coordinates. The values are getting casted to int because of the
       //drawLine method implementation.
       d.drawLine((int) l.start().getX(), (int) l.start().getY(), (int) l.end().getX(), (int) l.end().getY());
   }

    /**
     * The markMiddle method marks the middle of a line by a red circle.
     * @param l - a line.
     * @param d - the screen that the drawing will be made upon.
     */
   public void markMiddle(Line l, DrawSurface d) {
       //The radius is used for drawing the circles.
       final int MID_RADIUS = 3;
       //Calculating the middle point.
       Point mid = l.middle();
       d.setColor(Color.RED);
       //Using the DrawSurface method fillCircle with mid-coordinates. The values are getting casted to int because
       // of the fillCircle method implementation.
       d.fillCircle((int) mid.getX(), (int) mid.getY(), MID_RADIUS);
   }
    /**
     * The markIntersections method marks all the intersections of lines segments in an array.
     * @param lineArr - an array of lines.
     * @param d - the screen that the drawing will be made upon.
     */
   public void markIntersections(Line[] lineArr, DrawSurface d) {
       //LINES_COUNTER and START are used in order to avoid using magic numbers.
       //MID_RADIUS is used for drawing the circles.
       final int LINES_COUNTER = 10, START = 0, MID_RADIUS = 3;
       //Checking each line in the array
       for (int i = START; i < LINES_COUNTER; i++) {
           //Comparing each line to all other lines in the array
           for (int j = START; j < LINES_COUNTER; j++) {
               //There's no need to check if a line is intersecting with itself.
               if (i == j) {
                   continue;
               }
               //A check the lines are intersecting.
               if (lineArr[i].intersectionWith(lineArr[j]) != null) {
                   d.setColor(Color.BLUE);
                   //Using the DrawSurface method fillCircle with mid-coordinates. The values are getting casted to int
                   // because of the fillCircle method implementation.
                   d.fillCircle((int) lineArr[i].intersectionWith(lineArr[j]).getX(),
                           (int) lineArr[i].intersectionWith(lineArr[j]).getY(), MID_RADIUS);
               }
           }
       }
   }

    /**
     * The drawRandomLines method generates a screen with 10 random lines.It then marks each line
     * middle point by a red circle and marks every intersection made between 2 lines by a blue circle.
     */
   public void drawRandomLines() {
       //LINES_COUNTER and START are used in order to avoid using magic numbers.
       final int LINES_COUNTER = 10, START = 0, DIMENSIONS = 500;
       //Creating a new Window with the title "Abstract Art Drawing"
       //which is 500 pixels wide and 500 pixels high.
       GUI gui = new GUI("Abstract Art Drawing", DIMENSIONS, DIMENSIONS);
       DrawSurface screen = gui.getDrawSurface();
       //Creating a line array for storing all the generated lines.
       Line[] lineArray = new Line[LINES_COUNTER];
       Line newLine;
       for (int i = START; i < LINES_COUNTER; i++) {
           //generating a line with random start and end points values.
           newLine = generateRandomLine(screen);
           //Storing the new line.
           lineArray[i] = newLine;
           //Drawing the new line.
           drawThisLine(newLine, screen);
           //Marking the middle point of the line.
           markMiddle(newLine, screen);
       }
       //Marking all the intersections between the lines.
       markIntersections(lineArray, screen);
       //Displaying all the content
       gui.show(screen);
   }

    /**
     * The main method drives the entire drawing process.
     * @param args - an array of string parameters from the command line.
     */
    public static void main(String[] args) {
        AbstractArtDrawing board = new AbstractArtDrawing();
        board.drawRandomLines();
    }
}
