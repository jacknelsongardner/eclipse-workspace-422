package samplePlug;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class NumberOfLinesOfCommentsCheck extends AbstractCheck {
	
	private static final String MSG_KEY = "comments";
    
    private int howManyCommentLines = 0;
    
    public int GetResult() { return howManyCommentLines; }
    
    private int[] commentTokens = new int[] {
    		
    		TokenTypes.SINGLE_LINE_COMMENT,
    		TokenTypes.COMMENT_CONTENT
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
    	this.howManyCommentLines = 0;
    }
    
    // VISIT TOKEN ACTIONS
    @Override
    public void visitToken(DetailAST ast) 
    {
    		
    		if (ast.getType() == TokenTypes.SINGLE_LINE_COMMENT)
    		{
    			howManyCommentLines++;
                //log(ast, ast.getType() + "single line comment found in code");
    		}
    		else if (ast.getType() == TokenTypes.COMMENT_CONTENT)
    		{
    			int linesInComment = 0;
    			
    			boolean searchingForN = false;
    			
    			for(int i = 0; i < ast.getText().length(); i++)
    			{
    				char character = ast.getText().charAt(i);
    				
    				if (character == '\\' && !searchingForN)
    				{
    					searchingForN = true;
    				}
    				else if (character == 'n' && searchingForN)
    				{
    					linesInComment++;
    				}
    			}
    			
    			howManyCommentLines += linesInComment;
    			//log(ast, ast.getType() + "multi line comment found in code found");
    		}
    		
    		
    }
    
    @Override
    public void finishTree(DetailAST ast) {
    	String message = "Operands found in code: " + howManyCommentLines;
        // Report the count as needed, or use it for other purposes
        log(howManyCommentLines, message);
        
    }
	
}
