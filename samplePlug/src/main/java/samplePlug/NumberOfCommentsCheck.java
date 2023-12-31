package samplePlug;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class NumberOfCommentsCheck extends AbstractCheck {
	
private static final String MSG_KEY = "comments";
    
    private int howManyCommentsCheck = 0;
    
    public int GetResult() {return howManyCommentsCheck; }
    
    private int[] commentTokens = new int[] {
    		
    		TokenTypes.SINGLE_LINE_COMMENT,
    		TokenTypes.BLOCK_COMMENT_BEGIN
    };
    
    
    // TOKEN METHODS
    @Override
    public int[] getDefaultTokens() 
    {
    	return commentTokens;
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
    	this.howManyCommentsCheck = 0;
    }
    
    // VISIT TOKEN ACTIONS
    @Override
    public void visitToken(DetailAST ast) 
    {
    		for(int i = 0; i < commentTokens.length; i++)
    		{
    			if (ast.getType() == commentTokens[i])
    			{
    				howManyCommentsCheck++;
    	            //log(ast, ast.getType() + " operand found in code!");
    			}
    		}
    		
    }
    
    @Override
    public void finishTree(DetailAST ast) {
    	
    	String message = "Comments found in code: " + howManyCommentsCheck;
    	
        // Report the count as needed, or use it for other purposes
        log( howManyCommentsCheck, message);
        
    }
	
}
