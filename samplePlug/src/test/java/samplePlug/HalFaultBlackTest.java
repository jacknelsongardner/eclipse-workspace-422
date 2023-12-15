
package samplePlug;


public class HalFaultBlackTest {

    public static void main(String[] args) {
        // Fault 1: Duplicate Operand
        int a = 5;
        int b = 5;

        // Fault 2: Duplicate Operator
        int result = a + b + a + b;

        // Fault 3: Duplicate Operator and Operand
        int duplicateExpression = a + b + a + b;

        // Fault 4: Single Operand with Multiple Occurrences
        int duplicateOperand = a + a + a + a;

        // Fault 5: Single Operator with Multiple Occurrences
        int duplicateOperator = a + b + a + b;

        // Fault 6: Complex Expression with Duplicates
        int complexExpression = (a + b) * (a - b) / (a + b);

        // Fault 7: Multiple Expressions with Shared Operand
        int sharedOperand1 = a + b;
        int sharedOperand2 = b + a;

        // Fault 8: Multiple Expressions with Shared Operator
        int sharedOperator1 = a + b;
        int sharedOperator2 = a - b;

        // Fault 9: Duplicate Operand in Different Types
        double duplicateOperandDouble = 5.0;
        float duplicateOperandFloat = 5.0f;

        // Fault 10: Duplicate Operator in Different Types
        double duplicateOperatorDouble = a + b;
        float duplicateOperatorFloat = a + b;
    }
}