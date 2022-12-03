package minimum;

import java.util.*;

public class P12935 {
    public int findMinIdx(int[] arr) {
        int minIdx = 0;
        for(int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[minIdx]) {
                minIdx = i;
            }
        }
        return minIdx;
    }


    public int[] solution(int[] arr) {
        int[] answer = {};

        if (arr.length == 1) return new int[]{-1};

        answer = new int[arr.length-1];

        int idx = findMinIdx(arr);
        int ptr = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i == idx) continue;
            answer[ptr] = arr[i];
            ptr++;
        }

        return answer;
    }
}
