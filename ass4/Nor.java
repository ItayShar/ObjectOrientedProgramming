import java.util.Map;

/**
 * The Nor class contains attributes and methods used on nor-expressions.
 */
public class Nor extends BinaryExpression implements Expression {
    /**
     * The Nor method constructs a new Nor instance.
     * @param left - the left expression of the or instance.
     * @param right - the right expression of the or instance.
     */
    public Nor(Expression left, Expression right) {
        super(left, right);
    }
    /**
     * The evaluate method calculates the truth value of the instance based on a mapping of variables and values.
     * @param assignment - a map instance of variables and values
     * @return - The truth value of the instance based on the mapping.
     * @throws Exception
     */
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return (!(this.left.evaluate(assignment) || this.right.evaluate(assignment)));
    }

    /**
     * The evaluate method calculates the truth value of the instance.
     * @return - The truth value of the instance.
     * @throws Exception
     */
    public Boolean evaluate() throws Exception {
        return (!(this.left.evaluate() || this.right.evaluate()));
    }

    /**
     * The toString method converts the instance to a string form.
     * @return - the string form of the instance.
     */
    public String toString() {
        return ("(" +  this.left.toString() + " V " + this.right.toString() + ")");
    }

    /**
     * The assign method replaces a variable with an Expression.
     * @param var - the variable that will be replaced.
     * @param expression - the expression that will replace the variable.
     * @return - a new expression generated by the replacement.
     */
    public Expression assign(String var, Expression expression) {
        return new Nor(this.left.assign(var, expression), this.right.assign(var, expression));
    }

    /**
     * The nandify method converts an expression to a nand-only expression equal to the original expression.
     * @return - a nand-only expression equal to the original expression.
     */
    public Expression nandify() {
        return new Nand(new Nand(new Nand(this.left.nandify(), this.left.nandify()), new Nand(this.right.nandify(),
                this.right.nandify())), new Nand(new Nand(this.left.nandify(), this.left.nandify()),
                new Nand(this.right.nandify(), this.right.nandify())));
    }

    /**
     * The norify method converts an expression to a nor-only expression equal to the original expression.
     * @return - a nor-only expression equal to the original expression.
     */
    public Expression norify() {
        return new Nor(this.right.norify(), this.left.norify());
    }

    /**
     * The simplify method generates a simplified version of an expression instance.
     * @return - a simplified expression instance
     */
    public Expression simplify() {
        if (this.left.toString() == "T" || this.right.toString() == "T") {
            return new Val(false);
        }
        if (this.right.toString() == "F" && this.left.toString() != "F") {
            return new Not(this.left).simplify();
        }
        if (this.left.toString() == "F" && this.right.toString() != "F") {
            return new Not(this.right).simplify();
        }
        if (this.left == this.right) {
            return new Not(this.left).simplify();
        }
        return new Nor(this.left.simplify(), this.right.simplify()).simplify();
    }
}