package samplePlug;

public class LoopNestedBlackTest {
	public void doWhileLoopExample() {
		for (int i = 1; i <= 3; i++) {
			int j = 1;
		    while (j <= 3) {
		    	do {
	                System.out.print(i * j + " ");
	                j++;
	            } while (j <= 3);
		    }
		}
		
		for (int i = 1; i <= 3; i++) {
			for (int j = 1; j <= 3; j++) {
				
			}
		}
		
		int i = 0;
		while (i <= 3) {
	    	i++;
	    	while (i <= 3) {
	    	
	    	}
	    }
		
		do {
			do {
                // nothing
            } while (1 == 0);
        } while (1 == 0);
    }
}