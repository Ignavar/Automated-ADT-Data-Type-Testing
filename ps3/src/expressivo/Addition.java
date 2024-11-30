package expressivo;

/**
 * An addition operation between two expressions.
 */
public class Addition implements Expression {
    private final Expression left, right;

    public Addition(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return left.toString() + " + " + right.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Addition addition = (Addition) obj;
        return left.equals(addition.left) && right.equals(addition.right);
    }

    @Override
    public int hashCode() {
        int result = left.hashCode();
        result = 31 * result + right.hashCode();
        return result;
    }

    @Override
    public void checkRep() {
        // Ensure left and right are non-null
        if (left == null || right == null) {
            throw new IllegalStateException("Addition requires both left and right operands.");
        }
    }
}