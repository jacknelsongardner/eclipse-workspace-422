package samplePlug;

import samplePlug.HalsteadLengthCheck;
import samplePlug.NumberOfOperatorsCheck;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import org.mockito.MockedStatic;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
//import static org.mockito.Mockito.*;

// TODO:  import MOCKITO here

public class NumberOfExpressionsWhiteTest {

    
    DetailAST semiMockTooMany;
    DetailAST semiMockTooFew;
    DetailAST semiMockJustRight;
    DetailAST semiMockNull;
    
    DetailAST mockExpr;
    DetailAST mockOperator;
    DetailAST mockNum;
    
    int[] exprTokens = new int[] {
    		
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
    	    TokenTypes.LITERAL_FALSE,
    	    TokenTypes.EXPR
    };
    
    NumberOfExpressionsCheck exprCheck;
    
    @BeforeEach
    public void setUp() {

    	// mocking having operators
    	mockExpr = mock(DetailAST.class);
    	when(mockExpr.getType()).thenReturn(TokenTypes.EXPR);
    	
    	// mocking the number of ops found
    	mockNum = mock(DetailAST.class);
    	when(mockNum.getLineNo()).thenReturn(5);
    	
        exprCheck = spy(new NumberOfExpressionsCheck());
       
    }
    
    @Test
    public void testBeginTree()
    {
    	this.setUp();
    	
    	DetailAST blankast = mock(DetailAST.class);
    	
    	exprCheck.beginTree(blankast);
    	
    	verify(exprCheck).beginTree(blankast);
    	
    }
    
    @Test
    public void testGetAcceptableTokens()
    {
    	this.setUp();
    	
    	assertArrayEquals(this.exprTokens,this.exprCheck.getAcceptableTokens());
    	
    	verify(exprCheck).getAcceptableTokens();
    }
    
    @Test
    public void testGetDefaultTokens()
    {
    	this.setUp();
    	
    	assertArrayEquals(this.exprTokens,this.exprCheck.getAcceptableTokens());
    	
    	verify(exprCheck).getDefaultTokens();
    }
    
    @Test
    public void testGetRequiredTokens()
    {
    	this.setUp();
    	
    	assertArrayEquals(this.exprTokens,this.exprCheck.getRequiredTokens());
    	
    	verify(exprCheck).getRequiredTokens();
    }
    
    @Test
    public void testVisitTokenOperator()
    {
    	this.setUp();
    	
    	exprCheck.visitToken(mockExpr);
    	
    	verify(exprCheck).visitToken(mockExpr);
    }
    
    @Test
    public void testFinishTree()
    {
    	this.setUp();
    	
    	doNothing().when(this.exprCheck).log(anyInt(),anyString());
    	
    	exprCheck.finishTree(mockNum);
    	
    	verify(exprCheck).finishTree(mockNum);
    }
}
