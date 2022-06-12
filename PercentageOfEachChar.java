public class PercentageOfEachChar{
	public static void main(String[] args) {		
		percentage("HelloWorld");
	}
	static void percentage(String ip) {
		if(!ip.matches("^[a-zA-Z0-9]+$")) {
			return;
		}
		final int len = ip.length();
		char[] occurences = new char[len]; // len is maximum size
		int visited = 0;
		for(int i=0;i<len;i++) {
			char c = ip.charAt(i);
			if(ip.indexOf(c)==i) {
				occurences[ip.indexOf(c)-visited] = c;
			}
			else {
				visited++;
			}
		}
		int start = 0;
		char[] resOcc = new char[len-visited];
		for(char c : occurences) {
			try {
			resOcc[start] = c;
			start++;
			}
			catch(ArrayIndexOutOfBoundsException e) {}
		}
		int p = 0;
		for(int i : eachOcc(ip,resOcc)) {
			System.out.printf("The percentage of '%c' is %f%% \n", resOcc[p], ((float)i*100f)/len);
			p++;
		}
		
	}
	static int[] eachOcc(String ip, char[] c) {
		int start = 0;
		int[] res = new int[c.length];
		for(char t : c) {
			for(int i=0;i<ip.length();i++) {
				if(ip.charAt(i)==t) {
					res[start]++;
				}
			}
			start++;
		}
		return res; 
	}
}
