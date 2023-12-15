package samplePlug;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.DefaultContext;
import com.puppycrawl.tools.checkstyle.JavaParser;
import com.puppycrawl.tools.checkstyle.JavaParser.Options;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

class BlackBoxTestEngine {


	String workingDir = System.getProperty("user.dir");
	String filePath = workingDir + "/src/test/java/samplePlug/";
	
	// ASTs
	DetailAST opfaultroot;
	DetailAST opsingleroot;
	DetailAST opmanyroot;
			
	DetailAST emptyroot;
	DetailAST emptyroot2;
	
	DetailAST commbothroot;
	DetailAST commmultiroot;
	DetailAST commsingleroot;
	DetailAST commfaultroot;
	
	DetailAST loopforroot;
	DetailAST loopwhileroot;
	DetailAST loopdoroot;
	DetailAST loopnestedroot;
	DetailAST loopfaultroot;
	
	DetailAST exprfaultroot;
	DetailAST exprroot;
	
	DetailAST halfaultroot;
	
	
	// Checkers
	NumberOfOperatorsCheck optorcheck;
	NumberOfOperandsCheck opandcheck;
	
	NumberOfCommentsCheck comcheck;
	NumberOfLinesOfCommentsCheck commlinecheck;
	
	NumberOfLoopingStatementsCheck loopcheck;
	NumberOfExpressionsCheck exprcheck;
	
	HalsteadLengthCheck hallencheck;
	HalsteadVolumeCheck halvolcheck;
	HalsteadVocabularyCheck halvoccheck;
	HalsteadEffortCheck haleffcheck;
	HalsteadDifficultyCheck haldiffcheck;
	
	
	
	public static DetailAST parseFileToAST(File file) throws IOException, CheckstyleException {
        
		FileText ft = new FileText(file,"UTF-8");
		
		//FileContents fc = new FileContents(ft);
		
		DetailAST root = JavaParser.parseFileText(ft, Options.WITH_COMMENTS);
		
		return root;
    }
	
	void setup() throws IOException, CheckstyleException {

		File emptyfile = new File(filePath + "EmptyBlackTest.java");
		
		File opsinglefile = new File(filePath + "OpSingleBlackTest.java");
		File opmanyfile = new File(filePath + "OpManyBlackTest.java");
		File opfaultfile = new File(filePath + "OpFaultBlackTest.java");
		
		
		File commbothfile = new File(filePath + "CommentBothBlackTest.java");
		File commfaultfile = new File(filePath + "CommentFaultBlackTest.java");
		File commsinglefile = new File(filePath + "CommentSingleBlackTest.java");
		File commmultifile = new File(filePath + "CommentMultiLineBlackTest.java");
		
		File loopforfile = new File(filePath + "LoopForBlackTest.java");
		File loopwhilefile = new File(filePath + "LoopWhileBlackTest.java");
		File loopdofile = new File(filePath + "LoopDoBlackTest.java");
		File loopfaultfile = new File(filePath + "LoopFaultBlackTest.java");
		File loopnestedfile = new File(filePath + "LoopNestedBlackTest.java");
		
		File exprfile = new File(filePath + "ExpressionBlackTest.java");
		File exprfaultfile = new File(filePath + "ExpressionFaultBlackTest.java");
		
		File halfaultfile = new File(filePath + "HalFaultBlackTest.java");
		
		
		emptyroot = parseFileToAST(emptyfile);
		
		commmultiroot = parseFileToAST(commbothfile);
		commsingleroot = parseFileToAST(commsinglefile);
		commbothroot = parseFileToAST(commbothfile);
		commfaultroot = parseFileToAST(commfaultfile);
		commbothroot = parseFileToAST(commbothfile);
		
		opfaultroot = parseFileToAST(opfaultfile);
		opmanyroot = parseFileToAST(opmanyfile);
		opsingleroot = parseFileToAST(opsinglefile);
		
		loopforroot = parseFileToAST(loopforfile);
		loopwhileroot = parseFileToAST(loopwhilefile);
		loopdoroot = parseFileToAST(loopdofile);
		loopfaultroot = parseFileToAST(loopfaultfile);
		loopnestedroot = parseFileToAST(loopnestedfile);

		exprroot = parseFileToAST(exprfile);
		exprfaultroot = parseFileToAST(exprfaultfile);
		
		halfaultroot = parseFileToAST(halfaultfile);
		
		// Checkers
		this.optorcheck = new NumberOfOperatorsCheck();
		this.opandcheck = new NumberOfOperandsCheck();
		
		this.exprcheck = new NumberOfExpressionsCheck();
		this.loopcheck = new NumberOfLoopingStatementsCheck();
		
		this.comcheck = new NumberOfCommentsCheck();
		this.commlinecheck = new NumberOfLinesOfCommentsCheck();
		
		this.haleffcheck = new HalsteadEffortCheck();
		this.haldiffcheck = new HalsteadDifficultyCheck();
		this.hallencheck = new HalsteadLengthCheck();
		this.halvoccheck = new HalsteadVocabularyCheck();
		this.halvolcheck = new HalsteadVolumeCheck();
	}
	
