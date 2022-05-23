//208005587 Itay Sharfer
import java.util.List;
import java.util.Map;

/**
 * The BaseExpression class contains attributes methods used on all the kinds of expressions.
 */
public abstract class  BaseExpression implements Expression {
    protected Expression exp;

    /**
     * The evaluate method calculates the truth value of an instance based on a map istance.
     * @param assignment - a map instance with keys and values of the variables in the BaseExpression instance.
     * @return - The truth value of the instance based on the mapping.
     * @throws Exception
     */
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return this.exp.evaluate(assignment);
    }

    /**
     * The evaluate method calculates the truth value of an instance.
     * @return - The truth value of the instance.
     * @throws Exception
     */
    public Boolean evaluate() throws Exception {
        return this.exp.evaluate();
    }

    /**
     * The getVariables method gets all the variables of an instance.
     * @return - a list of all variables in the expression.
     */
    public List<String> getVariables() {
        return (this.exp.getVariables());
    }

    /**
     * The assign method assigns an expression to a variable of an instance.
     * @param var - the variable that will be replaced by an expression.
     * @param expression - the expression that will be assigned.
     * @return - a new expression instance after assignment.
     */
    public Expression assign(String var, Expression expression) {
        return this.exp.assign(var, expression);
    }
}
