/** Exercise 1.1.2
 *  @author dingqy
 */

public class HelloNumbers {
    public static void main(String[] args) {
        int x = 0;
        int y = 0;
        int sum = 0;
        while (x < 10) {
            while (y <= x){
              sum = sum + y;
              y = y + 1;
            }
            System.out.print(sum + " ");
            x = x + 1;
            y = 0;
            sum = 0;
        }
    }
}
