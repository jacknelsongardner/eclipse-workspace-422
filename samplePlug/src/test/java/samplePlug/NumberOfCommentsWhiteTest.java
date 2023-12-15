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

public class NumberOfCommentsWhiteTest {

    
    DetailAST semiMockTooMany;
    DetailAST semiMockTooFew;
    DetailAST semiMockJustRight;
    DetailAST semiMockNull;
    
    DetailAST mockSinComm;
    DetailAST mockMulCommB;
    DetailAST mockMulCommE;
    
    DetailAST mockOperator;
    DetailAST mockNum;
    
    int[] comTokens = {
    		TokenTypes.SINGLE_LINE_COMMENT,
    		TokenTypes.BLOCK_COMMENT_BEGIN
    		};
    
    NumberOfCommentsCheck comCheck;
    
    
    public void setUp() {

    	// mocking having comments
    	mockSinComm = mock(DetailAST.class);
    	when(mockSinComm.getType()).thenReturn(TokenTypes.SINGLE_LINE_COMMENT);
    	
    	mockMulCommB = mock(DetailAST.class);
    	when(mockMulCommB.getType()).thenReturn(TokenTypes.BLOCK_COMMENT_BEGIN);
    	
    	mockMulCommE = mock(DetailAST.class);
    	when(mockMulCommE.getType()).thenReturn(TokenTypes.BLOCK_COMMENT_END);
    	
    	
    	
    	// mocking the number of ops found
    	mockNum = mock(DetailAST.class);
    	when(mockNum.getLineNo()).thenReturn(5);
    	
    	
        comCheck = spy(new NumberOfCommentsCheck());
       
    }
    
    @Test
    public void testGetAcceptableTokens()
    {
    	this.setUp();
    	
    	assertArrayEquals(comTokens,comCheck.getAcceptableTokens());
    	
    	verify(comCheck).getAcceptableTokens();
    }
    
    @Test
    public void testGetDefaultTokens()
    {
    	this.setUp();
    	
    	assertArrayEquals(comTokens,comCheck.getDefaultTokens());
    	
    	verify(comCheck).getDefaultTokens();
    }
    
    @Test
    public void testGetRequiredTokens()
    {
    	this.setUp();
    	
    	assertArrayEquals(comTokens,comCheck.getRequiredTokens());
    	
    	verify(comCheck).getRequiredTokens();
    }
    
    @Test
    public void testBeginTree()
    {
    	this.setUp();
    	
    	DetailAST blankast = mock(DetailAST.class);
    	
    	comCheck.beginTree(blankast);
    	
    	verify(comCheck).beginTree(blankast);
    	
    }
    
    @Test
    public void testVisitTokens()
    {
    	this.setUp();
    	
    	comCheck.visitToken(mockMulCommB);
    	comCheck.visitToken(mockSinComm);
    	
    	verify(comCheck).visitToken(mockMulCommB);
    	verify(comCheck).visitToken(mockSinComm);
    }
    
    @Test
    public void testFinishTree()
    {
    	this.setUp();
    	
    	doNothing().when(comCheck).log(anyInt(),anyString());
    	
    	comCheck.finishTree(mockNum);
    	
    	verify(comCheck).finishTree(mockNum);
    }
}