	// Operator tests
	
	@Test
	void operatorsEmptyTest() throws IOException, CheckstyleException {
		this.setup();
		
		// Configure Checks
		optorcheck.configure(new DefaultConfiguration("Local"));
		
		// Contextualize Checks
		optorcheck.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		optorcheck.beginTree(emptyroot);

		// Visit Each Token in Tree
		helper(optorcheck,emptyroot);
		
		// Complete tree and display intended logs to user.
		optorcheck.finishTree(emptyroot);
		
		// Verify Results for Halstead Length Check
        assertEquals(0, optorcheck.GetResult());
        System.out.println("Operator Empty Check Done!");

	}
	
	@Test
	void operatorsManyTest() throws IOException, CheckstyleException {
		this.setup();
		
		// Configure Checks
		optorcheck.configure(new DefaultConfiguration("Local"));
		
		// Contextualize Checks
		optorcheck.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		optorcheck.beginTree(opmanyroot);

		// Visit Each Token in Tree
		helper(optorcheck,opmanyroot);
		
		// Complete tree and display intended logs to user.
		optorcheck.finishTree(opmanyroot);
		
		// Verify Results for Halstead Length Check
        assertEquals(22, optorcheck.GetResult());
        System.out.println("Operator Empty Check Done!");

	}
	
	@Test
	void operatorsSingleTest() throws IOException, CheckstyleException {
		this.setup();
		
		// Configure Checks
		optorcheck.configure(new DefaultConfiguration("Local"));
		
		// Contextualize Checks
		optorcheck.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		optorcheck.beginTree(opsingleroot);

		// Visit Each Token in Tree
		helper(optorcheck,opsingleroot);
		
		// Complete tree and display intended logs to user.
		optorcheck.finishTree(opsingleroot);
		
		// Verify Results for Halstead Length Check
        assertEquals(0, optorcheck.GetResult());
        System.out.println("Operator Empty Check Done!");

	}
	
	@Test
	void operatorsFaultTest() throws IOException, CheckstyleException {
		this.setup();
		
		// Configure Checks
		optorcheck.configure(new DefaultConfiguration("Local"));
		
		// Contextualize Checks
		optorcheck.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		optorcheck.beginTree(opfaultroot);

		// Visit Each Token in Tree
		helper(optorcheck,opfaultroot);
		
		// Complete tree and display intended logs to user.
		optorcheck.finishTree(opfaultroot);
		
		// Verify Results for Halstead Length Check
        assertEquals(0, optorcheck.GetResult());
        System.out.println("Operator Empty Check Done!");

	}
	
	// Operand tests
	
	@Test
	void operandsEmptyTest() throws IOException, CheckstyleException {

		
		this.setup();
		
		// Configure Checks
		opandcheck.configure(new DefaultConfiguration("Local"));
		
		// Contextualize Checks
		opandcheck.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		opandcheck.beginTree(emptyroot);

		// Visit Each Token in Tree
		helper(opandcheck,emptyroot);
		
		// Complete tree and display intended logs to user.
		opandcheck.finishTree(emptyroot);
		
		// Verify Results for Halstead Length Check
        assertEquals(0, opandcheck.GetResult());
        System.out.println("Operator Empty Check Done!");

	}
	
	@Test
	void operandsManyTest() throws IOException, CheckstyleException {
		this.setup();
		
		// Configure Checks
		opandcheck.configure(new DefaultConfiguration("Local"));
		
		// Contextualize Checks
		opandcheck.contextualize(new DefaultContext());
		 
		// Initialize Local Variables in Check
		opandcheck.beginTree(opmanyroot);

		// Visit Each Token in Tree
		helper(opandcheck,opmanyroot);
		
		// Complete tree and display intended logs to user.
		opandcheck.finishTree(opmanyroot);
		
		// Verify Results for Halstead Length Check
        assertEquals(116, opandcheck.GetResult());
        System.out.println("Operator Empty Check Done!");

	}

	@Test
	void operandsSingleTest() throws IOException, CheckstyleException {

		
		this.setup();
		
		// Configure Checks
		opandcheck.configure(new DefaultConfiguration("Local"));
		
		// Contextualize Checks
		opandcheck.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		opandcheck.beginTree(opsingleroot);

		// Visit Each Token in Tree
		helper(opandcheck,opsingleroot);
		
		// Complete tree and display intended logs to user.
		opandcheck.finishTree(opsingleroot);
		
		// Verify Results for Halstead Length Check
        assertEquals(8, opandcheck.GetResult());
        System.out.println("Operator Empty Check Done!");

	}

