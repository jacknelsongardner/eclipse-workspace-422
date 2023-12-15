# eclipse-workspace
 422 project

This is my 422 project. The plugins that I created are in the samplePlug folder, and the ones I want to use to demonstrate this first deliverable is the HalsteadCheck and the OperatorCheck, which check part A and part B of deliverable one respectively. Thankyou!

Assumptions regarding operators/operands:

This is a comprehensive list of the operands/operators searched for by the halstead checks, and operand/operator checks: 

        TokenTypes.LITERAL_CHAR,
        TokenTypes.LITERAL_FLOAT,
        TokenTypes.LITERAL_INT,
        TokenTypes.LITERAL_LONG,
        TokenTypes.LITERAL_DOUBLE,
        TokenTypes.TYPE,
        TokenTypes.NUM_INT,
        TokenTypes.NUM_LONG,
        TokenTypes.NUM_FLOAT,
        TokenTypes.NUM_DOUBLE,
        //TokenTypes.NUM_DECIMAL,
        //TokenTypes.NUM_HEX,
        //TokenTypes.NUM_BIN,
        //TokenTypes.NUM_FLOAT_OR_DOUBLE,
        //TokenTypes.NUM_LONG_OR_DOUBLE,
        //TokenTypes.NUM_INT_OR_LONG,
        //TokenTypes.NUM_INT_OR_FLOAT,
        TokenTypes.PLUS,
        TokenTypes.MINUS,
        TokenTypes.STAR,
        TokenTypes.DIV,
        TokenTypes.MOD,
        TokenTypes.BAND,
        TokenTypes.BOR,
        TokenTypes.BXOR,
        TokenTypes.LOR,
        TokenTypes.LAND,
        //TokenTypes.NOT,
   
        
        TokenTypes.QUESTION,
        TokenTypes.COLON,
        TokenTypes.EQUAL,
        TokenTypes.NOT_EQUAL,
        TokenTypes.GE,
        TokenTypes.LE,
        TokenTypes.LT,
        TokenTypes.GT,
        TokenTypes.SL,
        TokenTypes.SR,
        TokenTypes.BSR,
        TokenTypes.ASSIGN,
        TokenTypes.PLUS_ASSIGN,
        TokenTypes.MINUS_ASSIGN,
        TokenTypes.STAR_ASSIGN,
        TokenTypes.DIV_ASSIGN,
        TokenTypes.MOD_ASSIGN,
        TokenTypes.BAND_ASSIGN,
        TokenTypes.BOR_ASSIGN,
        TokenTypes.BXOR_ASSIGN,
        TokenTypes.SL_ASSIGN,
        TokenTypes.SR_ASSIGN,
        TokenTypes.BSR_ASSIGN

As you can tell, some were commented out. I kept these here to show which ones are no longer supported by checkstyle, but were attempted in implementation.

One of the assumptions made was that int A and B would NOT be unique operands from one another. Another assumption was that operands and operators would be counted without context (so if A and B do not have operators, they will still be considered operands if they are within the requirements above). This may result in halstead metrics being larger than expected.

Another assumption is in regards to some of the wider breadth of tokens we count as operators/operands. In this case, we count a wider variety because in the case of coding, more things could be thought of as ops if thought about in a way that is not strictly mathematical.

Assumptions made for looping checks:

The only loops that we check for are FOR loops, WHILE loops, and DO WHILE loops.

Assumptions made for lines of comments:

One of the assumptions we made was that /* */ themselves would not be accepted as lines of comments. This does not apply to SINGLE comments, since we will assume if the user used a single empty comment it was meant to be recognized as a line (for whatever reason). New lines will be recognized by the \\n (as specified in online java docs) from within the comment content. This may result in unusual results if user entered \\n themselves (which is unlikely)

A note about unusual test cases: some test cases, you may notice, will say SINGLE but will expect multiple results, in for example, some of the op tests. This is as intended. Certain elements were necesary to get and ended up being counted as operands because of our wide range of things counted as such. Therefor, in some cases, we were forced to compromise. Please know that when tests pass, it is because they are working as intended, even if their expected outputs may seem a bit unusual. 
