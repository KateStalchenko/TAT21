package main;

/**
 * Is used for parsing string array to double array
 */
public class NumberParser {
    /**
     * Parses string array of arguments to double array if it's possible
     * @param args
     * @return double array of arguments
     * @throws ArrayIndexOutOfBoundsException if the length of array doesn't equal to 3
     * @throws IllegalArgumentException if string array contains incorrect values which it is impossible to parse to double
     */
    public double[] parseStringArrayToDoubleArray(String[] args) {
        double[] array = new double[3];
        if (args.length != 3) {
            throw new ArrayIndexOutOfBoundsException("You should write 3 arguments!");
        }

        for (int i = 0; i < array.length; i++) {
            try {
                array[i] = Double.parseDouble(args[i]);
            } catch (Exception e) {
                throw new IllegalArgumentException("Incorrect value!\r\n" + e.getMessage(), e);
            }
        }
        return array;
    }
}
