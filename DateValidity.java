
	class VersatileKit {
	private VersatileKit() {};
	//Check date (if the date is valid)
	private static final int MAXYR = 9999; 
	private static final int MINYR = 1800; 
	
	private static boolean isLeap(int y) {
		if(y%4==0) {
			if (y%100==0) {
				if (y%400==0) {
					return true;
				}
				return false;
			}
			return true;
		} 
		return false;
	}
	static boolean checkDate(int m, int d, int y) {//[1]
		// Range validity
		if (m<1||m>12) {
			return false;
		}
		if (d<1||d>31) {
			return false;
		}
		if (y<MINYR||y>MAXYR) {
			return false;
		}
		// With months perspective
		if (m==2) {
			if (isLeap(y)) {
				if (d <=29) {
					return true;
				}
			}
			else {if (d <=28) 
				return true;
			}
			return false;
		}
		if (m==4||m==6||m==9||m==11) {
			if (d<=30) {
				return true;
			}
			else {return false;}
		}
		return true;
		
	}
}
//[1]Bansal, S., 2019. C Program to check if a date is valid or not. [online] Tutorialspoint.com. Available at: 
//<https://www.tutorialspoint.com/c-program-to-check-if-a-date-is-valid-or-not> [Accessed 21 March 2022].
