import java.util.Map;
import java.util.TreeMap;

/**
 * The ExpressionsTest class tests the different classes in assignment 4.
 */
public class ExpressionsTest {
    /**
     * The main method drives the test proccess.
     * @param args - string array.
     */
    public static void main(String[] args) {
        Expression e1 = new And(new Xor(new Var("x"), new Var("y")), new Or(new Var("z"), new Val(true)));
        System.out.println(e1);
        Map<String, Boolean> assignment = new TreeMap<>();
        assignment.put("x", true);
        assignment.put("y", false);
        assignment.put("z", false);
        try {
            System.out.println(e1.evaluate(assignment));
        } catch (Exception e) { }
        System.out.println(e1.nandify());
        System.out.println(e1.norify());
    }
}
