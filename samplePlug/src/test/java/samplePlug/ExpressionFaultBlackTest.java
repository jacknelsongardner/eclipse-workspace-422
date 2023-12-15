package samplePlug;

public class ExpressionFaultBlackTest {

    public static void main(String[] args) {
        // Fault 1: Conditional Statement Mistaken as Expression
        if (true) {
            System.out.println("This is not an expression");
        }

        // Fault 2: Method Invocation Without Assignment
        calculateSum(2, 3);

        // Fault 3: Loop Construct Mistaken as Expression
        for (int i = 0; i < 5; i++) {
            System.out.println("Loop construct");
        }

        // Fault 5: Switch Statement Mistaken as Expression
        int x = 0;

        switch (x) {
            case 1:
                // Code block
                break;
            case 2:
                // Code block
                break;
            default:
                // Code block
        }

        // Fault 6: Lambda Expression Without Assignment
        Runnable runnable = () -> System.out.println("Lambda expression");

        // Fault 8: Empty Block Mistaken as Expression
        {}

        // Fault 9: Comment Block Mistaken as Expression
        /*
         * This is a comment block
         * that is not an expression
         */

    }

    private static int calculateSum(int x, int y) {
        return x + y;
    }
}