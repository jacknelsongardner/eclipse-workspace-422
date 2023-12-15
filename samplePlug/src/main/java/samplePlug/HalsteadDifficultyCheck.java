package samplePlug;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import java.util.HashSet;
import java.util.Set;

public class HalsteadDifficultyCheck extends AbstractCheck {

    private static final String MSG_KEY = "comments";

    private int howManyOperandsFound = 0;
    private int howManyOperatorsFound = 0;
    private int howManyUniqueOpsFound = 0;  // Added initialization

    private Set<Integer> encounteredTokens = new HashSet<>();
    
    public int GetResult() {return (int)calculateHalsteadDifficulty(); }
    
    private int[] operandTokens = new int[]{TokenTypes.IDENT,
            TokenTypes.LITERAL_CHAR,
            TokenTypes.LITERAL_FLOAT,
            TokenTypes.LITERAL_INT,
            TokenTypes.LITERAL_LONG,
            TokenTypes.LITERAL_DOUBLE,
            TokenTypes.TYPE,
            TokenTypes.NUM_INT,
            TokenTypes.NUM_LONG,
            TokenTypes.NUM_FLOAT,
            TokenTypes.NUM_DOUBLE};

    private int[] operatorTokens = new int[]{TokenTypes.PLUS,
            TokenTypes.MINUS,
            TokenTypes.STAR,
            TokenTypes.DIV,
            TokenTypes.MOD,
            TokenTypes.BAND,
            TokenTypes.BOR,
            TokenTypes.BXOR,
            TokenTypes.LOR,
            TokenTypes.LAND,
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
            TokenTypes.BSR_ASSIGN};

    @Override
    public int[] getDefaultTokens() {
        return new int[]{TokenTypes.IDENT,
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
                TokenTypes.BSR_ASSIGN};
    }

    @Override
    public int[] getAcceptableTokens() {
        return getDefaultTokens();
    }

    @Override
    public int[] getRequiredTokens() {
        return getDefaultTokens();
    }

    @Override
    public void beginTree(DetailAST ast) {
    	this.howManyOperandsFound = 0;
    	this.howManyOperatorsFound = 0;
    	this.howManyUniqueOpsFound = 0;
    	
    }
    
    
    @Override
    public void visitToken(DetailAST ast) {
        boolean goodToken = false;

        for (int i = 0; i < operandTokens.length; i++) {
            if (ast.getType() == operandTokens[i]) {
                howManyOperandsFound++;
                goodToken = true;
                //log(ast, ast.getType() + " operand found in code!");
            }
        }

        for (int i = 0; i < operatorTokens.length; i++) {
            if (ast.getType() == operatorTokens[i]) {
                howManyOperatorsFound++;
                goodToken = true;
                //log(ast, ast.getType() + " operator found in code!");
            }
        }

        if (goodToken) {
            if (!encounteredTokens.contains(ast.getType())) {
                howManyUniqueOpsFound++;
            }
            encounteredTokens.add(ast.getType());
        }
    }

    @Override
    public void finishTree(DetailAST ast) {
        //log(ast, "Operators found in code: " + howManyOperatorsFound, howManyOperandsFound);
        //log(ast, "Operands found in code: " + howManyOperandsFound, howManyOperandsFound);

        double halsteadDifficulty = calculateHalsteadDifficulty();
        String message = "Halstead Difficulty: " + halsteadDifficulty;
        log((int) halsteadDifficulty, message);
    }

    private double calculateHalsteadDifficulty() {
        double n = howManyUniqueOpsFound / 2.0; // distinct operators
        double N = howManyOperandsFound; // distinct operands

        return n != 0 ? n * (howManyOperatorsFound / N) : 0;
    }
}
