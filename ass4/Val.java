//208005587 Itay Sharfer
import java.util.List;
import java.util.Map;

/**
 * The Val class contains attributes and methods used on values that are part of expressions.
 */
public class Val implements Expression {
    private boolean value;

    /**
     * The Val method constructs a new Val instance.
     * @param value - value of the new instance
     */
    public Val(boolean value) {
        this.value = value;
    }

    /**
     * The evaluate method evaluates the value of an instance based on a map object key to value mapping.
     * @param assignment - a map instance of variable values.
     * @return - Truth value of an instance based on the mapping.
     * @throws Exception
     */
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return this.value;
    }

    /**
     * The evaluate method evaluates the truth value of an instance.
     * @return - The truth value of an instance.
     * @throws Exception
     */
    public Boolean evaluate() throws Exception {
        return this.value;
    }
    /**
     * The toString method converts an instance to a string form.
     * @return - the string form of the instance.
     */
    public String toString() {
        if (this.value) {
            return "T";
        } else {
            return "F";
        }
    }

    /**
     * The getVariables method returns null since there are no variables in a Val instance.
     * @return - null
     */
    public List<String> getVariables() {
        return null;
    }

    /**
     * The assign method return the instance since a value cannot be assigned.
     * @param var - a string instance
     * @param expression - a variable instance.
     * @return - the instance.
     */
    public Expression assign(String var, Expression expression) {
        return this;
    }

    /**
     * The nandify method returns the instance since nand is a binary operator and cannot be invoked on one element.
     * @return - this instance.
     */
    public Expression nandify() {
        return this;
    }

    /**
     * The norify method returns the instance since nor is a binary operator and cannot be invoked on one element.
     * @return - this instance.
     */
    public Expression norify() {
        return this;
    }

    /**
     * The simplify method returns the instance since a value cannot be simplified.
     * @return - the calling instance.
     */
    public Expression simplify() {
        return this;
    }
}
