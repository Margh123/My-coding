package Chess;

class Pieces {
	private Pieces() {};
	
	static boolean isLegal(String move) { // Check if a move is legal
		String start = move.substring(0, 2);
		String end = move.substring(2);
		
		String placeStart = ChessBoard.board[8-(start.charAt(1)-48)][(int)(start.charAt(0)-97)];
		String placeEnd = ChessBoard.board[8-(end.charAt(1)-48)][(int)(end.charAt(0)-97)];
		
		switch(placeStart){
		// A piece cannot move to that square bc that square is occupied by a friendly piece.
		case "R": case "N": case "B": case "Q": case "K": case "P":
			if (Character.isUpperCase(placeEnd.charAt(0))) { 
				return false;
			}
			
			break;
		case "r": case "n": case "b": case "q": case "k": case "p":
			if (Character.isLowerCase(placeEnd.charAt(0))) {
				return false;
			}
			break;
		}
		
		// Piece movement validity
		switch(placeStart) {
		case "N": case "n":
			if(!Movement.KnightMove(move)) {
				return false;
			}
			break;
		case "B": case "b":
			if(!Movement.BishopMove(move)) {
				return false;
			}
			break;
		case "R": case "r":
			if(!Movement.RookMove(move)) {
				return false;
			}
			break;
		case "Q": case "q":
			if(!Movement.QueenMove(move)) {
				return false;
			}
			break;
			// The pawns (hard section)
		case "P": case "p":
			if(!Movement.PawnMove(move)) {
				return false;
			}
			break;
		}
	
	
		return true;
		
	}
	
	private static boolean IsOppositeColor(char X, char Y) {
		return (Character.isUpperCase(X)&&Character.isLowerCase(X))||
				(Character.isLowerCase(X)&&Character.isUpperCase(X));
	}
	static boolean containsEe() {
		for(String st : ChessBoard.board[2]) {
			if (st.equals("e")) {
				return true;
			}
		}
		for(String st : ChessBoard.board[5]) {
			if (st.equals("E")) {
				return true;
			}
		}
		return false;
		
	}
		static class Movement{
		private Movement() {}
		static boolean KnightMove(String move) {
			String start = move.substring(0, 2);
			String end = move.substring(2);

			float startfile = start.charAt(0)-96;
			float endfile = end.charAt(0)-96;

			float startrank = start.charAt(1)-48;
			float endrank = end.charAt(1)-48;
			//System.out.println((startrank-endrank)/(startfile-endfile));
			return Math.abs((startfile-endfile)/(startrank-endrank))==2.0 || 
				   Math.abs((startfile-endfile)/(startrank-endrank))==0.5; // If true then the knight move correctly!
			
		}
		static boolean BishopMove(String move) {
			String start = move.substring(0, 2);
			String end = move.substring(2);

			float startfile = start.charAt(0)-96;
			float endfile = end.charAt(0)-96;

			float startrank = start.charAt(1)-48;
			float endrank = end.charAt(1)-48;
			//System.out.println((startrank-endrank)/(startfile-endfile));
			return Math.abs((startfile-endfile)/(startrank-endrank))==1.0; // If true then the bishop move correctly!
		}
		
