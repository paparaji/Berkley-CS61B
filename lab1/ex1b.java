public class ex1b {
  public static void drawTriangle(int N) {
    int row = 0;
    while (row < N) {
      System.out.println(' ');
      int col = 0;
      while (col <= row) {
        System.out.print('*');
        col += 1;
      }
      row += 1;
    } 
  }

  public static void main(String[] args) {
    drawTriangle(10);
  }
}
