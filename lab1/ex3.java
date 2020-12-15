public class ex3 {
  public static int max(int[] m) {
    int i = 0;
    int temp = 0;
    while (i < m.length) {
      if (m[i] > temp)
        temp = m[i];
      i = i + 1;
    }
    return temp;
  }
  public static void main(String[] args) {
    int[] numbers = new int[]{9,2,15,2,23,10,6};
    System.out.print(max(numbers));
  }
}
