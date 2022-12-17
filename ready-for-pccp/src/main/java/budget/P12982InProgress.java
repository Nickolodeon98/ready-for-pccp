package budget;

import java.util.Arrays;

public class P12982InProgress {
    /* 각 부서에 따라 필요한 금액들 중 몇 개를 더해야 배정된 예산을 넘지 않으면서 지원하는 부서의 개수가 최대가 되는지 구한다.
    * 1. 완전 탐색으로 해본 결과, 완전 탐색으로는 풀 수 없다.*/

    public int bigEfficiency(int[] d, int budget) {
        int max = 0;
        for (int i = 0; i < d.length; i++) {
            int efficiency = support(d, budget, i);
            if (efficiency > max) max = efficiency;
        }
        return max;
    }

    /* 배열 d 에서 원소 current 를 반드시 지원한다고 했을 때의 결과 */
    public int support(int[] d, int budget, int current) {
        int count = 1;
        int total = d[current];
        for (int i = 0; i < d.length; i++) {
            if (current == i) continue;
            total += d[i]; // 부서 하나를 지원한다.
            count++; // 몇 개를 지원했는지 저장한다.
            if (total > budget) { // 부서를 지원했는데 예산을 넘어가면 지원 취소한다.
                total -= d[i]; // 총 지원 금액에서 현재 지원 금액을 뺀다.
                count--;
            }
        }
        return count;
    }

    public int findEfficiency(int[] d, int budget) {
        int firstOption = findOptimum(d, budget, 0, 0, 0);
        int secondOption = findOptimum(d, budget, 1, 0, 0);
        return firstOption > secondOption ? firstOption : secondOption;
    }
    public int findOptimum(int[] d, int budget, int option, int currentIdx, int count) {
        if (budget < 0) return count-1;
        if (budget == 0) return count;
        if (currentIdx == d.length) return count;
        if (count == d.length) return count;

        if (option == 1) {
            budget -= d[currentIdx];
            count++;
        }
        currentIdx++;
        int firstOption = findOptimum(d, budget, 0, currentIdx, count);
        int secondOption = findOptimum(d, budget, 1, currentIdx, count);
        int max = firstOption > secondOption ? firstOption : secondOption;

        return max;
    }

    public static void main(String[] args) {
        P12982InProgress p12982 = new P12982InProgress();
        int[] d = {1,3,2,5,4};
        System.out.println(p12982.findEfficiency(d, 9));
    }
}
