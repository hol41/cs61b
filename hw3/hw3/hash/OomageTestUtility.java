package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        int[] bucks = new int[M];
        for (int i = 0; i < bucks.length; i = i + 1) {
            bucks[i] = 0;
        }

        for (Oomage o: oomages) {
            int bNumber = (o.hashCode() & 0x7FFFFFFF) % M;
            bucks[bNumber] = bucks[bNumber] + 1;
        }


        for (int buck : bucks) {
            if (buck < oomages.size() / 50.0 || buck > oomages.size() / 2.5) {
                return false;
            }
        }
        return true;
    }
}