	@Test
	void operandsFaultTest() throws IOException, CheckstyleException {

		
		this.setup();
		
		// Configure Checks
		opandcheck.configure(new DefaultConfiguration("Local"));
		
		// Contextualize Checks
		opandcheck.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		opandcheck.beginTree(opfaultroot);

		// Visit Each Token in Tree
		helper(opandcheck,opfaultroot);
		
		// Complete tree and display intended logs to user.
		opandcheck.finishTree(opfaultroot);
		
		// Verify Results for Halstead Length Check
        assertEquals(7, opandcheck.GetResult());
        System.out.println("Operator Empty Check Done!");

	}

	
	// Number of comments tests
	
	@Test
	void commentsEmptyTest() throws IOException, CheckstyleException {
		this.setup();
		
		// Configure Checks
		comcheck.configure(new DefaultConfiguration("Local"));
		
		// Contextualize Checks
		comcheck.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		comcheck.beginTree(emptyroot);

		// Visit Each Token in Tree
		helper(comcheck,emptyroot);
		
		// Complete tree and display intended logs to user.
		comcheck.finishTree(emptyroot);
		
		// Verify Results for Halstead Length Check
        assertEquals(0, comcheck.GetResult());
        System.out.println("Comments Empty Check Done!");

	}
	
	@Test
	void commentsMultiTest() throws IOException, CheckstyleException {
		this.setup();
		
		// Configure Checks
		comcheck.configure(new DefaultConfiguration("Local"));
		
		// Contextualize Checks
		comcheck.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		comcheck.beginTree(commmultiroot);

		// Visit Each Token in Tree
		helper(comcheck,commmultiroot);
		
		// Complete tree and display intended logs to user.
		comcheck.finishTree(commmultiroot);
		
		// Verify Results for Halstead Length Check
        assertEquals(4, comcheck.GetResult());
        System.out.println("Comments Empty Check Done!");

	}
	
	@Test
	void commentsBothTest() throws IOException, CheckstyleException {
		this.setup();
		
		// Configure Checks
		comcheck.configure(new DefaultConfiguration("Local"));
		
		// Contextualize Checks
		comcheck.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		comcheck.beginTree(commbothroot);

		// Visit Each Token in Tree
		helper(comcheck,commbothroot);
		
		// Complete tree and display intended logs to user.
		comcheck.finishTree(commbothroot);
		
		// Verify Results for Halstead Length Check
        assertEquals(4, comcheck.GetResult());
        System.out.println("Comments Empty Check Done!");

	}
	
	@Test
	void commentsFaultTest() throws IOException, CheckstyleException {
		this.setup();
		
		// Configure Checks
		comcheck.configure(new DefaultConfiguration("Local"));
		
		// Contextualize Checks
		comcheck.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		comcheck.beginTree(commfaultroot);

		// Visit Each Token in Tree
		helper(comcheck,commfaultroot);
		
		// Complete tree and display intended logs to user.
		comcheck.finishTree(commfaultroot);
		
		// Verify Results for Halstead Length Check
        assertEquals(4, comcheck.GetResult());
        System.out.println("Comments Empty Check Done!");

	}
	
	@Test
	void commentsSingleTest() throws IOException, CheckstyleException {
		this.setup();
		
		// Configure Checks
		comcheck.configure(new DefaultConfiguration("Local"));
		
		// Contextualize Checks
		comcheck.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		comcheck.beginTree(commsingleroot);

		// Visit Each Token in Tree
		helper(comcheck,commsingleroot);
		
		// Complete tree and display intended logs to user.
		comcheck.finishTree(commsingleroot);
		
		// Verify Results for Halstead Length Check
        assertEquals(2, comcheck.GetResult());
        System.out.println("Comments Empty Check Done!");

	}
	
	// Lines of comments tests
	
	@Test
	void commentsLinesEmptyTest() throws IOException, CheckstyleException {
		this.setup();
		
		// Configure Checks
		commlinecheck.configure(new DefaultConfiguration("Local"));
		
		// Contextualize Checks
		commlinecheck.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		commlinecheck.beginTree(emptyroot);

		// Visit Each Token in Tree
		helper(commlinecheck,emptyroot);
		
		// Complete tree and display intended logs to user.
		commlinecheck.finishTree(emptyroot);
		
		// Verify Results for Halstead Length Check
        assertEquals(0, commlinecheck.GetResult());
        System.out.println("Operator Empty Check Done!");

	}

	@Test
	void commentsLinesMultiTest() throws IOException, CheckstyleException {
		this.setup();
		
		// Configure Checks
		commlinecheck.configure(new DefaultConfiguration("Local"));
		
		// Contextualize Checks
		commlinecheck.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		commlinecheck.beginTree(commmultiroot);

		// Visit Each Token in Tree
		helper(commlinecheck,commmultiroot);
		
		// Complete tree and display intended logs to user.
		commlinecheck.finishTree(commmultiroot);
		
		// Verify Results for Halstead Length Check
        assertEquals(2, commlinecheck.GetResult());
        System.out.println("Operator Empty Check Done!");

	}

