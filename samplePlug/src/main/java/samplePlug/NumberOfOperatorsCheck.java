package samplePlug;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class NumberOfOperatorsCheck extends AbstractCheck {
	
private static final String MSG_KEY = "comments";
    
    private int howManyOperatorsFound = 0;
    
    private int[] operatorTokens = new int[] {
    		TokenTypes.PLUS,
    	    TokenTypes.MINUS,
    	    TokenTypes.STAR,
    	    TokenTypes.DIV,
    	    TokenTypes.MOD,
    	    TokenTypes.INC,
    	    TokenTypes.DEC,
    	    TokenTypes.EQUAL,
    	    TokenTypes.NOT_EQUAL,
    	    TokenTypes.LT,
    	    TokenTypes.GT,
    	    TokenTypes.LE,
    	    TokenTypes.GE,
    	    TokenTypes.LOR,
    	    TokenTypes.LAND,
    	    TokenTypes.BAND,
    	    TokenTypes.BOR,
    	    TokenTypes.BXOR,
    	    TokenTypes.BNOT,
    	    TokenTypes.SL,
    	    TokenTypes.SR,
    	    //TokenTypes.USR,
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
    	    //TokenTypes.USR_ASSIGN,
    	    //TokenTypes.INSTANCEOF
    };
    
    
    // TOKEN METHODS
    @Override
    public int[] getDefaultTokens() 
    {
    	return operatorTokens;
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

    // VISIT TOKEN ACTIONS
    @Override
    public void visitToken(DetailAST ast) 
    {
    		for(int i = 0; i < operatorTokens.length; i++)
    		{
    			if (ast.getType() == operatorTokens[i])
    			{
    				howManyOperatorsFound++;
    	            log(ast, ast.getType() + " operand found in code!");
    			}
    		}
    		
    }
    
    @Override
    public void finishTree(DetailAST ast) {
    	
        // Report the count as needed, or use it for other purposes
        log(ast, "Operands found in code: " + howManyOperatorsFound, howManyOperatorsFound);
        
    }
	
}
