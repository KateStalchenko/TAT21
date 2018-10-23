package main.ArithmeticOperations;

/**
 * Provides interface for performing arithmetic operations
 *
 * @author Katsiaryna Stalchanka
 */
public interface ArithmeticOperations {
    /**
     * Performs specified operations on left and right operands
     *
     * @param leftOperand
     * @param rightOperand
     * @return result of these operations
     */
    double execute(double leftOperand, double rightOperand);
}