	@Test
	void commentsLinesBothTest() throws IOException, CheckstyleException {
		this.setup();
		
		// Configure Checks
		commlinecheck.configure(new DefaultConfiguration("Local"));
		
		// Contextualize Checks
		commlinecheck.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		commlinecheck.beginTree(commbothroot);

		// Visit Each Token in Tree
		helper(commlinecheck,commbothroot);
		
		// Complete tree and display intended logs to user.
		commlinecheck.finishTree(commbothroot);
		
		// Verify Results for Halstead Length Check
        assertEquals(2, commlinecheck.GetResult());
        System.out.println("Operator Empty Check Done!");

	}

	@Test
	void commentsLinesFaultTest() throws IOException, CheckstyleException {
		this.setup();
		
		// Configure Checks
		commlinecheck.configure(new DefaultConfiguration("Local"));
		
		// Contextualize Checks
		commlinecheck.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		commlinecheck.beginTree(commfaultroot);

		// Visit Each Token in Tree
		helper(commlinecheck,commfaultroot);
		
		// Complete tree and display intended logs to user.
		commlinecheck.finishTree(commfaultroot);
		
		// Verify Results for Halstead Length Check
        assertEquals(2, commlinecheck.GetResult());
        System.out.println("Operator Empty Check Done!");

	}

	@Test
	void commentsLinesSingleTest() throws IOException, CheckstyleException {
		this.setup();
		
		// Configure Checks
		commlinecheck.configure(new DefaultConfiguration("Local"));
		
		// Contextualize Checks
		commlinecheck.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		commlinecheck.beginTree(commsingleroot);

		// Visit Each Token in Tree
		helper(commlinecheck,commsingleroot);
		
		// Complete tree and display intended logs to user.
		commlinecheck.finishTree(commsingleroot);
		
		// Verify Results for Halstead Length Check
        assertEquals(2, commlinecheck.GetResult());
        System.out.println("Operator Empty Check Done!");

	}

	// Number of loops tests
	
	@Test
	void loopsEmptyTest() throws IOException, CheckstyleException {
		this.setup();
		
		// Configure Checks
		loopcheck.configure(new DefaultConfiguration("Local"));
		
		// Contextualize Checks
		loopcheck.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		loopcheck.beginTree(emptyroot);

		// Visit Each Token in Tree
		helper(loopcheck,emptyroot);
		
		// Complete tree and display intended logs to user.
		loopcheck.finishTree(emptyroot);
		
		// Verify Results for Halstead Length Check
        assertEquals(0, loopcheck.GetResult());
        System.out.println("Operator Empty Check Done!");

	}

	@Test
	void loopsForTest() throws IOException, CheckstyleException {
		this.setup();
		
		// Configure Checks
		loopcheck.configure(new DefaultConfiguration("Local"));
		
		// Contextualize Checks
		loopcheck.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		loopcheck.beginTree(loopforroot);

		// Visit Each Token in Tree
		helper(loopcheck,loopforroot);
		
		// Complete tree and display intended logs to user.
		loopcheck.finishTree(loopforroot);
		
		// Verify Results for Halstead Length Check
        assertEquals(1, loopcheck.GetResult());
        System.out.println("Operator Empty Check Done!");

	}

	@Test
	void loopsDoTest() throws IOException, CheckstyleException {
		this.setup();
		
		// Configure Checks
		loopcheck.configure(new DefaultConfiguration("Local"));
		
		// Contextualize Checks
		loopcheck.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		loopcheck.beginTree(loopdoroot);

		// Visit Each Token in Tree
		helper(loopcheck,loopdoroot);
		
		// Complete tree and display intended logs to user.
		loopcheck.finishTree(loopdoroot);
		
		// Verify Results for Halstead Length Check
        assertEquals(1, loopcheck.GetResult());
        System.out.println("Operator Empty Check Done!");

	}

	@Test
	void loopsWhileTest() throws IOException, CheckstyleException {
		this.setup();
		
		// Configure Checks
		loopcheck.configure(new DefaultConfiguration("Local"));
		
		// Contextualize Checks
		loopcheck.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		loopcheck.beginTree(loopwhileroot);

		// Visit Each Token in Tree
		helper(loopcheck,loopwhileroot);
		
		// Complete tree and display intended logs to user.
		loopcheck.finishTree(loopwhileroot);
		
		// Verify Results for Halstead Length Check
        assertEquals(1, loopcheck.GetResult());
        System.out.println("Operator Empty Check Done!");

	}

	@Test
	void loopsNestedTest() throws IOException, CheckstyleException {
		this.setup();
		
		// Configure Checks
		loopcheck.configure(new DefaultConfiguration("Local"));
		
		// Contextualize Checks
		loopcheck.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		loopcheck.beginTree(loopnestedroot);

		// Visit Each Token in Tree
		helper(loopcheck,loopnestedroot);
		
		// Complete tree and display intended logs to user.
		loopcheck.finishTree(loopnestedroot);
		
		// Verify Results for Halstead Length Check
        assertEquals(9, loopcheck.GetResult());
        System.out.println("Operator Empty Check Done!");

	}

