package samplePlug;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class NumberOfCommentsCheck extends AbstractCheck {
	
    private static final String MSG_KEY = "comments";
    
    private int howManyCommentsFound = 0;
    private int maxCommentsAllowed = 5;
    
    public void setMaxComments(int maxCom)
    {
    	this.maxCommentsAllowed = maxCom;
    }

    // TOKEN METHODS
    @Override
    public int[] getDefaultTokens() 
    {
        return new int[] { TokenTypes.BLOCK_COMMENT_BEGIN, TokenTypes.SINGLE_LINE_COMMENT };
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
    	if (ast.getType() == TokenTypes.BLOCK_COMMENT_BEGIN || ast.getType() == TokenTypes.SINGLE_LINE_COMMENT) {
            // Increment the count
            howManyCommentsFound++;
        }
    }
    
    @Override
    public void finishTree(DetailAST ast) {
        // Report the count as needed, or use it for other purposes
        log(ast, "Comments found in code: ", howManyCommentsFound);
        log(ast, "Comments allowed in code: ", maxCommentsAllowed);
    }
    
}

