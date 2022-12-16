package greedy;

public class P42883Another {
    public String buildStr(String number, int k) {

        StringBuilder numberWrapped = new StringBuilder(number);

        int start = 0;
        for (int i = 0; i < k; i++) {
            for (int j = start; j < numberWrapped.length()-1; j++) {
                if (numberWrapped.charAt(j) == '9') {
                    start = j;
                    continue;
                }
                int compared = numberWrapped.charAt(j);
                int toCompare = numberWrapped.charAt(j+1);

                if (compared < toCompare) {
                    numberWrapped.deleteCharAt(j);
                    break;
                }
            }
        }

        /* 만약 위 과정을 거쳤는데 도출된 문자열의 길이가 원래 문자열에서 k 개의 문자를 뺀 문자열의 길이와 다르다면, 중첩 for 문에서
         * 내부 for 문을 들어갔을 때 하나의 문자도 지우지 않고 반복이 끝난 경우가 있었다는 의미이므로, 지워지지 않고 number.length() - k 길이의
         * 문자열 뒤에 붙어 있는 문자열은 내림차순으로 정렬된 숫자로 이루어져 있다는 사실을 추론할 수 있다. 그래서 단순히 앞에서부터 잘라서
         * k 개 문자를 지운 문자열의 길이를 가진 문자열로 만들면 된다. */

        String answer = numberWrapped.toString();

        if (numberWrapped.length() > number.length() - k) {
            answer = numberWrapped.substring(0, number.length()-k);
        }

        return answer;
    }

    public static void main(String[] args) {
        String testNum = "4177252841";
        int testK = 4;
        P42883Another p42883Another = new P42883Another();
        System.out.println(p42883Another.buildStr(testNum, testK));
    }
}