	@Test
	void loopsFaultedTest() throws IOException, CheckstyleException {
		this.setup();
		
		// Configure Checks
		loopcheck.configure(new DefaultConfiguration("Local"));
		
		// Contextualize Checks
		loopcheck.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		loopcheck.beginTree(loopfaultroot);

		// Visit Each Token in Tree
		helper(loopcheck,loopfaultroot);
		
		// Complete tree and display intended logs to user.
		loopcheck.finishTree(loopfaultroot);
		
		// Verify Results for Halstead Length Check
        assertEquals(0, loopcheck.GetResult());
        System.out.println("Operator Empty Check Done!");

	}

	// Number of expressions tests
	
	@Test
	void expressionEmptyTest() throws IOException, CheckstyleException {
		this.setup();
		
		// Configure Checks
		exprcheck.configure(new DefaultConfiguration("Local"));
		
		// Contextualize Checks
		exprcheck.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		exprcheck.beginTree(emptyroot);

		// Visit Each Token in Tree
		helper(exprcheck,emptyroot);
		
		// Complete tree and display intended logs to user.
		exprcheck.finishTree(emptyroot);
		
		// Verify Results for Halstead Length Check
        assertEquals(0, exprcheck.GetResult());
        System.out.println("Expression Empty Check Done!");

	}

	@Test
	void expressionTypicalTest() throws IOException, CheckstyleException {
		this.setup();
		
		// Configure Checks
		exprcheck.configure(new DefaultConfiguration("Local"));
		
		// Contextualize Checks
		exprcheck.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		exprcheck.beginTree(exprroot);

		// Visit Each Token in Tree
		helper(exprcheck,exprroot);
		
		// Complete tree and display intended logs to user.
		exprcheck.finishTree(exprroot);
		
		// Verify Results for Halstead Length Check
        assertEquals(99, exprcheck.GetResult());
        System.out.println("Expression Empty Check Done!");

	}

	@Test
	void expressionFaultTest() throws IOException, CheckstyleException {
		this.setup();
		
		// Configure Checks
		exprcheck.configure(new DefaultConfiguration("Local"));
		
		// Contextualize Checks
		exprcheck.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		exprcheck.beginTree(exprfaultroot);

		// Visit Each Token in Tree
		helper(exprcheck,exprfaultroot);
		
		// Complete tree and display intended logs to user.
		exprcheck.finishTree(exprfaultroot);
		
		// Verify Results for Halstead Length Check
        assertEquals(86, exprcheck.GetResult());
        System.out.println("Expression Empty Check Done!");

	}

	
	// Halstead length tests
	
	@Test
	void halsteadLengthEmptyTest() throws IOException, CheckstyleException {
		this.setup();
		
		// Configure Checks
		hallencheck.configure(new DefaultConfiguration("Local"));
		
		// Contextualize Checks
		hallencheck.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		hallencheck.beginTree(emptyroot);

		// Visit Each Token in Tree
		helper(hallencheck,emptyroot);
		
		// Complete tree and display intended logs to user.
		hallencheck.finishTree(emptyroot);
		
		// Verify Results for Halstead Length Check
        assertEquals(0, hallencheck.GetResult());
        System.out.println("Expression Empty Check Done!");

	}

	@Test
	void halsteadLengthOpFaultTest() throws IOException, CheckstyleException {
		this.setup();
		
		// Configure Checks
		hallencheck.configure(new DefaultConfiguration("Local"));
		
		// Contextualize Checks
		hallencheck.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		hallencheck.beginTree(opfaultroot);

		// Visit Each Token in Tree
		helper(hallencheck,opfaultroot);
		
		// Complete tree and display intended logs to user.
		hallencheck.finishTree(opfaultroot);
		
		// Verify Results for Halstead Length Check
        assertEquals(0, hallencheck.GetResult());
        System.out.println("Expression Empty Check Done!");

	}

	@Test
	void halsteadLengthOpManyTest() throws IOException, CheckstyleException {
		this.setup();
		
		// Configure Checks
		hallencheck.configure(new DefaultConfiguration("Local"));
		
		// Contextualize Checks
		hallencheck.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		hallencheck.beginTree(opmanyroot);

		// Visit Each Token in Tree
		helper(hallencheck,opmanyroot);
		
		// Complete tree and display intended logs to user.
		hallencheck.finishTree(opmanyroot);
		
		// Verify Results for Halstead Length Check
        assertEquals(5568, hallencheck.GetResult());
        System.out.println("Expression Empty Check Done!");

	}
	
