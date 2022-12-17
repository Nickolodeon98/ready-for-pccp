package greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P12979Final {
    public int solution(int N, int[] stations, int W) {
        List<Integer> numOfFs = new ArrayList<>();

        int ptrToTrueOccurrence = 0;

        for (int i = 0; i < stations.length; i++) {
            if (i == stations.length - 1 && N > stations[i] + W) {
                numOfFs.add(N - (stations[i] + W));
            }
            int countFalse = (stations[i] - W - 1) - ptrToTrueOccurrence;
            if (countFalse <= 0) {
                ptrToTrueOccurrence = stations[i] + W;
                continue;
            }
            numOfFs.add(countFalse);
            ptrToTrueOccurrence = stations[i] + W;
        }

        int countMin = 0;

        for (int countF : numOfFs) {
            if (countF % ((W * 2) + 1) == 0) {
                countMin += countF / ((W * 2) + 1);
                continue;
            }
            countMin += (countF / ((W * 2) + 1)) + 1;
        }

        return countMin;
    }
}
