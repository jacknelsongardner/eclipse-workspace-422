package samplePlug;

public class OpManyBlackTest {
	public void manyOperatorsAndOperands() {
		// Arithmetic Operators
        int a = 5;                   // 2 operators (a, 5) and 1 operator (=)
        int b = 2;                   // 2 operators (b, 2) and 1 operator (=)
        int additionResult = a + b;  // 2 operators (a, b) and 1 operator (+)
        int subtractionResult = a - b; // 2 operators (a, b) and 1 operator (-)
        int multiplicationResult = a * b; // 2 operators (a, b) and 1 operator (*)
        int divisionResult = a / b; // 2 operators (a, b) and 1 operator (/)
        int moduloResult = a % b;   // 2 operators (a, b) and 1 operator (%)

        // Relational Operators 
        boolean isEqual = (a == b); // 2 operators (a, b) and 1 operator (==)
        boolean isNotEqual = (a != b); // 2 operators (a, b) and 1 operator (!=)

        // Logical Operators
        boolean logicalAnd = (a > 0 && b > 0); // 2 operators (a, b) and 1 operator (&&)
        boolean logicalOr = (a > 0 || b > 0); // 2 operators (a, b) and 1 operator (||)

        // Increment and Decrement Operators
        int incrementResult = a++;  // 1 operator (a++) and 1 operand (a)
        int decrementResult = b--;  // 1 operator (b--) and 1 operand (b)

        // Bitwise Operators
        int bitwiseAndResult = a & b; // 2 operators (a, b) and 1 operator (&)
        int bitwiseOrResult = a | b;  // 2 operators (a, b) and 1 operator (|)
        int bitwiseXorResult = a ^ b; // 2 operators (a, b) and 1 operator (^)
        int bitwiseComplementResult = ~a; // 1 operator (~) and 1 operand (a)

        // Shift Operators
        int leftShiftResult = a << 1; // 2 operators (a, 1) and 1 operator (<<)
        int rightShiftResult = a >> 1; // 2 operators (a, 1) and 1 operator (>>)

        // Conditional Operator (Ternary)
        int max = (a > b) ? a : b; // 3 operators (a, b, >) and 1 operator (?)

        // Unary Operators
        int unaryMinusResult = -a; // 1 operator (-) and 1 operand (a)
        boolean logicalNotResult = !(a > b); // 2 operators (a, b) and 1 operator (!)

        // Assignment Operator
        int assignedValue = (a > b) ? a : b; // 3 operators (a, b, >) and 1 operator (?)
    }
}