package samplePlug;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import java.util.Set;
import java.util.HashSet;

public class HalsteadVocabularyCheck extends AbstractCheck {
	
private static final String MSG_KEY = "comments";
    
    private int howManyOperandsFound = 0;
    private int howManyOperatorsFound = 0;
    
    private int howManyUniqueOpsFound = 0;
    
    
    private Set<Integer> encounteredTokens = new HashSet<>();
    
    public int GetResult() {
        return howManyUniqueOpsFound;
    }
    
    private int[] operandTokens = new int[] {
        TokenTypes.IDENT,
        //TokenTypes.LITERAL_STRING,
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
    };
    
    private int[] operatorTokens = new int[] {
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
    };
    
    
    // TOKEN METHODS
    @Override
    public int[] getDefaultTokens() 
    {
    	return new int[] { TokenTypes.IDENT,
    	        //TokenTypes.LITERAL_STRING,
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
    	        TokenTypes.BSR_ASSIGN };
    }

    @Override
    public int[] getAcceptableTokens() 
    {
        return getDefaultTokens();
    }

    @Override
    public int[] getRequiredTokens() 
    {
        return getDefaultTokens();
    }

    @Override
    public void beginTree(DetailAST ast) {
    	this.howManyOperandsFound = 0;
    	this.howManyOperatorsFound = 0;
    	this.howManyUniqueOpsFound = 0;
    	
    }
    
    // VISIT TOKEN ACTIONS
    @Override
    public void visitToken(DetailAST ast) 
    {
    	/*
    	if (Arrays.asList(operandTokens).contains(ast.getType())) {
            // Increment the count
            howManyOperandsFound++;
            log(ast, "Operators found in code!", howManyOperandsFound);
        }
    	else if (ast.getType() == TokenTypes.PLUS) {
            // Increment the count
            howManyOperandsFound++;
            log(ast, "Plus operator found in code!");
        }
    	else
    	{
    	*/
    		boolean goodToken = false;
    	
    		for(int i = 0; i < operandTokens.length; i++)
    		{
    			if (ast.getType() == operandTokens[i])
    			{
    				howManyOperandsFound++;
    				goodToken = true;
    	            //log(ast, ast.getType() + " operand found in code!");
    			}
    		}
    		
    		for(int i = 0; i < operatorTokens.length; i++)
    		{
    			if (ast.getType() == operatorTokens[i])
    			{
    				howManyOperatorsFound++;
    				goodToken = true;
    	            //log(ast, ast.getType() + " operator found in code!");
    			}
    		}
    		
    		if (goodToken == true)
    		{
    			if (!encounteredTokens.contains(ast.getType()))
    			{
    				howManyUniqueOpsFound++;
    			}
    		}

    }
    
    @Override
    public void finishTree(DetailAST ast) {
    	
    	String operator_msg = "Unique ops found in code: " + this.howManyUniqueOpsFound;
    	
        // Report the count as needed, or use it for other purposes
        log(this.howManyUniqueOpsFound, operator_msg);
        
    }
	
}
