public class ABC {

	public static void main(String[] args){
		int layers = 11; // How high the triangle is
		
		int maxstar = 2*layers - 1;
		
		for(int i = 1; i<=layers;i++) {
			int posL = (int)(maxstar / 2)+1 - (i-1);
			int posR = (int)(maxstar / 2)+1 + (i-1);
			
			if(i==layers) {
				for(int k = 1; k<=maxstar;k++) {
					System.out.print('*');
				}
				break;
			}
			
			for(int j = 1; j<= maxstar;j++ ) {
				if(j==posL|j==posR) {
					System.out.print('*');
				} else {
					System.out.print(' ');
				}
			}
			System.out.println();
		}
	}
	


}
