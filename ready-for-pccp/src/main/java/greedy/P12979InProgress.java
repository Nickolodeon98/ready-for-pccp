package greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P12979InProgress {

    public static void onOrOff(int[] info) {
        boolean[] onAndOff = new boolean[info.length];
        System.out.println(Arrays.toString(onAndOff));

//        Arrays.fill(onAndOff, false);
    }

    public static int completeSpread(int N, int[] stations, int W) {
        boolean[] onOrOff = new boolean[N]; // false 로 초기화 된다. 모두 전파가 닿지 않는 상황으로 초기 상태를 배열로 표현한다.

        /* stations 의 정보에 따라 전파가 닿는 아파트들의 정보가 업데이트된다. */

        for (int station : stations) {
            for (int j = station - W; j <= station + W; j++) {
                if (j > N || j < 1) continue;
                onOrOff[j - 1] = true;
            }
        }

        System.out.println(Arrays.toString(onOrOff));

        List<Integer> numOfFs = new ArrayList<>(); // 연속 구간에 놓여 있는 false 의 개수들을 쉼표로 분리해서 저장한다.
        int count = 0;
        int idx = 0;
        for (int i = 0; i < onOrOff.length; i++) {
            idx = i;
            while (!onOrOff[idx]) {
                count++;
                idx++;
            }
            if (count > 0) {
                numOfFs.add(count);
                count = 0;
                i = idx;
            }
        }
        System.out.println(numOfFs);
        int countMin = 0;
        for (int countF : numOfFs) {
            if (countF % ((W * 2) + 1) == 0) {
                countMin += countF / (W * 2) + 1;
                continue;
            }
            countMin += (countF / ((W * 2) + 1)) + 1;
        }

        return countMin;
    }



    public static void main(String[] args) {
        int[] test = new int[300];
//        P12979InProgress.onOrOff(test);
        System.out.println(P12979InProgress.completeSpread(11, new int[]{4, 11}, 1));
    }
}
