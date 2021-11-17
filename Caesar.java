
public class Flatform1 {
	public static void main(String[] args) {
		System.out.println(Caesar.Use("IDOOV", -38));
	}
}
class Caesar{
	static String Use(String org, int shift) {
		int conv;
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<org.length();i++) {
			if(((int)org.charAt(i)<65 || (int)org.charAt(i)>90)&& org.charAt(i)!=' ') {
				System.out.println("Invalid String");
				return null;
			}
			if(org.charAt(i)==' ') {
				sb.append(' ');
				continue;
			}
			if (shift>=0) {
				conv = (int)org.charAt(i)-65+shift;
			}
			else {
				conv = (int)org.charAt(i)-65+shift;
				if (conv<0) {
					conv = 26 - (-(conv)%26);
				}
			}
			sb.append((char)((conv % 26) +65));
		}
		return sb.toString();
	}
}
