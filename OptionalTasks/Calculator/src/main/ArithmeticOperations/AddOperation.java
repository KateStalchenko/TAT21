package main.ArithmeticOperations;

/**
 * @author Katsiaryna Stalchanka
 */
public class AddOperation implements ArithmeticOperations {
    @Override
    /**
     * Adds left operand to right operand and returns their sum
     */
    public double execute(double leftOperand, double rightOperand) {
        return leftOperand + rightOperand;
    }
}
