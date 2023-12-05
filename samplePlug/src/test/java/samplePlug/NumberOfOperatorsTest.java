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
import static org.mockito.Mockito.*;
//import static org.mockito.Mockito.*;

// TODO:  import MOCKITO here

public class NumberOfOperatorsTest {

    
    DetailAST semiMockTooMany;
    DetailAST semiMockTooFew;
    DetailAST semiMockJustRight;
    DetailAST semiMockNull;
    
    DetailAST mockOperand;
    DetailAST mockOperator;
    DetailAST mockNum;
    
    
    NumberOfOperatorsCheck opCheck;
    
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
    	
    	
        opCheck = new NumberOfOperatorsCheck();
       
    }
    
    @Test
    public void testGetAcceptableTokens()
    {
    	this.setUp();
    	
    	opCheck.getAcceptableTokens();
    	
    	verify(opCheck).getAcceptableTokens();
    }
    
    @Test
    public void testGetDefaultTokens()
    {
    	this.setUp();
    	
    	opCheck.getDefaultTokens();
    	
    	verify(opCheck).getDefaultTokens();
    }
    
    @Test
    public void testGetRequiredTokens()
    {
    	this.setUp();
    	
    	opCheck.getRequiredTokens();
    	
    	verify(opCheck).getRequiredTokens();
    }
    
    @Test
    public void testVisitTokenOperator()
    {
    	this.setUp();
    	
    	opCheck.visitToken(mockOperator);
    	
    	verify(opCheck).visitToken(mockOperator);
    }
    
    @Test
    public void testFinishTree()
    {
    	this.setUp();
    	
    	opCheck.finishTree(mockNum);
    	
    	verify(opCheck).finishTree(mockNum);
    }
}
