//How many integer numbers which have 4 digits that are different pair by pair and less than 2020 ?
public class platf1 {  
    public static void main(String[] args) {  
    	int a = 0;
    	for(int i=1023; i<=2020; i++) {
    		if(Check.ContainDup(i)) {continue;}
    		System.out.println(i);
    		a++;
    	}
    	System.out.println("There are " + a + " " + "numbers");
    }
}
class Check{
	static boolean ContainDup(int a) {
		String st = Integer.toString(a);
		String copyst = st;
		for (int i=0; i<st.length(); i++) {
			char c = st.charAt(i);
			for (int j=0; j<st.length(); j++) {
				char copy = copyst.charAt(j);
				if (i!=j) {
					if(c == copy) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
