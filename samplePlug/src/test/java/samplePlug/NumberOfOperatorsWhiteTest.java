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

public class NumberOfOperatorsWhiteTest {

    
    DetailAST semiMockTooMany;
    DetailAST semiMockTooFew;
    DetailAST semiMockJustRight;
    DetailAST semiMockNull;
    
    DetailAST mockOperand;
    DetailAST mockOperator;
    DetailAST mockNum;
    
    int[] opTokens = new int[] {
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
    
    NumberOfOperatorsCheck optorCheck;
    
    @BeforeEach
    public void setUp() {

    	// mocking having operators
    	mockOperand = mock(DetailAST.class);
    	when(mockOperand.getType()).thenReturn(TokenTypes.IDENT);
    	
    	
    	// mocking having operands
    	mockOperator = mock(DetailAST.class);
    	when(mockOperator.getType()).thenReturn(TokenTypes.PLUS);
    	
    	// mocking the number of ops found
    	mockNum = mock(DetailAST.class);
    	when(mockOperator.getLineNo()).thenReturn(5);
    	
    	
        optorCheck = spy(new NumberOfOperatorsCheck());
       
    }
    
    @Test
    public void testGetResult()
    {
    	this.setUp();
    	
    	this.optorCheck.GetResult();
    	
    	verify(this.optorCheck).GetResult();
    	
    }
    
    @Test
    public void testBeginTree()
    {
    	this.setUp();
    	
    	DetailAST blankast = mock(DetailAST.class);
    	
    	optorCheck.beginTree(blankast);
    	
    	verify(optorCheck).beginTree(blankast);
    	
    }
    
    @Test
    public void testGetAcceptableTokens()
    {
    	this.setUp();
    	
    	assertArrayEquals(opTokens,optorCheck.getAcceptableTokens());
    	
    	verify(optorCheck).getAcceptableTokens();
    }
    
    @Test
    public void testGetDefaultTokens()
    {
    	this.setUp();
    	
    	assertArrayEquals(opTokens,optorCheck.getDefaultTokens());
    	
    	verify(optorCheck).getDefaultTokens();
    }
    
    @Test
    public void testGetRequiredTokens()
    {
    	this.setUp();
    	
    	assertArrayEquals(opTokens,optorCheck.getRequiredTokens());
    	
    	verify(optorCheck).getRequiredTokens();
    }
    
    @Test
    public void testVisitTokenOperator()
    {
    	this.setUp();
    	
    	optorCheck.visitToken(mockOperator);
    	
    	verify(optorCheck).visitToken(mockOperator);
    }
    
    @Test
    public void testFinishTree()
    {
    	this.setUp();
    	doNothing().when(optorCheck).log(anyInt(),anyString());

    	
    	optorCheck.finishTree(mockNum);
    	
    	verify(optorCheck).finishTree(mockNum);
    }
}
