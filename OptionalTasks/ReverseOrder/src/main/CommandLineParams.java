package main;

public class CommandLineParams {
    /**
     * Entry point to the program
     * @param args Command Line Params
     */
    public static void main(String[] args) {
        for (int i = args.length-1; i >= 0; i--) {
            System.out.println("Argument " + (args.length-1-i) + " = " + args[i]);
        }
    }
}