		static boolean RookMove(String move) {
			return move.charAt(0)==move.charAt(2)||move.charAt(1)==move.charAt(3); // If true then the rook move correctly!
		}
		static boolean QueenMove(String move) {
			return BishopMove(move)^RookMove(move); // If true then the queen move correctly!
		}
		static boolean PawnMove(String move) {
			String start = move.substring(0, 2);
			String end = move.substring(2);
			
			int startfile = start.charAt(0)-96;
			int endfile = end.charAt(0)-96;

			int startrank = start.charAt(1)-48;
			int endrank = end.charAt(1)-48;
			
			String placeStart = ChessBoard.board[8-(start.charAt(1)-48)][(int)(start.charAt(0)-97)];
			
			String pseudoCaptureSquare; // "E" or "e" or "*"
			// Starting from initial ranks
			//White side
			if(placeStart.equals("P")) {

				if(startrank == 2) {
					//Capture
					if(startrank-endrank==-1 && Math.abs(startfile-endfile)==1) {
						char targetPiece = ChessBoard.board[8-endrank][endfile-1].charAt(0); 
						if(targetPiece == 'e') {
							ChessBoard.board[8-(start.charAt(1)-46)][(int)(start.charAt(0)-97)] = "*";
							return true;
						}
						return Character.isLowerCase(targetPiece);
					}
					// Give an En Passant chance to black
					if((startfile==endfile && startrank-endrank==-2)&&
						(ChessBoard.board[8-endrank][(int)(start.charAt(0)-96)].equals("p")||
						 ChessBoard.board[8-endrank][(int)(start.charAt(0)-98)].equals("p")	)&&
						(ChessBoard.board[8-(start.charAt(1)-47)][(int)(start.charAt(0)-97)].equals("*")
						&& ChessBoard.board[8-(start.charAt(1)-46)][(int)(start.charAt(0)-97)].equals("*"))) {
						pseudoCaptureSquare = "E";
						ChessBoard.board[8-(start.charAt(1)-47)][(int)(start.charAt(0)-97)] = pseudoCaptureSquare;
					}
						//Push
						return ((startfile==endfile && startrank-endrank==-2)
								||(startfile==endfile && startrank-endrank==-1))&&
								(ChessBoard.board[8-(start.charAt(1)-47)][(int)(start.charAt(0)-97)].equals("*")
								&& ChessBoard.board[8-(start.charAt(1)-46)][(int)(start.charAt(0)-97)].equals("*"));

				}
				if(startrank == 3||startrank == 4||startrank == 5||startrank == 6||startrank == 7) {
					if(startrank-endrank==-1 && Math.abs(startfile-endfile)==1) {
						char targetPiece = ChessBoard.board[8-endrank][endfile-1].charAt(0); 
						return Character.isLowerCase(targetPiece);
					}
					return (startfile==endfile && startrank-endrank==-1)&&
							ChessBoard.board[8-(start.charAt(1)-47)][(int)(start.charAt(0)-97)].equals("*");
				}


			}
			//Black side
			if(placeStart.equals("p")) {
				if(startrank == 7) {
					//Capture
					if(startrank-endrank==1 && Math.abs(startfile-endfile)==1) {
						char targetPiece = ChessBoard.board[8-endrank][endfile-1].charAt(0); 
						if(targetPiece == 'E') {
							ChessBoard.board[8-(start.charAt(1)-46)][(int)(start.charAt(0)-97)] = "*";
							return true;
						}
						return Character.isUpperCase(targetPiece);
					}
					// Give an En Passant chance to White
					if((startfile==endfile && startrank-endrank==2)&&
						(ChessBoard.board[8-endrank][(int)(start.charAt(0)-96)].equals("P")||
						 ChessBoard.board[8-endrank][(int)(start.charAt(0)-98)].equals("P")	)&&
						(ChessBoard.board[8-(start.charAt(1)-49)][(int)(start.charAt(0)-97)].equals("*")
						&& ChessBoard.board[8-(start.charAt(1)-50)][(int)(start.charAt(0)-97)].equals("*"))) {
						pseudoCaptureSquare = "e";
						ChessBoard.board[8-(start.charAt(1)-47)][(int)(start.charAt(0)-97)] = pseudoCaptureSquare;
					}
						//Push
						return ((startfile==endfile && startrank-endrank==2)
								||(startfile==endfile && startrank-endrank==1))&&
								(ChessBoard.board[8-(start.charAt(1)-49)][(int)(start.charAt(0)-97)].equals("*")
								&& ChessBoard.board[8-(start.charAt(1)-50)][(int)(start.charAt(0)-97)].equals("*"));

				}
				if(startrank == 6||startrank == 5||startrank == 4||startrank == 3||startrank == 2) {
					if(startrank-endrank==1 && Math.abs(startfile-endfile)==1) {
						char targetPiece = ChessBoard.board[8-endrank][endfile-1].charAt(0); 
						return Character.isUpperCase(targetPiece);
					}
					return (startfile==endfile && startrank-endrank==1)&&
							ChessBoard.board[8-(start.charAt(1)-49)][(int)(start.charAt(0)-97)].equals("*");
				}


			}
			return false;
			
		}
		
	}
}


