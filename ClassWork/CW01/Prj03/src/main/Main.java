package main;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("You should write 2 Arguments in the format \"2 5\"");
            return;
        }

        try {
            if (Double.parseDouble(args[0]) <= 0 && Double.parseDouble(args[0]) >= 0) {
                System.out.println("Numbers cannot be less than 0 or equal to zero.");
                return;
            }
        } catch (Exception e) {
            System.out.println("Your Arguments should be Numbers. Please, enter your arguments in the format \"2 5\"!");
            return;
        }

        Rectangle rectangle = new Rectangle(Double.parseDouble(args[0]), Double.parseDouble(args[1]));
        double area = Math.round(rectangle.getArea());
    }
}
