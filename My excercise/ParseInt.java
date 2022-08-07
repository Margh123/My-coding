class Doit{
	static int parseInt(String st) {
		String valid = "0|(-?[1-9][0-9]*)";
		if(!st.matches(valid)) {
			throw new IllegalArgumentException();
		}
		int j = (st.charAt(0)=='-')?1:0;
		int res = 0;
		for(int i=0;i<st.length()-j;i++) {
			res+= (st.charAt(i+j)-48)*(int)Math.pow(10, st.length()-(i+1)-j);
		}
		if(j==1) {
			res*=-1;
		}
		return res;
	}
}
