/**
 * @author Katsiaryna Stalchanka
 */
public class Hello {
    /**
     * Print "Hello, NAME" to console. The value(s) of name(s) is(are) taken from command line arguments.
     * @param args Command Line Params
     */
    public static void main(String[] args) {
        for (String name : args) {
            if (name.isEmpty()){
                System.out.println("Empty Argument. Try again.");
                break;
            }
            System.out.printf("Hello, %s\r\n", name);
        }
    }
}