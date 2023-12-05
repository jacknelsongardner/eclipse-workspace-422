package samplePlug;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class NumberOfExpressionsCheck extends AbstractCheck {
	
private static final String MSG_KEY = "comments";
    
    private int howManyExpressionsFound = 0;
    
    private int[] expressionTokens = new int[] {
    		
    		TokenTypes.LITERAL_ASSERT,
    	    TokenTypes.LITERAL_NEW,
    	    TokenTypes.LITERAL_INSTANCEOF,
    	    TokenTypes.LITERAL_THIS,
    	    TokenTypes.LITERAL_SUPER,
    	    TokenTypes.IDENT,
    	    TokenTypes.STRING_LITERAL,
    	    TokenTypes.CHAR_LITERAL,
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
    	    TokenTypes.LNOT,
    	    TokenTypes.BNOT,
    	    TokenTypes.UNARY_MINUS,
    	    TokenTypes.UNARY_PLUS,
    	    TokenTypes.POST_INC,
    	    TokenTypes.POST_DEC,
    	    TokenTypes.DOT,
    	    TokenTypes.METHOD_REF,
    	    TokenTypes.ARRAY_DECLARATOR,
    	    TokenTypes.INDEX_OP,
    	    TokenTypes.METHOD_CALL,
    	    TokenTypes.TYPECAST,
    	    TokenTypes.LITERAL_INSTANCEOF,
    	    TokenTypes.STATIC_IMPORT,
    	    TokenTypes.ENUM_CONSTANT_DEF,
    	    TokenTypes.LAMBDA,
    	    //TokenTypes.CAST,
    	    //TokenTypes.TERNARY,
    	    TokenTypes.COLON,
    	    TokenTypes.QUESTION,
    	    TokenTypes.LAMBDA,
    	    //TokenTypes.LAMBDA_PARAMETERS,
    	    //TokenTypes.LAMBDA_EXPRESSION,
    	    TokenTypes.METHOD_CALL,
    	    TokenTypes.CTOR_CALL,
    	    TokenTypes.CTOR_DEF,
    	    TokenTypes.CLASS_DEF,
    	    TokenTypes.INTERFACE_DEF,
    	    TokenTypes.ANNOTATION_DEF,
    	    TokenTypes.ENUM_DEF,
    	    TokenTypes.INTERFACE_DEF,
    	    TokenTypes.PACKAGE_DEF,
    	    TokenTypes.IMPORT,
    	    TokenTypes.ARRAY_INIT,
    	    TokenTypes.LITERAL_NULL,
    	    TokenTypes.INSTANCE_INIT,
    	    TokenTypes.LITERAL_SYNCHRONIZED,
    	    TokenTypes.LITERAL_CLASS,
    	    //TokenTypes.LITERAL_EXTENDS,
    	    //TokenTypes.LITERAL_IMPLEMENTS,
    	    TokenTypes.LITERAL_THROWS,
    	    //TokenTypes.LITERAL_IMPORT,
    	    TokenTypes.LITERAL_STATIC,
    	    TokenTypes.LITERAL_SYNCHRONIZED,
    	    //TokenTypes.LITERAL_FINAL,
    	    //TokenTypes.LITERAL_ABSTRACT,
    	    TokenTypes.LITERAL_NATIVE,
    	    TokenTypes.LITERAL_TRANSIENT,
    	    TokenTypes.LITERAL_VOLATILE,
    	    TokenTypes.LITERAL_PUBLIC,
    	    TokenTypes.LITERAL_PROTECTED,
    	    TokenTypes.LITERAL_PRIVATE,
    	    //TokenTypes.LITERAL_STRICTFP,
    	    TokenTypes.LITERAL_DEFAULT,
    	    TokenTypes.TYPE_ARGUMENT,
    	    TokenTypes.TYPE_PARAMETER,
    	    TokenTypes.ANNOTATION_DEF,
    	    TokenTypes.ANNOTATION,
    	    TokenTypes.ANNOTATION_MEMBER_VALUE_PAIR,
    	    TokenTypes.ANNOTATION_ARRAY_INIT,
    	    TokenTypes.ANNOTATION_MEMBER_VALUE_PAIR,
    	    //TokenTypes.LITERAL_IMPORT,
    	    //TokenTypes.LITERAL_PACKAGE,
    	    TokenTypes.STATIC_IMPORT,
    	    TokenTypes.WILDCARD_TYPE,
    	    TokenTypes.LITERAL_NULL,
    	    TokenTypes.LITERAL_TRUE,
    	    TokenTypes.LITERAL_FALSE
    };
    
    
    // TOKEN METHODS
    @Override
    public int[] getDefaultTokens() 
    {
    	return expressionTokens;
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
    		for(int i = 0; i < expressionTokens.length; i++)
    		{
    			if (ast.getType() == expressionTokens[i])
    			{
    				howManyExpressionsFound++;
    	            log(ast, ast.getType() + " operand found in code!");
    			}
    		}
    		
    }
    
    @Override
    public void finishTree(DetailAST ast) {
    	
        // Report the count as needed, or use it for other purposes
        log(ast, "Operands found in code: " + howManyExpressionsFound, howManyExpressionsFound);
        
    }
	
}
