import java.util.ArrayList;

// Provide a new data type which is "Complex Number"
public class Cmplx {
	private double Re; // real
	private double Img; // Imaginary
	Cmplx(double r, double i){ // Output number will be Re + Img*i
		Re = r;
		Img = i;
	}
	Cmplx(double r){ // Create complex number from a real number
		Re = r;
	}
	static String getNumber(Cmplx z){ // This Function also calculate the number for special situations such as 0.0 + 3.0i is 3.0i 
		// Dealing with negative imaginary part
		ArrayList<String> zeros = new ArrayList<>();
		zeros.add("0.0");
		zeros.add("-0.0");
		String sign = "+";
		String i = "i";
		String R = Double.toString(z.Re);
		String I = Double.toString(z.Img);
		if (z.Img<0) {
			sign = "";
		}
		//Dealing with zero parts
		if (zeros.contains(R) && zeros.contains(I)) {
			return "0.0";
		}
		if (zeros.contains(R)) {
			R = sign = "";
			if (z.Img>0) {
				sign = "";
			}
		}
		if (zeros.contains(I)) {
			I = i = sign = "";
			
		}
		// Dealing with 1 or negative 1 in imaginary part
		if (I.equals("1.0")) {
			I = "";
		}
		if (I.equals("-1.0")) {
			sign = "-";
			I = "";
		}
		// The final result
		return R + sign + I + i;
	}
	public String toString() {
		return getNumber(this);
	}
	static double mod(Cmplx z) { // Take the modulus of the complex number
		return Math.sqrt(z.Re*z.Re + z.Img*z.Img);
	}
	static double getPart(boolean b, Cmplx z) { //returns real part if b is true otherwise return imaginary part 
		return (b)?z.Re:z.Img;
	}
	// Operations between two complex numbers
	static boolean equals(Cmplx z1, Cmplx z2) {
		return z1.Re == z2.Re && z1.Img == z2.Img;
	}
	static Cmplx add(Cmplx z1, Cmplx z2) { //Add 2 numbers
		return new Cmplx(z1.Re+z2.Re,z1.Img+z2.Img);
	}
	static Cmplx add(Cmplx z1, Cmplx z2, Cmplx z3) { //Add 3 numbers
		return new Cmplx(z1.Re+z2.Re+z3.Re,z1.Img+z2.Img+z3.Img);
	}
	static Cmplx mult(Cmplx z1, Cmplx z2) { 
		return new Cmplx(z1.Re*z2.Re-z1.Img*z2.Img,z1.Re*z2.Img+z2.Re*z1.Img);
	}
	static Cmplx mult(Cmplx z1, Cmplx z2, Cmplx z3) { 
		return mult(z1, mult(z2, z3));
	}
	static Cmplx subt(Cmplx z1, Cmplx z2) { 
		return new Cmplx(z1.Re-z2.Re,z1.Img-z2.Img);
	}
	static Cmplx div(Cmplx z1, Cmplx z2) { 
		return new Cmplx((z1.Re*z2.Re+z1.Img*z2.Img)/(z2.Re*z2.Re+z2.Img*z2.Img),
				(z1.Img*z2.Re-z2.Img*z1.Re)/(z2.Re*z2.Re+z2.Img*z2.Img));
	}
	// For single complex number
	static Cmplx conj(Cmplx z) { // Takes the conjugrate number
		return new Cmplx(z.Re,0-z.Img);
	}
	static Cmplx opp(Cmplx z) { // Takes the opposite number
		return new Cmplx(0-z.Re,0-z.Img);
	}
	static Cmplx inv(Cmplx z) { // Takes the inverse number
		return div(conj(z),new Cmplx(Math.pow(mod(z), 2)));
	}
	// Advance functions
	static Cmplx pow(Cmplx z, int n) { //raises z to the power of n (integer can be negative or zero except for some cases)
		Cmplx res = z;
		int k = Math.abs(n);
		if (equals(z, new Cmplx(0,0))) {
			if(n<=0) {
				throw new ArithmeticException("Cannot raise 0 to a non positive number");
			}
			return new Cmplx(0);
		}
		for(int i =1; i<k;i++) {
			res = mult(res,z);
		}
		if(n==0) {
			return new Cmplx(1);
		}
		if (n < 0) {
			return inv(res); 
		}
		return res;
	}
	static Cmplx trig(Cmplx z) {
		return z;
		
	}
	public static void main(String[] args) {
		TrigCmplx z = new TrigCmplx(10,7);
		System.out.println(TrigCmplx.getNumber(z));

	}

}
class TrigCmplx extends Cmplx{ // Trig form of a complex number
	
	TrigCmplx(double r) { // Building trig form from a regular complex number.
		// As well as convert regular from to trig form
		super(r);
		modulus = r;
		if (r > 0) {
			argument = 0;
		}
		if (r == 0) {
			argument = Double.NaN;
		}
		if (r < 0) {
			argument = 0-Math.PI;
		}
	}
	TrigCmplx(double r, double i){
		super(r,i);
		modulus = Cmplx.mod(new Cmplx(r,i));
		if (r > 0) {
			argument = Math.atan(i/r);
		}
		if (r < 0 && i>=0) {
			argument = Math.atan(i/r) + Math.PI;
		}
		if (r < 0 && i<0) {
			argument = Math.atan(i/r) - Math.PI;
		}
		if (r == 0 && i>0) {
			argument = Math.PI/2;
		}
		if (r == 0 && i<0) {
			argument = 0-Math.PI/2;
		}
		if (r == 0 && i==0) {
			argument = Double.NaN;
		}
	}
	TrigCmplx(double m, double a, int i){ // Building trig form from actual data
		super(m*Math.cos(a),m*Math.sin(a)); // Convert trig to regular
		if (m < 0) {
			throw new ArithmeticException("Modulus cannot be negative!");
		}
		modulus = m;
		argument = a;		
	}
	double modulus;
	double argument;
	static String getNumber(TrigCmplx z) { // The number is convenced to be r(cosA + isinA)
		return String.format("%f$%f", z.modulus,z.argument); 		
	}
	static double getPart(boolean b, TrigCmplx z) { //returns modulus if b is true otherwise return argument
		return (b)?z.modulus:z.argument;
	}
	static TrigCmplx pow(TrigCmplx z, int i) {
		if (z.modulus == 0) {
			if(i<=0) {
				throw new ArithmeticException("Cannot raise 0 to a non positive number");
			}
			return new TrigCmplx(0);	
		}
		if (i == 0) {
			return new TrigCmplx(1);	
		}
	return new TrigCmplx(Math.pow(z.modulus, i),i*z.argument, 0);	
	}
}
