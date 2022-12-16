package greedy;

import java.util.*;
import java.util.stream.Collectors;

public class MultiConditionSorting {
    // [1, 9, 4, 6, 0, 8, 5, 2, 3, 7]
    // 1482527714
    // [0, 8, 3, 5, 1, 9, 4, 6, 7, 2]
    public String sort(String number, int k, String originalNum) {

        int requiredLength = originalNum.length() - k;

        if (requiredLength <= 0) return "";

        StringBuilder sb = new StringBuilder(number);

        sb.reverse();

        char[] chs = sb.toString().toCharArray();

        Map<Integer, Character> idxInfo = new HashMap<>();

        for (int i = 0; i < chs.length; i++) {
            idxInfo.put(i, chs[i]);
        }

        List<Integer> indices = idxInfo.entrySet().stream()
                .sorted((o1, o2) -> o2.getValue() - o1.getValue())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        System.out.println(indices);
        String answer = "";

        for (int i = chs.length-1; i >= 0; i--) {
            int digitPos = number.indexOf(String.valueOf(sb.charAt(chs[i])));
            int leftOver = (number.length() - 1) - digitPos;
            if (leftOver >= requiredLength) {
                answer = String.format(number.charAt(indices.get(i)) + sort(number.substring(indices.get(i) + 1), k + 1, originalNum));
                break;
            }
            number = number.replace(String.valueOf(sb.charAt(chs[i])), " ");
        }
        return answer;
    }

    public static void main(String[] args) {
        MultiConditionSorting multiConditionSorting = new MultiConditionSorting();
        multiConditionSorting.sort("4177252841", 4, "4177252841");
    }
}