	@Test
	void halsteadLengthOpSingleTest() throws IOException, CheckstyleException {
		this.setup();
		
		// Configure Checks
		hallencheck.configure(new DefaultConfiguration("Local"));
		
		// Contextualize Checks
		hallencheck.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		hallencheck.beginTree(opsingleroot);

		// Visit Each Token in Tree
		helper(hallencheck,opsingleroot);
		
		// Complete tree and display intended logs to user.
		hallencheck.finishTree(opsingleroot);
		
		// Verify Results for Halstead Length Check
        assertEquals(8, hallencheck.GetResult());
        System.out.println("Expression Empty Check Done!");

	}

	@Test
	void halsteadLengthFaultTest() throws IOException, CheckstyleException {
		this.setup();
		
		// Configure Checks
		hallencheck.configure(new DefaultConfiguration("Local"));
		
		// Contextualize Checks
		hallencheck.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		hallencheck.beginTree(halfaultroot);

		// Visit Each Token in Tree
		helper(hallencheck,halfaultroot);
		
		// Complete tree and display intended logs to user.
		hallencheck.finishTree(halfaultroot);
		
		// Verify Results for Halstead Length Check
        assertEquals(3420, hallencheck.GetResult());
        System.out.println("Expression Empty Check Done!");

	}

	
	// Halstead Volume tests
	
	@Test
	void halsteadVolumeEmptyTest() throws IOException, CheckstyleException {
		this.setup();
		
		// Configure Checks
		halvolcheck.configure(new DefaultConfiguration("Local"));
		
		// Contextualize Checks
		halvolcheck.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		halvolcheck.beginTree(emptyroot);

		// Visit Each Token in Tree
		helper(halvolcheck,emptyroot);
		
		// Complete tree and display intended logs to user.
		halvolcheck.finishTree(emptyroot);
		
		// Verify Results for Halstead Length Check
        assertEquals(0, halvolcheck.GetResult());
        System.out.println("Expression Empty Check Done!");

	}

	@Test
	void halsteadVolumeOpFaultTest() throws IOException, CheckstyleException {
		this.setup();
		
		// Configure Checks
		halvolcheck.configure(new DefaultConfiguration("Local"));
		
		// Contextualize Checks
		halvolcheck.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		halvolcheck.beginTree(opfaultroot);

		// Visit Each Token in Tree
		helper(halvolcheck,opfaultroot);
		
		// Complete tree and display intended logs to user.
		halvolcheck.finishTree(opfaultroot);
		
		// Verify Results for Halstead Length Check
        assertEquals(27, halvolcheck.GetResult());
        System.out.println("Expression Empty Check Done!");

	}

	@Test
	void halsteadVolumeOpManyTest() throws IOException, CheckstyleException {
		this.setup();
		
		// Configure Checks
		halvolcheck.configure(new DefaultConfiguration("Local"));
		
		// Contextualize Checks
		halvolcheck.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		halvolcheck.beginTree(opmanyroot);

		// Visit Each Token in Tree
		helper(halvolcheck,opmanyroot);
		
		// Complete tree and display intended logs to user.
		halvolcheck.finishTree(opmanyroot);
		
		// Verify Results for Halstead Length Check
        assertEquals(1302, halvolcheck.GetResult());
        System.out.println("Expression Empty Check Done!");

	}
	
	@Test
	void halsteadVolumeOpSingleTest() throws IOException, CheckstyleException {
		this.setup();
		
		// Configure Checks
		halvolcheck.configure(new DefaultConfiguration("Local"));
		
		// Contextualize Checks
		halvolcheck.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		halvolcheck.beginTree(opsingleroot);

		// Visit Each Token in Tree
		helper(halvolcheck,opsingleroot);
		
		// Complete tree and display intended logs to user.
		halvolcheck.finishTree(opsingleroot);
		
		// Verify Results for Halstead Length Check
        assertEquals(42, halvolcheck.GetResult());
        System.out.println("Expression Empty Check Done!");

	}

	@Test
	void halsteadVolumeFaultTest() throws IOException, CheckstyleException {
		this.setup();
		
		// Configure Checks
		halvolcheck.configure(new DefaultConfiguration("Local"));
		
		// Contextualize Checks
		halvolcheck.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		halvolcheck.beginTree(halfaultroot);

		// Visit Each Token in Tree
		helper(halvolcheck,halfaultroot);
		
		// Complete tree and display intended logs to user.
		halvolcheck.finishTree(halfaultroot);
		
		// Verify Results for Halstead Length Check
        assertEquals(980, halvolcheck.GetResult());
        System.out.println("Expression Empty Check Done!");

	}

	// Halstead Difficulty tests
	
	@Test
	void halsteadDifficultyEmptyTest() throws IOException, CheckstyleException {
		this.setup();
		
		// Configure Checks
		haldiffcheck.configure(new DefaultConfiguration("Local"));
		
		// Contextualize Checks
		haldiffcheck.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		haldiffcheck.beginTree(emptyroot);

		// Visit Each Token in Tree
		helper(haldiffcheck,emptyroot);
		
		// Complete tree and display intended logs to user.
		haldiffcheck.finishTree(emptyroot);
		
		// Verify Results for Halstead Length Check
        assertEquals(0, haldiffcheck.GetResult());
        System.out.println("Expression Empty Check Done!");

	}

