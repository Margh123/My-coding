// Find all five-digit numbers (abcde (overbar)) that satisfy a^e+b^d+c^c+d^b+e^a=abcde(overbar)
public class Flatform1 {
	public static void main(String[] args) {
		String s;
		for (int i=10000;i<=99999;i++) {
			s = Integer.toString(i);
			if (s.charAt(2)=='0'||(s.charAt(1)=='0' && s.charAt(3)=='0')) {
				continue;
			}
			if(Math.pow((int)((s.charAt(0))-48),(int)((s.charAt(4))-48))+
			   Math.pow((int)((s.charAt(1))-48),(int)((s.charAt(3))-48))+
			   Math.pow((int)((s.charAt(2))-48),(int)((s.charAt(2))-48))+
			   Math.pow((int)((s.charAt(3))-48),(int)((s.charAt(1))-48))+
			   Math.pow((int)((s.charAt(4))-48),(int)((s.charAt(0))-48))==(double)i) {
				System.out.println(i);
			}
		}
	}
}
// 48625
