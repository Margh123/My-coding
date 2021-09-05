import java.util.Scanner;

public class plat2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("I will guess your number");
		System.out.println("Choose a number from 0 to 2000");
		System.out.println("If you have pick a number, input C to continue");
		String IP = sc.next();
		while (!IP.equals("C")) {
			System.out.println("Invalid input, please try again (input C to continue)");
			IP = sc.next();
		}
		StringBuilder result = new StringBuilder("");
		for (int i = 0; i < 11; i++) {
			if (i == 0) {
				System.out.println("Is your number divisible by 2 ?");
			}
			if (i == 1) {
				System.out.println("Take your number divide by 2 then take the integer part");
				System.out.println("Is the result divisible by 2 ?");
			}
			if (i > 1) {
				System.out.println("Take your result divide by 2 then take the integer part");
				System.out.println("Is the result divisible by 2 ?");
			}
			System.out.println("input Y for Yes, N for No");
			IP = sc.next();
			while (!(IP.equals("N") || IP.equals("Y"))) {
				System.out.println("Invalid input, please try again (input Y for Yes, N for No)");
				IP = sc.next();
			}
			if (IP.equals("N")) {
				result.insert(0, 1);
			} else {
				result.insert(0, 0);
			}
		}
		String s = result.toString();
		System.out.println("Your number is:" + " " + Integer.parseInt(s, 2));
	}
}
