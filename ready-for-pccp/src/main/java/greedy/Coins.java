package greedy;

public class Coins {

    public static int countComposers(int value) {
        int[] coinTypes = {200, 100, 50, 20, 10, 5, 2, 1};
        int total = 0;
        for (int coin : coinTypes) {
            if (value/coin != 0) System.out.println(coin);
            total += value / coin;
            value %= coin;
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println("TOTAL: " + countComposers(163));
    }

}
