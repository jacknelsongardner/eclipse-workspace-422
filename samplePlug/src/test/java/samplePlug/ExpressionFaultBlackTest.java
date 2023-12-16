package samplePlug;

public class ExpressionFaultBlackTest {

    public static void main(String[] args) {
        // Fault: Conditional Statement Mistaken as Expression
        if (true) {
            System.out.println("This is not an expression");
        }

        // Fault: Loop Construct Mistaken as Expression
        for (int i = 0; i < 5; i++) {
            System.out.println("Loop construct");
        }

        // Fault: Switch Statement Mistaken as Expression
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

        // Fault: Empty Block Mistaken as Expression
        {}

        // Fault: Comment Block Mistaken as Expression
        /*
         * This is a comment block
         * that is not an expression
         */

    }
}