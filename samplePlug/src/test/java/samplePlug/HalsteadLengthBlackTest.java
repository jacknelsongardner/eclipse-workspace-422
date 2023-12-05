package samplePlug;

import samplePlug.HalsteadLengthCheck;

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

public class HalsteadLengthBlackTest {
	 
    DetailAST mockOperand;
    DetailAST mockOperator;
    
    DetailAST mockNum;
    
    DetailAST blankast;
    
    HalsteadLengthCheck halsteadLengthCheck;
    
    int[] optokens = { TokenTypes.IDENT,
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
        TokenTypes.PLUS,
        TokenTypes.MINUS,
        TokenTypes.STAR,
        TokenTypes.DIV,
        TokenTypes.MOD,
        TokenTypes.BAND,
        TokenTypes.BOR,
        TokenTypes.BXOR,
        TokenTypes.LOR,
        TokenTypes.LAND,
        //TokenTypes.NOT,
   
        
        TokenTypes.QUESTION,
        TokenTypes.COLON,
        TokenTypes.EQUAL,
        TokenTypes.NOT_EQUAL,
        TokenTypes.GE,
        TokenTypes.LE,
        TokenTypes.LT,
        TokenTypes.GT,
        TokenTypes.SL,
        TokenTypes.SR,
        TokenTypes.BSR,
        TokenTypes.ASSIGN,
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
        TokenTypes.BSR_ASSIGN };
    
    @BeforeEach
    public void setUp() {
    	
    	// mocking having operators
    	mockOperand = mock(DetailAST.class);
    	when(mockOperand.getType()).thenReturn(TokenTypes.IDENT);
    	
    	// mocking having operands
    	mockOperator = mock(DetailAST.class);
    	when(mockOperator.getType()).thenReturn(TokenTypes.PLUS);
    	
    	mockNum = mock(DetailAST.class);
    	when(mockOperator.getLineNo()).thenReturn(5);
    	
    	// mocking halchecker
        halsteadLengthCheck = spy(new HalsteadLengthCheck());
        halsteadLengthCheck.setMax(1);
        
        
    }
    
    
    @Test
    public void testBeginTree()
    {
    	this.setUp();
    	
    	DetailAST blankast = mock(DetailAST.class);
    	
    	halsteadLengthCheck.beginTree(blankast);
    	
    	verify(halsteadLengthCheck).beginTree(blankast);
    	
    }
    
    @Test
    public void testGetAcceptableTokens()
    {
    	this.setUp();
    	
    	assertArrayEquals(optokens,halsteadLengthCheck.getAcceptableTokens());
    	
    }
    
    @Test
    public void testGetDefaultTokens()
    {
    	this.setUp();
    	
    	assertArrayEquals(optokens,halsteadLengthCheck.getDefaultTokens());
    }
    
    @Test
    public void testGetRequiredTokens()
    {
    	this.setUp();
    	
    	assertArrayEquals(optokens,halsteadLengthCheck.getRequiredTokens());
    }
    
    @Test
    public void testVisitTokenOperand()
    {
    	this.setUp();
    	halsteadLengthCheck.visitToken(blankast);
    	
    	verify(halsteadLengthCheck).visitToken(mockOperand);
    	
    }
    
    @Test
    public void testVisitTokenIncrementsOperandsAndLogs() {
        DetailAST mockAst = mock(DetailAST.class);
        halsteadLengthCheck.visitToken(mockAst);

        assertEquals(1, halsteadLengthCheck.getOperandsFound());

        // Verify that the log method was called with the expected arguments
        verify(halsteadLengthCheck).log(anyInt(), anyString(), eq(halsteadLengthCheck.getOperandsFound()));
    }

    @Test
    public void testVisitTokenIncrementsOperatorsAndLogs() {
        DetailAST mockAst = mock(DetailAST.class);
        halsteadLengthCheck.visitToken(mockAst);

        assertEquals(1, halsteadLengthCheck.getOperatorsFound());

        // Verify that the log method was called with the expected arguments
        verify(halsteadLengthCheck).log(anyInt(), anyString(), eq(halsteadLengthCheck.getOperatorsFound()));
    }

    @Test
    public void testFinishTreeLogsHalsteadLengthIfExceedsMax() {
        DetailAST mockAst = mock(DetailAST.class);

        // Set a specific maxHalsteadLength for testing
        halsteadLengthCheck.setMax(10);

        // Set operands and operators count to trigger the log
        halsteadLengthCheck.visitToken(mockAst);
        halsteadLengthCheck.visitToken(mockAst);

        // Verify that the log method was called with the expected arguments
        verify(halsteadLengthCheck).log(anyInt(), anyString(), eq(halsteadLengthCheck.getOperandsFound() * halsteadLengthCheck.getOperatorsFound()));
    }

    @Test
    public void testFinishTreeDoesNotLogIfHalsteadLengthDoesNotExceedMax() {
        DetailAST mockAst = mock(DetailAST.class);

        // Set a specific maxHalsteadLength for testing
        halsteadLengthCheck.setMax(100);

        // Set operands and operators count that do not exceed maxHalsteadLength
        halsteadLengthCheck.visitToken(mockAst);

        // Verify that the log method was not called
        verify(halsteadLengthCheck, never()).log(any(), anyString(), anyInt());
    }
}
