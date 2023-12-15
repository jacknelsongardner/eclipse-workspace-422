package samplePlug;

public class CommentFaultBlackTest {

	public void multiTypeComment() {
		
		//
		
		/* 
		 
		 */
		
		/*  this is a multi line comment with \n */
		
		// this is a comment with \n
		
		/* this is a multiline comment on a single line */
        
		// This is a comment // This is a comment within a comment /* This is a multiline comment within a comment */
        
		String str = "// this is not a comment, this is a string";
    }
}