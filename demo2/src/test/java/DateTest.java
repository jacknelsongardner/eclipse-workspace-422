import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import org.mockito.MockedStatic;
import static org.mockito.Mockito.*;

public class DateTest {

	@Test
	public void testValidCombination() throws IllegalDateException {

		assertFalse(Date.isLeap(2012));
		assertFalse(Date.validCombination(29, 2, 2012));
		
		//How to mock a static function: 
		try (MockedStatic<Date> mockDate = mockStatic(Date.class, CALLS_REAL_METHODS))
		{
			mockDate.when(() -> Date.isLeap(2012)).thenReturn(true);

			//assertTrue(Date.isLeap(2012));
			assertTrue(Date.validCombination(29, 2, 2012));
			
			mockDate.when(() -> Date.isLeap(2017)).thenReturn(false);
			assertFalse(Date.isLeap(2017));		
			assertFalse(Date.validCombination(29, 2, 2017));
			assertTrue(Date.validCombination(31, 1, 2017));
			assertFalse(Date.validCombination(31, 6, 2017));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
	}

	
	@Test 
	public void testDateValid() {

		Date d;
		try {
			d = new Date(1,2,2000);
			assertEquals(1, d.getDd());
			assertEquals(2, d.getMm());
			assertEquals(2000, d.getYyyy());

		} catch (IllegalDateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testDateNotValid() {
		IllegalDateException e = assertThrows(IllegalDateException.class, () -> {
			new Date(-1,2,2000);
		});
		
		System.out.println(e.getMessage());
	}

	@Test
	public void testInitializeAttributes() {
		
		//Use a "spy" object, that returns mock value as defined, but calls actual function otherwise.
		Date spyDate;
		try {
			spyDate = spy(new Date(1,2,2000));

			doReturn(32).when(spyDate).dateToDayNumber();
			doReturn("Tuesday").when(spyDate).dateToDayName();
			doReturn("Aquarius").when(spyDate).zodiacSign();

			spyDate.initializeAttributes();

			verify(spyDate).dateToDayNumber();
			verify(spyDate).dateToDayName();
			verify(spyDate).zodiacSign();

			assertEquals(32, spyDate.dateToDayNumber());
			assertEquals("Tuesday", spyDate.dateToDayName());
			assertEquals("Aquarius", spyDate.zodiacSign());

			verify(spyDate, never()).setDd(1);

		} catch (IllegalDateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testZodiacSign() {

		Date spyDate;

		try {
			// "Capricorn": December 22 - January 19
			// normal and border cases:
			spyDate = spy(new Date(22,12,2017));
			doReturn(12).when(spyDate).getMm();
			doReturn(22).when(spyDate).getDd();
			assertEquals("Capricorn", spyDate.zodiacSign());

			spyDate = spy(new Date(1,1,2017));
			doReturn(1).when(spyDate).getMm();
			doReturn(1).when(spyDate).getDd();
			assertEquals("Capricorn", spyDate.zodiacSign());

			spyDate = spy(new Date(19,1,2017));
			doReturn(1).when(spyDate).getMm();
			doReturn(19).when(spyDate).getDd();
			assertEquals("Capricorn", spyDate.zodiacSign());

			// more border cases based on the implementation:
			spyDate = spy(new Date(31,12,2017));
			doReturn(12).when(spyDate).getMm();
			doReturn(31).when(spyDate).getDd();
			assertEquals("Capricorn", spyDate.zodiacSign());

			// etc. for the rest of the signs 
			// ...

		} catch (IllegalDateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// the rest of the tests...

}
