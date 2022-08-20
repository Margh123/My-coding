public class ParallelogramPattern {
    public static void main(String args[]) {

     int w = 10; // How wide the parallelogram is
     int h = 7; // How high the parallelogram is
     
     // Step 1: The Top
      {
          System.out.print(" ".repeat(h-1));
          System.out.println("*".repeat(w));
      }
      
      //Step 2: The Body
       {
           for(int k= 2; k<= h-1; k++){
               System.out.print(" ".repeat(h-k));
                 {
                     System.out.print("*");
                     System.out.print(" ".repeat(w-2));
                     System.out.print("*");
                 }
                System.out.println(" ".repeat(k-1));
           }
       }
       
       // Step 3: The bottom
       {
           System.out.print("*".repeat(w));
           System.out.print(" ".repeat(h-1));
       }
    }
}