	void halsteadDifficultyOpFaultTest() throws IOException, CheckstyleException {
		this.setup();
		
		// Configure Checks
		haldiffcheck.configure(new DefaultConfiguration("Local"));
		
		// Contextualize Checks
		haldiffcheck.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		haldiffcheck.beginTree(opfaultroot);

		// Visit Each Token in Tree
		helper(haldiffcheck,opfaultroot);
		
		// Complete tree and display intended logs to user.
		haldiffcheck.finishTree(opfaultroot);
		
		// Verify Results for Halstead Length Check
        assertEquals(0, haldiffcheck.GetResult());
        System.out.println("Expression Empty Check Done!");

	}

	@Test
	void halsteadDifficultyOpManyTest() throws IOException, CheckstyleException {
		this.setup();
		
		// Configure Checks
		haldiffcheck.configure(new DefaultConfiguration("Local"));
		
		// Contextualize Checks
		haldiffcheck.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		haldiffcheck.beginTree(opmanyroot);

		// Visit Each Token in Tree
		helper(haldiffcheck,opmanyroot);
		
		// Complete tree and display intended logs to user.
		haldiffcheck.finishTree(opmanyroot);
		
		// Verify Results for Halstead Length Check
        assertEquals(4, haldiffcheck.GetResult());
        System.out.println("Expression Empty Check Done!");

	}
	
	@Test
	void halsteadDifficultyOpSingleTest() throws IOException, CheckstyleException {
		this.setup();
		
		// Configure Checks
		haldiffcheck.configure(new DefaultConfiguration("Local"));
		
		// Contextualize Checks
		haldiffcheck.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		haldiffcheck.beginTree(opsingleroot);

		// Visit Each Token in Tree
		helper(haldiffcheck,opsingleroot);
		
		// Complete tree and display intended logs to user.
		haldiffcheck.finishTree(opsingleroot);
		
		// Verify Results for Halstead Length Check
        assertEquals(0, haldiffcheck.GetResult());
        System.out.println("Expression Empty Check Done!");

	}

	@Test
	void halsteadDifficultyFaultTest() throws IOException, CheckstyleException {
		this.setup();
		
		// Configure Checks
		haldiffcheck.configure(new DefaultConfiguration("Local"));
		
		// Contextualize Checks
		haldiffcheck.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		haldiffcheck.beginTree(halfaultroot);

		// Visit Each Token in Tree
		helper(haldiffcheck,halfaultroot);
		
		// Complete tree and display intended logs to user.
		haldiffcheck.finishTree(halfaultroot);
		
		// Verify Results for Halstead Length Check
        assertEquals(2, haldiffcheck.GetResult());
        System.out.println("Expression Empty Check Done!");

	}

	// Halstead Effort tests
	
	@Test
	void halsteadEffortEmptyTest() throws IOException, CheckstyleException {
		this.setup();
		
		// Configure Checks
		haleffcheck.configure(new DefaultConfiguration("Local"));
		
		// Contextualize Checks
		haleffcheck.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		haleffcheck.beginTree(emptyroot);

		// Visit Each Token in Tree
		helper(haleffcheck,emptyroot);
		
		// Complete tree and display intended logs to user.
		haleffcheck.finishTree(emptyroot);
		
		// Verify Results for Halstead Length Check
        assertEquals(0, haleffcheck.GetResult());
        System.out.println("Expression Empty Check Done!");

	}

	void halsteadEffortOpFaultTest() throws IOException, CheckstyleException {
		this.setup();
		
		// Configure Checks
		haleffcheck.configure(new DefaultConfiguration("Local"));
		
		// Contextualize Checks
		haleffcheck.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		haleffcheck.beginTree(opfaultroot);

		// Visit Each Token in Tree
		helper(haleffcheck,opfaultroot);
		
		// Complete tree and display intended logs to user.
		haleffcheck.finishTree(opfaultroot);
		
		// Verify Results for Halstead Length Check
        assertEquals(0, haleffcheck.GetResult());
        System.out.println("Expression Empty Check Done!");

	}

	
	@Test
	void halsteadEffortOpManyTest() throws IOException, CheckstyleException {
		this.setup();
		
		// Configure Checks
		haleffcheck.configure(new DefaultConfiguration("Local"));
		
		// Contextualize Checks
		haleffcheck.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		haleffcheck.beginTree(opmanyroot);

		// Visit Each Token in Tree
		helper(haleffcheck,opmanyroot);
		
		// Complete tree and display intended logs to user.
		haleffcheck.finishTree(opmanyroot);
		
		// Verify Results for Halstead Length Check
        assertEquals(5926, haleffcheck.GetResult());
        System.out.println("Expression Empty Check Done!");

	}
	
