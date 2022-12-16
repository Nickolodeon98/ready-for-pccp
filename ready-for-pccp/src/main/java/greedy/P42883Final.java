package greedy;

import java.util.*;
import java.util.stream.Collectors;

public class P42883Final {
    public String builtStr(String number, int k, int originalNumLength) {
        int requiredLength = originalNumLength - k;

        if (requiredLength <= 0) return "";

        Map<Integer, Character> idxInfo = new HashMap<>();

        for (int i = 0; i < number.length(); i++) {
            idxInfo.put(i, number.charAt(i));
        }

        List<Integer> indices = idxInfo.entrySet().stream()
                .sorted((o1, o2) -> o2.getValue() - o1.getValue())
                .filter(i -> i.getKey() <= number.length() - requiredLength)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        System.out.println(indices);

        return String.join("", String.valueOf(number.charAt(indices.get(0))), builtStr(number.substring(indices.get(0) + 1), k + 1, originalNumLength));
    }

    public static void main(String[] args) {
        P42883Final p42883 = new P42883Final();
        String testOriginalNum = "1231234"; // 7 2 3 5 0 8 4 6 1 9
        System.out.println(p42883.builtStr(testOriginalNum, 3, 7));
    }
}
