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
//        boolean[] onOrOff = new boolean[N]; // false 로 초기화 된다. 모두 전파가 닿지 않는 상황의 초기 상태를 배열로 표현한다.
//
//        /* stations 의 정보에 따라 전파가 닿는 아파트들의 정보가 업데이트된다. */
//
//        for (int station : stations) {
//            for (int j = station - W; j <= station + W; j++) {
//                if (j > N || j < 1) continue;
//                onOrOff[j - 1] = true;
//            }
//        }
//
//        System.out.println(Arrays.toString(onOrOff));


        List<Integer> numOfFs = new ArrayList<>(); // 연속 구간에 놓여 있는 false 의 개수들을 쉼표로 분리해서 저장한다.

        /* false 연속 구간의 false 개수를 세는 다른 방법은 true 가 등장할 때 멈춘 후 현 인덱스에서 이전 true 가 등장한 인덱스를 빼면 된다.
         * 만약 이전에 true 가 등장했던 위치가 3 이고 현재 true 가 7에서 등장했다면, 연속해서 false 가 3개 등장했다는 의미이다.
         * 필요한 포인터는 이전 true 등장 위치를 가리키는 변수와 현재 true 의 위치를 가리키는 변수 두 개이다.
         * 아니면 다음과 같은 방법을 사용할 수 있다:
         * 1. stations 의 원소들에 각각 W 를 뺀 값과 W 를 더한 값 두 값을 기억한다.
         * e.g. stations = [4, 11] W = 1 일 때,
         * 3,5 와 10,12 두 구간에 true 가 위치해 있다. stations 는 오름차순으로 정렬되어 있으므로
         * 우선 1부터 맨 처음 원소 - W 직전까지 모두 false 이고, 이후 각 원소별로 원소 + W ~ 다음 원소 - W 구간이 모두 False 이다. */

        int ptrToTrueOccurrence = 0;


        for (int i = 0; i < stations.length; i++) {
            if (i == stations.length-1  && N > stations[i] + W) { // N 이 전파가 도달하지 않는 곳일 때에만 실시한다.
                numOfFs.add(N - (stations[i] + W) ); // 여기서는 전파가 도달하지 않은 N 을 포함해서 세야 하므로 1을 추가적으로 빼주지 않는다.
            }
            int countFalse = (stations[i] - W - 1) - ptrToTrueOccurrence; // 사이에 false 몇 개가 있는지 센다. stations[i] - W 는 전파가 도달하는 곳이므로 포함하지 않아서 -1 을 추가적으로 해준다.
            if (countFalse <= 0) {
                ptrToTrueOccurrence = stations[i] + W;
                continue; // stations 배열에 저장된 기지국들의 전파 도달 범위가 겹치는 경우에는 사이에 false 가 있을 수 없으므로 넘어간다.
            }
            numOfFs.add(countFalse);
            ptrToTrueOccurrence = stations[i] + W;
        }

        System.out.println(numOfFs);

//        int count = 0;
//        int idx = 0;
//        for (int i = 0; i < onOrOff.length; i++) {
//            idx = i;
//            while (idx < N && !onOrOff[idx]) {
//                count++;
//                idx++;
//            }
//            if (count > 0) {
//                numOfFs.add(count);
//                count = 0;
//                i = idx;
//            }
//        }
//        System.out.println(numOfFs);
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



    public static void main(String[] args) {
        int[] test = new int[300];
//        P12979InProgress.onOrOff(test);
        System.out.println(P12979InProgress.completeSpread(16, new int[]{9}, 2));
    }
}
