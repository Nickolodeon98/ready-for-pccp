package greedy;

import javax.lang.model.type.IntersectionType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class P42862 {
    public int uniforms(int n, int[] lost, int[] reserve) {
        int answer = n - lost.length;

        Arrays.sort(lost);
        Arrays.sort(reserve);

        List<Integer> lostOnes = Arrays.stream(lost).mapToObj(i->i).collect(Collectors.toList());
        List<Integer> extraOnes = Arrays.stream(reserve).mapToObj(i->i).collect(Collectors.toList());

        lostOnes.retainAll(extraOnes);

        System.out.println(lostOnes);

        int subtracted = lostOnes.size(); // 겹치는 원소의 개수
        answer += subtracted;

        /* reserve 배열에 있는 학생들의 번호에서 1을 빼고 더한 값들을 배열로 만든다. */
        int i = 0;
        int j = 0;
        while (i < reserve.length && j < lost.length) {
            if (lostOnes.contains(lost[j])) {
                j++;
                continue;
            }
            if (lostOnes.contains(reserve[i])) {
                i++;
                continue;
            }
            if (Math.abs(reserve[i] - lost[j]) == 1) {
                i++;
                j++;
                answer++;
                continue;
            }
            if (lost[j] >= reserve[i]) {
                i++;
                continue;
            }
            j++;
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] testLost = {1,2,4};
        int[] testReserve = {2,3,4,5};
        int testN = 5;

        P42862 p42862 = new P42862();
        System.out.println(p42862.uniforms(testN, testLost, testReserve));
    }
}
