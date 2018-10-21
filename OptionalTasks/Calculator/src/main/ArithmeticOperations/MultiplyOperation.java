package main.ArithmeticOperations;

public class MultiplyOperation implements ArithmeticOperations {
    @Override
    /**
     * Multiplies left operand by right operand and returns their product
     */
    public double execute(double leftOperand, double rightOperand) {
        return leftOperand * rightOperand;
    }
}
