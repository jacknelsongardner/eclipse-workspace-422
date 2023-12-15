package samplePlug;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class NumberOfOperandsCheck extends AbstractCheck {
	
private static final String MSG_KEY = "comments";
    
    private int howManyOperandsFound = 0;
    private int howManyOperatorsFound = 0;
    
    public int GetResult() {return howManyOperandsFound; }
    
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
    
    // TOKEN METHODS
    @Override
    public int[] getDefaultTokens() 
    {
    	return operandTokens;
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
    }

    // VISIT TOKEN ACTIONS
    @Override
    public void visitToken(DetailAST ast) 
    {
    		for(int i = 0; i < operandTokens.length; i++)
    		{
    			if (ast.getType() == operandTokens[i])
    			{
    				howManyOperandsFound++;
    			}
    		}
    		
    }
   
    @Override
    public void finishTree(DetailAST ast) {
    	String message = "Operands found in code: " + howManyOperandsFound;
        // Report the count as needed, or use it for other purposes
        log(howManyOperandsFound, message);
        
    }
    
}
