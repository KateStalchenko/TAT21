package main;

import main.ArithmeticOperations.ArithmeticOperations;

public class Calculator {
    /**
     * Entry point to the program
     * @param args command line parameters
     */
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("You should write 3 arguments!");
            return;
        }

        String userOperation = args[1];
        double leftOperand;
        double rightOperand;
        double result;

        try {
            leftOperand = Double.parseDouble(args[0]);
            rightOperand = Double.parseDouble(args[2]);
        } catch (NumberFormatException e) {
            System.out.println("Enter the correct number arguments in the format \"2 + 3\"");
            return;
        }

        ArithmeticOperations operation = new OperationFactory().getOperation(userOperation);
        if (operation == null) {
            System.out.println("You must use symbols \"+\", \"-\", \":\", \"x\" and Numbers !");
            return;
        }
        try {
            result = operation.execute(leftOperand, rightOperand);
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("Result: " + result);
    }
}

