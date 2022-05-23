//208005587 Itay Sharfer
import java.util.ArrayList;
import java.util.List;

/**
 * The BinaryExpression class contains methods used on binary expressions.
 */
public abstract class BinaryExpression extends BaseExpression implements Expression {
    protected Expression left;
    protected Expression right;

    /**
     * The BinaryExpression method constructs a new BinaryExpression instance.
     * @param left - The left expression in the binary Expression
     * @param right - The right expression in the binary Expression
     */
    public BinaryExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
    /**
     * The getVariables method generates a list of all the variables in an instance.
     * @return - a list of all the variables in an instance.
     */
    public List<String> getVariables() {
        List<String> variables = new ArrayList<>();
        //If the list is null, the left expression doesn't contain variables.
        if (left.getVariables() != null) {
            for (String s : left.getVariables()) {
                if (!variables.contains(s)) {
                    variables.add(s);
                }
            }
        }
        //If the list is null, the right expression doesn't contain variables.
        if (right.getVariables() != null) {
            for (String s : right.getVariables()) {
                if (!variables.contains(s)) {
                    variables.add(s);
                }
            }
        }
        //null will be returned in case of an instance with no variables.
        return variables;
    }
}
