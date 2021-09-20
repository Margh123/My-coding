//In the board we have the given numbers 1/80, 2/80, 3/80,...., 80/80. We are allowed to remove any 2 random numbers lets say a,b 
//and add a new number a+b-2ab to the board. Repeat the process until the board has only 1 number X left. What is the number X ?

import java.util.ArrayList;
import java.util.Random;

public class Flatform1 {  
    public static void main(String[] args) {  
    	new DoMath().Act();
    }
}
class DoMath{
	// First we need to create an array of given numbers
	DoMath(){
		for (int i=1; i<=80; i++) {
			Nums.add(i/80.0);
		}
	}
	ArrayList<Double> Nums = new ArrayList<>();
	void Act() {
		Random r = new Random();
		int m;
		int n; 
		for (int i=1; i<=79;i++) {
			m = r.nextInt(81-i);
			n = r.nextInt(81-i);
			while (m == n) {
				m = r.nextInt(81-i);
				n = r.nextInt(81-i);	
			}
			Nums.add(Nums.get(m)+Nums.get(n)-2*Nums.get(m)*Nums.get(n));
			Nums.remove(m);
			Nums.remove(n);
		}
		System.out.println(Nums);
	}
	
}
