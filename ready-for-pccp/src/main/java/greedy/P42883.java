package greedy;

import java.util.*;

public class P42883 {
    public String builtStr(String number, int k, String originalNum) {
        /* 큰 수를 만들기 위해서는 우선 현재 수 number 의 모든 자릿수에 있는 숫자 중 가장 큰 숫자를 확인한다.
         * 확인한 숫자를 선택한 후, 숫자 뒤에 몇 개가 있는지 개수를 센다.
         * 개수가 만약 필요한 최종 숫자 길이의 숫자를 만드는 데 충분하다면 (이를 알기 위해서는 1. 지금 필요한 숫자의 개수 를
         * 2. 현재 뒤에 있는 숫자의 개수 와 비교한다. 2 가 1 보다 크거나 같으면 충분한 길이의 숫자가 현 숫자 뒤에 있다는 의미이다.)
         * 현재 수 number 내에서 가장 큰 수의 위치를 기점으로 뒤의 숫자들을 모두 자른다.
         * 자른 후 7번 줄부터 11번 줄까지의 과정을 잘린 부분을 대상으로 해서 실시하여 본다. */
        int requiredLength = originalNum.length() - k;

        if (requiredLength <= 0) return "";


        char[] digits = number.toCharArray();

//        List<Integer> forIdxCheck = new ArrayList<>();

        int[] forIdxCheck = new int[digits.length];
//        Arrays.stream
        for (int i = 0; i < digits.length; i++) {
            forIdxCheck[i] = digits[i];
        }

        /* number 가 길이 4인데 k 가 2 여서 2개를 잘라내고 길이 2의 문자열을 구하면 될 때 */
//        [1, 4, 2, 3] -> [4, 1, 3, 2]


//        int[] numsIndex = new int[pureNums.size()];

        /* 일단 배열 하나를 더 만들어서 배열 내에 가장 큰 수의 인덱스부터 가장 작은 수의 인덱스까지 차례대로 넣는 방법을 사용한다.
         * 과정을 지나면 더 이상 pureNums 는 사용하지 못하게 되기 때문에 다른 리스트에 복사해 놓는다. */
//        List<Integer> pureNums = new ArrayList<>(forIdxCheck);
        List<Integer> numsIndex = new ArrayList<>();
        int maxIdx = -1;
        int max = -1;
        /* 각 원소의 순번을 매겨서 이후 일관성 있게 가장 큰 수부터 내림차순으로 자릿수 숫자들을 확인할 수 있게 한다. */
        while (numsIndex.size() < digits.length) {
            for (int i = 0; i < forIdxCheck.length; i++) {
                if (numsIndex.contains(i)) continue;
                if (forIdxCheck[i] > max) {
                    max = forIdxCheck[i];
                    maxIdx= i;
                }
            }
            numsIndex.add(maxIdx);
//            forIdxCheck.set(maxIdx, -1);
            max = -1;
            /* 그 다음 큰 수의 인덱스를 넣는다. */
        }
//        System.out.println(originalNum);
//        System.out.println(numsIndex);
        String answer ="";
        for (int digit : numsIndex) {
            int leftOver = (number.length() - 1) - digit; // 현재 최대 숫자가 있는 위치 뒤에 길이 몇의 문자열이 있는지 확인한다.
            if (leftOver >= requiredLength - 1) { // 더 많이 남았을 때만 이 문자를 선택하고 뒤의 문자열을 댕강 잘라 잘린 문자열로 전체 프로세스를 반복한다.
                answer = String.format(number.charAt(digit) + builtStr(number.substring(digit + 1), k + 1, originalNum));
                break;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        P42883 p42883 = new P42883();
        String testOriginalNum = "1231234"; // 7 2 3 5 0 8 4 6 1 9
        System.out.println(p42883.builtStr(testOriginalNum, 3, testOriginalNum));
    }
}
