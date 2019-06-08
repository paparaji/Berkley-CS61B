package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        double high = oomages.size() / 2.5;
        double low = oomages.size() / 50;
        int[] temp = new int[M];
        for (Oomage t : oomages) {
            temp[(t.hashCode() & 0x7FFFFFFF) % M] += 1;
        }
        for (int i = 0; i < M; i++) {
            if (temp[i] < low || temp[i] > high) {
                return false;
            }
        }
        return true;
    }
}
