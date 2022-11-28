package budget;

import java.util.Arrays;

public class P12982Solved {
    public int ascendSupport(int[] d, int budget) {
        Arrays.sort(d);
        int total = 0;
        int count = 0;
        for (int department : d) {
            total += department;
            count++;
            if (total > budget) count--;
        }
        return count;
    }
}
