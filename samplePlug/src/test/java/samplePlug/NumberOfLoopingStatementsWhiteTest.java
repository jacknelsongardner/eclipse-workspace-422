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

public class NumberOfLoopingStatementsWhiteTest {

    
    DetailAST semiMockTooMany;
    DetailAST semiMockTooFew;
    DetailAST semiMockJustRight;
    DetailAST semiMockNull;
    
    DetailAST mockWhile;
    DetailAST mockOperator;
    DetailAST mockNum;
    
    int[] loopTokens = new int[] {
    		TokenTypes.LITERAL_FOR,
    	    TokenTypes.LITERAL_WHILE,
    	    TokenTypes.LITERAL_DO,
    	    TokenTypes.LITERAL_BREAK,
    	    TokenTypes.LITERAL_CONTINUE,
    };
    
    NumberOfLoopingStatementsCheck loopCheck;
    
    @BeforeEach
    public void setUp() {

    	// mocking having operators
    	mockWhile = mock(DetailAST.class);
    	when(mockWhile.getType()).thenReturn(TokenTypes.LITERAL_WHILE);
    	
    	// mocking the number of ops found
    	mockNum = mock(DetailAST.class);
    	when(mockNum.getLineNo()).thenReturn(5);
    	
        loopCheck = spy(new NumberOfLoopingStatementsCheck());
       
    }
    
    @Test
    public void testGetResult()
    {
    	this.setUp();
    	
    	this.loopCheck.GetResult();
    	
    	verify(this.loopCheck).GetResult();
    	
    }
    
    @Test
    public void testBeginTree()
    {
    	this.setUp();
    	
    	DetailAST blankast = mock(DetailAST.class);
    	
    	loopCheck.beginTree(blankast);
    	    	
    	verify(loopCheck).beginTree(blankast);
    	
    }
    
    @Test
    public void testGetAcceptableTokens()
    {
    	this.setUp();
    	
    	assertArrayEquals(loopTokens,loopCheck.getAcceptableTokens());
    	
    	verify(loopCheck).getAcceptableTokens();
    }
    
    @Test
    public void testGetDefaultTokens()
    {
    	this.setUp();
    	
    	assertArrayEquals(loopTokens,loopCheck.getDefaultTokens());
    	
    	verify(loopCheck).getDefaultTokens();
    }
    
    @Test
    public void testGetRequiredTokens()
    {
    	this.setUp();
    	
    	assertArrayEquals(loopTokens,loopCheck.getRequiredTokens());
    	
    	verify(loopCheck).getRequiredTokens();
    }
    
    @Test
    public void testVisitTokenOperator()
    {
    	this.setUp();
    	
    	loopCheck.visitToken(mockWhile);
    	
    	verify(loopCheck).visitToken(mockWhile);
    }
    
    @Test
    public void testFinishTree()
    {
    	this.setUp();
    	doNothing().when(loopCheck).log(anyInt(),anyString());

    	
    	loopCheck.finishTree(mockNum);
    	
    	verify(loopCheck).finishTree(mockNum);
    }
}
