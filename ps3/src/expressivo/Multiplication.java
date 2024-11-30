package expressivo;

/**
 * A multiplication operation between two expressions.
 */
public class Multiplication implements Expression {
    private final Expression left, right;

    public Multiplication(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return left.toString() + " * " + right.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Multiplication multiplication = (Multiplication) obj;
        return left.equals(multiplication.left) && right.equals(multiplication.right);
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
            throw new IllegalStateException("Multiplication requires both left and right operands.");
        }
    }
}