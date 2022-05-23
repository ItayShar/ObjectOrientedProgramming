/**
 * The BaseExpression class contains attributes methods used on unary expressions.
 */
public abstract class UnaryExpression extends BaseExpression implements Expression {
    protected Expression exp;

    /**
     * The UnaryExpression method constructs a new UnaryExpression instance.
     * @param exp - an expression instance.
     */
    public UnaryExpression(Expression exp) {
        this.exp = exp;
    }

}
