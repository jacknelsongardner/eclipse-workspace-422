package samplePlug;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.api.*;

public class SemiColonsCheck extends AbstractCheck {
    private static final String MSG_KEY = "maxSemicolons";
    private int maxSemicolons = 5; // Adjust this value to your desired maximum

    public void setMaxSemicolons(int maxSemicolons) {
        this.maxSemicolons = maxSemicolons;
    }

    @Override
    public int[] getDefaultTokens() {
        return new int[] { TokenTypes.SEMI };
    }

    @Override
    public int[] getAcceptableTokens() {
        return new int[] { TokenTypes.SEMI };
    }

    @Override
    public int[] getRequiredTokens() {
        return new int[] { TokenTypes.SEMI };
    }

    @Override
    public void visitToken(DetailAST ast) {
    	
        if (ast != null) {
            int line = ast.getLineNo();
            if (line > maxSemicolons) {
                log(ast, MSG_KEY, maxSemicolons);
            }
        }
        
    }
}

