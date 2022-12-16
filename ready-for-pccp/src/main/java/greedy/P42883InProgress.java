package greedy;

import java.util.ArrayList;
import java.util.List;

public class P42883InProgress {
    public String bigNum(int length, List<Integer> unVisited, int idx, String soFar) {
        List<Integer> tempIdx = new ArrayList<>();
        List<Integer> tempMax = new ArrayList<>();
        int max = 0;

        if (soFar.length() == length) return soFar; // 막다른 길이 아니더라도 만약 지금까지 만든 문자열의 길이가 원하는 문자열 길이와 같으면 리턴한다.

        if (idx == unVisited.size() - 1) return soFar; // 막다른 길에 갔으면 리턴한다.

        int maxIdx = 0;

        // idx 부터 문자열의 끝까지 문자의 개수는 size - idx 개가 있고, idx 에 있는 문자는 저장되지 않으니까 1을 또 뺀다.
        // tempIdx 에 모든 가능 문자들이 가득 차면 이 반복문은 종료된다.
        while(unVisited.size() - idx - 1 > tempIdx.size()) {
            // 아래 반복문은 최대보다 뒤에 있는 문자에 대해서만 동작한다.
            for (int i = idx+1; i < unVisited.size(); i++) {
                if (unVisited.get(i) > max) { // 중복된 숫자 문자가 있으면 뒤에서부터 찾는 것으로 한다. X 그 이유는 최대한 넓은 범위를 모두 보고
                    // 가장 큰 수를 찾아야 하게 해야 하기 때문에 뒤에서부터 찾으면 최대 수를 찾기 전에 최대가 아니지만 길이를 만족하는 수를 가장 큰 수인줄 착각할 수가 있다.
                    // 자신 이전의 인덱스에 있는 문자열은 아예 쳐다도 안 보기 때문이다.
                    max = unVisited.get(i); // 가장 큰 수를 찾는다.
                    maxIdx = i; // 큰 수의 위치를 기억한다.
                }
            }

            unVisited.set(maxIdx, -1); // 가장 큰 수를 일단 -1 로 변경해서 이후부턴 max 가 되지 못하게 만든다.

            String tempStr = soFar;

            soFar = String.join("", soFar, String.valueOf(max)); // 문자열을 빌딩한다. 리스트 내 최대의 수가 첫 문자가 된다.

            System.out.println(soFar); // 0. 4가 나온다. 1. 3이 나온다. 2. 34 가 나온다. 3. 3 이 나온다. 4. 34가 나온다. 5. 33이 나온다. 6. 334 가 나온다.
            // 7. 8.32 가 나온다. 9.324가 나온다. 10. 323이 나온다. 11.3234가 나온다.


            List<Integer> temp = new ArrayList<>(unVisited);

            if (!tempIdx.isEmpty()) {
                for (int i = 0; i < tempIdx.size(); i++) {
                    temp.set(tempIdx.get(i), tempMax.get(i)); // 원복한다.
                }
            }

            // 4 4 3 34 34 막다른길 3 34 34 33 334 334 막다른길 32 324 324 323 3234 3234 3234

            String str = "";
            str = bigNum(length, temp, maxIdx, soFar); // 여기서 재귀호출된다. 만약 막다른 길이었다면 바로 리턴할 것이다.
            System.out.println(str); // 1. 4가 나온다. 2. 34가 나온다. 3(우선). 막다른 길 나옴. 4. 34가 나온다. 5. 6. 334가 나온다. 7. 막다른길
            // 8. 9. 324가 나온다. 10. 11.3234가 나온다.

            if (str.length() == length) return str; // 완성 전에 막다른 길이었다면 문자열의 길이는 되게 짧을 것이고, 완성했다면 같을 것이다.
            // 끝- 3234로 반환된다.

            /* 모두 문자열 빌딩 전 상황으로 바꾼 후 다시 사이클을 돌린다.*/
            tempMax.add(max);
            max = 0;
            tempIdx.add(maxIdx);
            soFar = tempStr;
        }

        return "막다른 길에 도달했습니다.";
    }

    public static void main(String[] args) {
        P42883InProgress biggestNum = new P42883InProgress();
        int k = 4;
        String number = "4177252841";

        List<Integer> testUnvisited = new ArrayList<>();
        for (int i = 0; i < number.length(); i++) {
            testUnvisited.add(number.charAt(i) - '0');
        }
        System.out.println(testUnvisited);
        int testIdx = -1;
        String testSoFar = "";

        System.out.println(biggestNum.bigNum(number.length()-k, testUnvisited, testIdx, testSoFar));
    }
}