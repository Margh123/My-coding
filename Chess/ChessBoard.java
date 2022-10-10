package Chess;
import java.util.Scanner;

// Board representation
class ChessBoard {
	private ChessBoard(){};
	static String[][] board = new String[][] //Conventionally white will alway be at the bottom
			{{"r","n","b","q","k","b","n","r"},
			 {"p","p","p","p","p","p","p","p"},
			 {"*","*","*","*","*","*","*","*"}, // stars represent empty squares
			 {"*","*","*","*","*","*","*","*"},
			 {"*","*","*","*","*","*","*","*"},
			 {"*","*","*","*","*","*","*","*"},
			 {"P","P","P","P","P","P","P","P"},
			 {"R","N","B","Q","K","B","N","R"},
	};	// This chess board will be used in real game!
	
	static void drawChessBoard() { 
		int numLabel = 8;
		for(String[] row : board) {
			System.out.print(numLabel-- + " ");
			for(String col : row) {
				System.out.print(col + " ");
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
		
	}
	
	// Side to move indication (is also used to mark number of moves)
	static double moveNum = 1; // 0.5 is a half move meaning that white alway land on integer number whenever he starts.
	
	/** In order to query a move, type the starting coordinate of that piece 
	 * follows by destination coordinate of that piece
	 * for castling, move the king two squares to the rook: Ex: e1g1
	 * The moves will be translated into PGN
	 * */
	static void querier() {
		String move;
		String side = (isInteger(moveNum))?"White":"Black";
	//	System.out.println(moveNum);
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		do {
		System.out.print(side + " to move: ");
		move = sc.nextLine();}
		//Syntax verification
		while(!verify(move) || 
				checkSameSquare(move)||
				wrongStart(move)||
				!Pieces.isLegal(move));
			//valid the move!
		//If en passant was not made
		{
			if(Pieces.containsEe()) {
				// Lost en passant rights
				for(String st : ChessBoard.board[2]) {
					if (st.equals("e")) {
						st = "*";
					}
				}
				for(String st : ChessBoard.board[5]) {
					if (st.equals("E")) {
						st = "*";
					}
				}
			}		
		}
		drawMove(move);
		moveNum = moveNum + 0.5;
		
	}
	static void drawMove(String move) {
		String start = move.substring(0, 2);
		String end = move.substring(2);
		
		String placeStart = ChessBoard.board[8-(start.charAt(1)-48)][(int)(start.charAt(0)-97)];
		//Promotion rule (Normal vector notation but follows by a piece you want a pawn to promote to (Have to be Uppercase)).
		if(move.matches("[abcdefgh][27][abcdefgh][18][QNRB]") && Pieces.Movement.PawnMove(move)) {
			String res = (isInteger(moveNum))?move.substring(4):move.substring(4).toLowerCase();
			placeStart = res;
			
		}
		
		ChessBoard.board[8-(start.charAt(1)-48)][(int)(start.charAt(0)-97)] = "*";
		ChessBoard.board[8-(end.charAt(1)-48)][(int)(end.charAt(0)-97)] = placeStart;
	}
	static boolean verify(String move) {
		boolean b = move.matches("[abcdefgh][12345678][abcdefgh][12345678]");
		if(move.matches("[abcdefgh][27][abcdefgh][18]") && Pieces.Movement.PawnMove(move)) {
			System.out.println("You need to specify a piece you want to promote to");
			return false;
		}
		boolean c = move.matches("[abcdefgh][27][abcdefgh][18][QNRB]");
		return b || c;
	}
	private static boolean checkSameSquare(String st) { // Move to the same square ?
		return st.charAt(0) == st.charAt(2) &&  st.charAt(1) == st.charAt(3);
	}
	private static boolean wrongStart(String st) { //what if the starting square is empty or wrong piece ?
		String start = st.substring(0, 2);
		String place = board[8-(start.charAt(1)-48)][(int)(start.charAt(0)-97)]; // Run perfectly now

		if(place.equals("*")) { // if starting is an empty square
			return true;
		}
		// Check for wrong piece (unfriendly piece)
		boolean UL = Character.isUpperCase(place.charAt(0));
		if(isInteger(moveNum)&& UL) {
			return false;
		}
		if(!isInteger(moveNum)&& !UL) {
			return false;
		}
		return true;
		
	}
	private static boolean isInteger(double d) {
		return Double.toString(d).charAt(Double.toString(d).length()-1)=='0';
	}
}
