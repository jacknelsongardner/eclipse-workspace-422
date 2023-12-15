package samplePlug;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class NumberOfLoopingStatementsCheck extends AbstractCheck {
	
private static final String MSG_KEY = "comments";
    
    private int howManyLoopingStatementsFound = 0;
    
    public int GetResult() { return howManyLoopingStatementsFound; }
    
    private int[] loopingTokens = new int[] {
    		TokenTypes.LITERAL_FOR,
    	    TokenTypes.LITERAL_WHILE,
    	    TokenTypes.LITERAL_DO,
    	    TokenTypes.LITERAL_BREAK,
    	    TokenTypes.LITERAL_CONTINUE,
    };
    
    
    // TOKEN METHODS
    @Override
    public int[] getDefaultTokens() 
    {
    	return loopingTokens;
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
    	this.howManyLoopingStatementsFound = 0;
    }

    // VISIT TOKEN ACTIONS
    @Override
    public void visitToken(DetailAST ast) 
    {
    		for(int i = 0; i < loopingTokens.length; i++)
    		{
    			if (ast.getType() == loopingTokens[i])
    			{
    				howManyLoopingStatementsFound++;
    	            ///log(ast, ast.getType() + " operand found in code!");
    			}
    		}
    		
    }
    
    @Override
    public void finishTree(DetailAST ast) {
    	
    	String message = "Looping statements found in code: " + howManyLoopingStatementsFound;
    	
        // Report the count as needed, or use it for other purposes
        log(howManyLoopingStatementsFound, message);
        
    }
	
}
