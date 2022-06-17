// Write a method that accepts an integer number that only contains the digit 9. Lets say the number contains n digit 9, the method will return a number in form 
//1m8 where m is a number that also only contains the digit 9 but the number of digit 9 is n-1. 
// Ex: input 9999: output: 19998
public class Test {
	public static void main(String[] args) {
		System.out.println(getNum(9999l));

	}
	public static long getNum(long ip) {
		double i = ip+1;
		while(true) {
			if (i==1.0) {
				break;
			}
			if(i<1) {
				throw new IllegalArgumentException();
			}
			i = i/10.0;
			
		}
		return ip*2l;
		
	}
}


