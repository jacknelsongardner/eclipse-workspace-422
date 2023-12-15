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

public class NumberOfOperandsWhiteTest {

    int[] optokens = {
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
            TokenTypes.NUM_DOUBLE
    	};
    DetailAST mockOperand;
    DetailAST mockOperator;
    
    DetailAST mockNum;
    
    DetailAST blankast;
    
    NumberOfOperandsCheck opandCheck;
    
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
        opandCheck = spy(new NumberOfOperandsCheck());
        
        
        
    }
    
    
    @Test
    public void testBeginTree()
    {
    	this.setUp();
    	
    	DetailAST blankast = mock(DetailAST.class);
    	
    	opandCheck.beginTree(blankast);
    	
    	verify(opandCheck).beginTree(blankast);
    	
    }
    
    @Test
    public void testGetAcceptableTokens()
    {
    	this.setUp();
    	
    	assertArrayEquals(optokens,opandCheck.getAcceptableTokens());
    	
    	verify(opandCheck).getAcceptableTokens();
    	
    }
    
    @Test
    public void testGetDefaultTokens()
    {
    	this.setUp();
    	
    	assertArrayEquals(optokens,opandCheck.getDefaultTokens());

    	verify(opandCheck).getDefaultTokens();
    }
    
    @Test
    public void testGetRequiredTokens()
    {
    	this.setUp();
    	
    	assertArrayEquals(optokens,opandCheck.getRequiredTokens());

    	verify(opandCheck).getRequiredTokens();
    }
    
    @Test
    public void testVisitTokenOperand()
    {
    	this.setUp();
    	
    	opandCheck.visitToken(mockOperand);
    	verify(opandCheck).visitToken(mockOperand);
    	
    }
    
    
    @Test
    public void testFinishTree()
    {
    	this.setUp();
    	
    	doNothing().when(opandCheck).log(anyInt(),anyString());
    	
    	//halCheck.visitToken(mockOperator);
    	
    	opandCheck.finishTree(mockNum);
    	
    	verify(opandCheck).finishTree(mockNum);
    	
    	
    }
   
}