	@Test
	void halsteadEffortOpSingleTest() throws IOException, CheckstyleException {
		this.setup();
		
		// Configure Checks
		haleffcheck.configure(new DefaultConfiguration("Local"));
		
		// Contextualize Checks
		haleffcheck.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		haleffcheck.beginTree(opsingleroot);

		// Visit Each Token in Tree
		helper(haleffcheck,opsingleroot);
		
		// Complete tree and display intended logs to user.
		haleffcheck.finishTree(opsingleroot);
		
		// Verify Results for Halstead Length Check
        assertEquals(13, haleffcheck.GetResult());
        System.out.println("Expression Empty Check Done!");

	}

	@Test
	void halsteadEffortFaultTest() throws IOException, CheckstyleException {
		this.setup();
		
		// Configure Checks
		haleffcheck.configure(new DefaultConfiguration("Local"));
		
		// Contextualize Checks
		haleffcheck.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		haleffcheck.beginTree(halfaultroot);

		// Visit Each Token in Tree
		helper(haleffcheck,halfaultroot);
		
		// Complete tree and display intended logs to user.
		haleffcheck.finishTree(halfaultroot);
		
		// Verify Results for Halstead Length Check
        assertEquals(2482, haleffcheck.GetResult());
        System.out.println("Expression Empty Check Done!");

	}

	// Halstead Vocabulary Tests
	
	// Halstead Vocabulary tests
	
	@Test
	void halsteadVocabEmptyTests() throws IOException, CheckstyleException {
		this.setup();
		
		// Configure Checks
		halvoccheck.configure(new DefaultConfiguration("Local"));
		
		// Contextualize Checks
		halvoccheck.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		halvoccheck.beginTree(emptyroot);

		// Visit Each Token in Tree
		helper(halvoccheck,emptyroot);
		
		// Complete tree and display intended logs to user.
		halvoccheck.finishTree(emptyroot);
		
		// Verify Results for Halstead Length Check
        assertEquals(0, halvoccheck.GetResult());
        System.out.println("Expression Empty Check Done!");

	}


	void halsteadVocabularyOpFaultTest() throws IOException, CheckstyleException {
		this.setup();
		
		// Configure Checks
		halvoccheck.configure(new DefaultConfiguration("Local"));
		
		// Contextualize Checks
		halvoccheck.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		halvoccheck.beginTree(opfaultroot);

		// Visit Each Token in Tree
		helper(halvoccheck,opfaultroot);
		
		// Complete tree and display intended logs to user.
		halvoccheck.finishTree(opfaultroot);
		
		// Verify Results for Halstead Length Check
        assertEquals(0, halvoccheck.GetResult());
        System.out.println("Expression Empty Check Done!");

	}

	@Test
	void halsteadVocabularyOpManyTest() throws IOException, CheckstyleException {
		this.setup();
		
		// Configure Checks
		halvoccheck.configure(new DefaultConfiguration("Local"));
		
		// Contextualize Checks
		halvoccheck.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		halvoccheck.beginTree(opmanyroot);

		// Visit Each Token in Tree
		helper(halvoccheck,opmanyroot);
		
		// Complete tree and display intended logs to user.
		halvoccheck.finishTree(opmanyroot);
		
		// Verify Results for Halstead Length Check
        assertEquals(164, halvoccheck.GetResult());
        System.out.println("Expression Empty Check Done!");

	}
	
	@Test
	void halsteadVocabularyOpSingleTest() throws IOException, CheckstyleException {
		this.setup();
		
		// Configure Checks
		halvoccheck.configure(new DefaultConfiguration("Local"));
		
		// Contextualize Checks
		halvoccheck.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		halvoccheck.beginTree(opsingleroot);

		// Visit Each Token in Tree
		helper(halvoccheck,opsingleroot);
		
		// Complete tree and display intended logs to user.
		halvoccheck.finishTree(opsingleroot);
		
		// Verify Results for Halstead Length Check
        assertEquals(9, halvoccheck.GetResult());
        System.out.println("Expression Empty Check Done!");

	}

	@Test
	void halsteadVocabularyFaultTest() throws IOException, CheckstyleException {
		this.setup();
		
		// Configure Checks
		halvoccheck.configure(new DefaultConfiguration("Local"));
		
		// Contextualize Checks
		halvoccheck.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		halvoccheck.beginTree(halfaultroot);

		// Visit Each Token in Tree
		helper(halvoccheck,halfaultroot);
		
		// Complete tree and display intended logs to user.
		halvoccheck.finishTree(halfaultroot);
		
		// Verify Results for Halstead Length Check
        assertEquals(128, halvoccheck.GetResult());
        System.out.println("Expression Empty Check Done!");

	}

	
	public void helper(AbstractCheck b, DetailAST a) {
		while(a != null) {
			b.visitToken(a);
			helper(b,a.getFirstChild());
			a = a.getNextSibling();
		}
	}
}


