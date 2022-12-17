package greedy;

import java.util.ArrayList;
import java.util.List;

public class P12979 {

    public void electrify(int N, int[] stations, int W) {

        /* 1. N 을 이용해 이상적인 상태 ideal 리스트를 파악한다.
         * 2. ideal 리스트에서 stations 와 W 로 알 수 있는 이미 전파가 전달되고 있는 범위를 뺀다.
         * 3. W + 2 의 배수 중 ideal 리스트에 남은 원소들의 갯수를 나누었을 때 나머지가 0이 되는 최소 숫자를 구한다.
         * 4. 필요한 증설 개수는 W + 2 의 1배수면 1이고, 2배수면 2이고, N 배수이면 N 이다. */

        /* 1 */
        List<Integer> ideal = new ArrayList<>();

        /* 2 */
        for (int station : stations) {
            for (int j = station-W; j <= station+W; j++) {
                if (j > N || j < 0) continue;
                if (ideal.contains(j)) continue;
                ideal.add(j);
                System.out.println(j);
            }
        }

//        System.out.println(ideal);

    }

    public static void main(String[] args) {
        P12979 p12979 = new P12979();
        int testN = 200000000;
        int[] testStations = new int[10000];

        for (int i = 1; i <= testStations.length; i++) {
            testStations[i-1] = i;
        }
        
        int testW = 500;
        p12979.electrify(testN, testStations, testW);
    }
}
