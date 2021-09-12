import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
public class FootballOOP {

	public static void main(String[] args) { // Surrounds try catch
		Team t1 = Team.Create("A");
		Team t2 = Team.Create("B");
		Team t3 = Team.Create("C");
		Team t4 = Team.Create("D");
		Team t5 = Team.Create("E");
		t1.lose(t2);
		t1.lose(t3);
		t1.win(t4);
		t1.draw(t5);
		t2.win(t3);
		t2.win(t4);
		t2.lose(t5);
		t3.win(t4);
		t3.draw(t5);
		t4.lose(t5);
		LeaderBoard.add(t1);
		LeaderBoard.add(t2);
		LeaderBoard.add(t3);
		LeaderBoard.add(t4);
		LeaderBoard.add(t5);	
		System.out.println(LeaderBoard.rank());
		
	}

}
class Team{
	private Team(String s) {
		name = s;
		NameCreated.add(s);
		identifier = Utils.primeNumbers[counter]; // My implementation: Using prime numbers bc the product of two prime numbers is unique
		counter++;
	}
	private static int counter = 0; // Also the number of teams created
	private int identifier;
	private static ArrayList<String> NameCreated = new ArrayList<>();
	private static int counterPlayed;
	private static ArrayList<Integer> Visited = new ArrayList<>(); //Handing the use of function
	private String name;
	private static ArrayList<String> WLstatus = new ArrayList<>();
	private int numberOfWin; // WIN = numberOfWin * 3 // Use the base rather than the points
	private int numberOfLose;// Lose = 0
	private int numberOfDraw;//Draw = numberOfDraw
	private int points;
	static Team Create(String s) {	//Factory Method for creating a team other than constructor bc it would have more controls.
		String regex = "[a-zA-Z]+";
		if (s.matches(regex) && !NameCreated.contains(s)) {
			NameCreated.add(s);
			return new Team(s);			
		}
		if (NameCreated.contains(s)) {
			System.out.println("The name" + " " + s + " " + "has already existed");
			return null;
		}
		System.out.println("Invalid name");
		return null;		
	}
	static int getcounterPlayed() {return counterPlayed; }
	static int getcounter() {return counter; }
	public int getIdentifier() {
		return identifier;
	}
	public static ArrayList<String> getWLstatus(){
		return WLstatus;
	}
	//For the user
	public int getPoints() {
		points = 3*numberOfWin + numberOfDraw;
		return points;
	}
	public String toString(){ 
		return this.name;	
	}
	void win(Team t) {
		if (this == t) {	// We can also check if sqrt of this.identifier * t.identifier is an integer
			System.out.println("Cannot win lose or draw yourself!");
			return;
		}
		if(Visited.contains(this.identifier * t.identifier)) {
			System.out.printf("The result of the pair %s and %s has been confirmed already \n", this.name,t.name);
			return;
		}
		this.numberOfWin ++;
		t.numberOfLose ++;
		Visited.add(this.identifier * t.identifier);
		if (this.identifier > t.identifier) { // >35 means that the larger identifier win (when factorize the number 35). In this case is 7 
			WLstatus.add(String.format(">%d", this.identifier * t.identifier));
		}
		if (this.identifier < t.identifier) { // Similarly the smaller identifier win which is 5
			WLstatus.add(String.format("<%d", this.identifier * t.identifier));
		}
		counterPlayed++;
	}
	void lose(Team t) {
		if (this == t) {
			System.out.println("Cannot win lose or draw yourself!");
			return;
		}
		if(Visited.contains(this.identifier * t.identifier)) {
			System.out.printf("The result of the pair %s and %s has been confirmed already \n", this.name,t.name);
			return;
		}
		this.numberOfLose ++;
		t.numberOfWin ++;
		Visited.add(this.identifier * t.identifier); // If the pair use the status method already we also need to prevent second call of other status methods
		if (this.identifier > t.identifier) { //Similary to the lose cases but i would save the wins only 
			WLstatus.add(String.format("<%d", this.identifier * t.identifier));
		}
		if (this.identifier < t.identifier) { 
			WLstatus.add(String.format(">%d", this.identifier * t.identifier));
		}
		counterPlayed++;
	
	}
	void draw(Team t) {
		if (this == t) {
			System.out.println("Cannot win lose or draw yourself!");
			return;
		}
		if(Visited.contains(this.identifier * t.identifier)) {
			System.out.printf("The result of the pair %s and %s has been confirmed already \n", this.name,t.name);
			return;
		}
		this.numberOfDraw ++;
		t.numberOfDraw ++;
		Visited.add(this.identifier * t.identifier);
		counterPlayed++;
	}
}
class LeaderBoard {
	private LeaderBoard() {};
	private static Team[] list = new Team[Team.getcounter()];
	private static int countIdx = -1;
	static void add(Team t) {	// Suppose the teams number is confirmed but if user creates more teams then the newly created teams must compete with all other teams.
		// Hence avoid anonymous object in the parameter and also prevent null as input. However user cannot use the program after that
		if (Team.getcounterPlayed() != Utils.Combinator(2, Team.getcounter())) {
			System.out.println("Only add to leaderboard if all pairs have competed");
			return;			
		}
		countIdx++;
		list[countIdx] = t;
	}
	static ArrayList<Team> rank() {
		ArrayList<Team> ar = new ArrayList<Team>();
		for(Team t : list) {
			ar.add(t);
		}
		if (Utils.NullInArray(ar)) {System.out.println("Not enough teams were added"); return null;}
		Collections.sort(ar, Utils.c);
		return ar;
	}
	static ArrayList<Team> realrank() { // Sort and consider who won the match
		ArrayList<Team> ar = new ArrayList<Team>();
		for(Team t : list) {
			ar.add(t);			
		}
		if (Utils.NullInArray(ar)) {System.out.println("Not enough teams were added"); return null;}
		Collections.sort(ar, Utils.c); // After sorting, the same values will stand next to each other!
		String encrypted = null;
		int winner;
		boolean flag = true; // Is that pair has a draw ?
		for (int i =0; i<list.length-1;i++) {
			if (ar.get(i).getPoints() == ar.get(i+1).getPoints()) {
				int cipher = ar.get(i).getIdentifier() * ar.get(i+1).getIdentifier();
				String inRegex = "." + Integer.toString(cipher); //We are going to find the encrypted code in the WLstatus which has the number cipher
				for (int j=0; j<Team.getWLstatus().size(); j++) { // If the cipher isn't in the list then that pair has a draw
					if(Team.getWLstatus().get(j).matches(inRegex)) {
						encrypted = Team.getWLstatus().get(j);
						flag = false;
						break;
					}
					flag = true;
				}
				if(flag) {
					continue;
				}
				winner = Utils.strInterpret(encrypted);
				if (ar.get(i+1).getIdentifier() == winner) {
					Team temp = ar.get(i);
					ar.set(i, ar.get(i+1));
					ar.set(i+1, temp);
				}
			}
		}
		return ar;
	}
}
class Utils{
	private Utils() {} // This class is meant to be a collection
	static final int[] primeNumbers = {2, 3, 5 ,7 ,11, 13, 17 ,19 ,23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67};
	static int Combinator(int k, int n) { // k is number of picked elements, n is number of elements
		if (k == 0 || k == n) {return 1;}
	    if (k == 1) {return n;}
	    return Combinator(k - 1, n - 1) + Combinator(k, n - 1);
	    }
	private static ArrayList<Integer> Fact(int n) { // The list alway has two positions! 
        int i = 2;
        ArrayList<Integer> listNumbers = new ArrayList<Integer>();
        while (n > 1) {
            if (n % i == 0) {
                n = n / i;
                listNumbers.add(i);
            } else {
                i++;
            }
        }
        if (listNumbers.isEmpty()) {
            listNumbers.add(n);
        }
        return listNumbers;
    }
	static Comparator<Team> c = (Team a, Team b)->{return b.getPoints() - a.getPoints();};
	static int strInterpret(String a) {
		String sign = a.substring(0, 1);
		int number = Integer.parseInt(a.substring(1, a.length()));
		ArrayList<Integer> get = Fact(number); //The maximum number is alway on the right side of the list
		switch (sign) { // Since this statement alway return a value the final return statement returns default 0
		case ">": return get.get(1);
		case "<": return get.get(0);
		}
		return 0; // This is actually unreachable "in term of our usage not the user"
	}
	static boolean NullInArray(ArrayList<Team> args) {
		return (args.get(args.size()-1)==null)?true:false; //We only need to check if the last element in a Team array is null
	}
}
