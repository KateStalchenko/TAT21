package main.ArithmeticOperations;

public class DivideOperation implements ArithmeticOperations {
    @Override
    /**
     * Divides left operand by right operand and returns their quotient
     * @throws ArithmeticException when right operand equals to zero
     */
    public double execute(double leftOperand, double rightOperand) {
        if (rightOperand==0){
            throw new ArithmeticException("You cannot divide by zero!");
        }
        return leftOperand/rightOperand;
    }
}
