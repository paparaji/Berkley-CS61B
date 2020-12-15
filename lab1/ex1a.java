public class ex1a {
  public static void main(String[] args) {
    int row = 0;
    int SIZE = 5;
  

    while (row < SIZE) {
        System.out.println(' ');
        int col = 0;
        while (col <= row) {
          
          System.out.print('*');
          col += 1;
        }
        row += 1;      
        
    }
  }
}
