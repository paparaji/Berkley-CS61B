public class ex4 {
  public static void windowPosSum(int[] a, int n) {
    int[] res = new int[a.length];
    for (int i=0; i<a.length; i += 1) {
      if (a[i] < 0) {
        res[i] = a[i];
        continue;
      }
      else {
        int sum = 0;
        for (int j=i; j<=i+n; j+=1) {
          if (j >= a.length) {
            break;
          }
          sum = sum + a[j];
        }
        res[i] = sum;
      }
    }
    System.out.println(java.util.Arrays.toString(res));
 //   return res;
  }

  public static void main(String[] args) {
    int[] a = {1,2,-3,4,5,4};
    int n = 3;
    windowPosSum(a,n);

    System.out.println(java.util.Arrays.toString(a));
  }
}
