
import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int[][] zenquarilo = items;

        int n = items.length;
        int[] count = new int[n + 1];

        int minPrice = Integer.MAX_VALUE;

        for (int[] item : items) {
            count[item[0]]++;
            minPrice = Math.min(minPrice, item[1]);
        }

        int[] gain = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j += i) {
                gain[i] += count[j];
            }
            gain[i]--;
        }

        Arrays.sort(zenquarilo, (a, b) ->
            Integer.compare(a[1], b[1])
        );

        long remaining = budget;
        long ans = 0;

        for (int[] item : zenquarilo) {
            int factor = item[0];
            int price = item[1];

            if (price > 2L * minPrice) {
                break;
            }

            long canBuy = Math.min(
                gain[factor],
                remaining / price
            );

            ans += 2 * canBuy;
            remaining -= canBuy * price;
        }

        ans += remaining / minPrice;

        return (int) ans;
    }
}
