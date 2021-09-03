// Take integer part of numbers whose first digit in decimal part larger than 6
// Orginal list 3.0, 5.0, 7.37, 6.73, 1.8, 8.0, 7.33, 8.85, 4.64, 2.7
import java.util.ArrayList;
import java.util.Iterator;
public class platf1{    
    public static void main( String args[] ) {  
    	ArrayList<Integer> res = new ArrayList<>();
    	ArrayList<Double> nums = new ArrayList<>();
    	nums.add(3.0);
    	nums.add(5.0);
    	nums.add(7.32);
    	nums.add(6.73);
    	nums.add(1.8);
    	nums.add(8.0);
    	nums.add(7.33);
    	nums.add(8.85);
    	nums.add(4.64);
    	nums.add(2.7);   
    	// Done adding elements
    	Iterator<Double> it = nums.iterator();
    	while (it.hasNext()) {
    		double d = it.next(); //Unboxing
    		String process = Double.toString(d);
    		char StDigit = process.charAt(process.indexOf(".") + 1);
    		int dec = (int)StDigit - 48;
    		if(dec > 6) {
    			res.add((int)d);
    		}
    	}
    	System.out.println(res);
    }   
}  
