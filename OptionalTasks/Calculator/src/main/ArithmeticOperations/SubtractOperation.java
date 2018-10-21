package main.ArithmeticOperations;

public class SubtractOperation implements ArithmeticOperations {
    @Override
    /**
     *Takes the difference of left operand and right operand
     */
    public double execute(double leftOperand, double rightOperand) {
        return leftOperand - rightOperand;
    }
}
