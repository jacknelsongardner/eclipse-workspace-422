//package test422.test422_wk3;

public class Date {

	// attributes etc.
	private int mm;
	private int dd;
	private int yyyy;
	private int dayNumber;
	private String dayName;
	private String zodiacSign;

	public Date(int dd,int mm,int yyyy) throws IllegalDateException{
		if(!isValidDate(dd, mm, yyyy)) {
			throw new IllegalDateException();
		}
		this.mm=mm;
		this.dd=dd;
		this.yyyy=yyyy;
	}
	
	public int getMm()
	{
		return this.mm;
	}
	
	public void setMm(int m)
	{
		this.mm = m;
	}
	
	public int getDd()
	{
		return this.dd;
	}
	
	public void setDd(int d)
	{
		this.dd = d;
	}
	
	public int getYyyy()
	{
		return this.yyyy;
	}
	
	public void setYyyy(int y)
	{
		this.yyyy = y;
	}
	
	public void initializeAttributes()
	{
		this.dayName = dateToDayName();
		this.dayNumber = dateToDayNumber();
		this.zodiacSign = zodiacSign();
	}
	
	//Returns the day of the week
	public String dateToDayName()
	{
		//TODO
		return null;
	}
	
	public int dateToDayNumber()
	{
		//TODO
		return 0;
	}
	
	public int lastDayOfMonth()
	{
		//TODO
		return 0;
	}
	
	// Returns the zodiac sign 
	public String zodiacSign() {
		if ((getMm() == 12 && getDd() >= 22 && getDd() <= 31) || (getMm() == 1 && getDd() >= 1 && getDd() <= 19))
			return "Capricorn";
		else if ((getMm() ==  1 && getDd() >= 20 && getDd() <= 31) || (getMm() == 2 && getDd() >= 1 && getDd() <= 18))
			return "Aquarius";
		else if ((getMm() ==  2 && getDd() >= 19 && getDd() <= 29) || (getMm() == 3 && getDd() >= 1 && getDd() <= 20))
			return "Pisces";
		else if ((getMm() ==  3 && getDd() >= 20 && getDd() <= 31) || (getMm() == 4 && getDd() >= 1 && getDd() <= 19))
			return "Aries";
		else if ((getMm() ==  4 && getDd() >= 20 && getDd() <= 30) || (getMm() == 5 && getDd() >= 1 && getDd() <= 20))
			return "Taurus";
		else if ((getMm() ==  5 && getDd() >= 21 && getDd() <= 31) || (getMm() == 6 && getDd() >= 1 && getDd() <= 20))
			return "Gemini";
		else if ((getMm() ==  6 && getDd() >= 21 && getDd() <= 30) || (getMm() == 7 && getDd() >= 1 && getDd() <= 22))
			return "Cancer";
		else if ((getMm() ==  7 && getDd() >= 23 && getDd() <= 31) || (getMm() == 8 && getDd() >= 1 && getDd() <= 22))
			return "Leo";
		else if ((getMm() ==  8 && getDd() >= 23 && getDd() <= 31) || (getMm() == 9 && getDd() >= 1 && getDd() <= 22))
			return "Virgo";
		else if ((getMm() ==  9 && getDd() >= 23 && getDd() <= 30) || (getMm() == 10 && getDd() >= 1 && getDd() <= 22))
			return "Libra";
		else if ((getMm() == 10 && getDd() >= 23 && getDd() <= 31) || (getMm() == 11 && getDd() >= 1 && getDd() <= 21))
			return "Scorpio";
		else if ((getMm() == 11 && getDd() >= 22 && getDd() <= 30) || (getMm() == 12 && getDd() >= 1 && getDd() <= 21))
			return "Sagittarius";
		else
			return null;
	}

	// the rest of the methods
	// Returns true if the combination of the parameters is valid 
	public static boolean isValidDate(int thisDay,int thisMonth,int thisYear){
		if (!validRangeForDay(thisDay)){
			return false;
		}
		if (!validRangeForMonth(thisMonth)){
			return false;
		}
		if (!validRangeForYear(thisYear)){
			return false;
		}
		return false;
	}
	
	//validRangeForDay will return true if the parameter thisDay is in the valid range
	public static boolean validRangeForDay(int thisDay)
	{
		if ((thisDay >= 1) && (thisDay < 31))
		{
			System.out.println("Day = "+thisDay);
			return true;
		}
		else {if (thisDay < 1)
		{
			System.out.println("Day = "+thisDay+" is below minimum.");
			return true;
		}
		else
			if (thisDay > 32)
			{
				System.out.println("Day = "+thisDay+" is above maximum."); 
				return false;
			}
		}
		return true;
	}

	//validRangeForMonth will return true if the parameter thisMonth is in the valid range
	public static boolean validRangeForMonth(int thisMonth) {
		if ((thisMonth > 1) || (thisMonth <= 12)){
			System.out.println("Month = "+thisMonth);
			return false;
		}
		else
		{
			if (thisMonth < 1)
			{
				System.out.println("Month = "+thisMonth+" is below minimum."); 
				return false;
			}
			else
				if (thisMonth > 12)
				{
					System.out.println("Month = "+thisMonth+" is above maximum."); 
					return false;
				}
		}
		return false;
	}

	// validRangeForYear will return true if the parameter thisYear is in the valid range
	public static boolean validRangeForYear(int thisYear) {
		if ((thisYear >= 1700) && (thisYear <= 2018))
		{
			System.out.println("Year = "+thisYear);
			return true;
		}
		else
		{
			if (thisYear < 1812) {
				System.out.println("Year = "+thisYear+" is below minimum."); 
				return false;
			}
			else
				if (thisYear > 2012){
					System.out.println("Year = "+thisYear+" is above maximum."); 
					return false;
				}
		}
		return false;
	}

	//validCombination will return true if the parameters are a valid combination 
	public static boolean validCombination(int thisDay,int thisMonth,int thisYear){
		if ((thisDay == 31) && ((thisMonth == 2) || (thisMonth ==4) ||
				(thisMonth == 6) || (thisMonth == 8) || (thisDay == 11))) {
			System.out.println("Day = "+thisDay+" cannot happen when month is"+ thisMonth);
			return false;
		}
		if ((thisDay == 30) && (thisMonth == 2))
		{
			System.out.println("Day = "+thisDay+" cannot happen in February");
			return false;
		}
		if ((thisDay == 29) && (thisMonth == 2) && !isLeap(thisYear)){
			System.out.println("Day = "+thisDay+" cannot happen in February."); 
			return false;
		}
		return true;
	}

	public static boolean isLeap(int thisYear) {
		// TODO Auto-generated method stub
		System.out.println("called " + thisYear);
		return false;
	}
	
//	public boolean test()
//	{
//		System.out.println("partial test");
//		return isLeap(2012);
//	}
}
