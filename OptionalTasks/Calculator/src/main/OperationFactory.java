package main;

import main.ArithmeticOperations.*;

/**
 * Is used to create objects of operations
 *
 * @author Katsiaryna Stalchanka
 */

class OperationFactory {
    /**
     * Creates objects of ArithmeticOperations based on the value of operator symbol
     * @param userOperation
     * @return objects of ArithmeticOperations or null if operator symbol is incorrect
     */
    ArithmeticOperations getOperation(String userOperation) {
        ArithmeticOperations operation = null;
        if (userOperation.equals("+")) {
            operation = new AddOperation();
        } else if (userOperation.equals("-")) {
            operation = new SubtractOperation();
        } else if (userOperation.equals("x")) {
            operation = new MultiplyOperation();
        } else if (userOperation.equals(":")) {
            operation = new DivideOperation();
        }
        return operation;
    }
}
