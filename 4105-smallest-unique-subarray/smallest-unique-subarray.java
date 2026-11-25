
import java.util.*;

class Solution {
    int n;
    int[] nums;
    long base = 19;
    long mod = 1000000007;
    long[] power;

    public int smallestUniqueSubarray(int[] nums) {
        this.nums = nums;
        n = nums.length;

        power = new long[n + 1];
        power[0] = 1;

        for (int i = 1; i <= n; i++) {
            power[i] = (power[i - 1] * base) % mod;
        }

        int low = 1, high = n;
        int ans = n;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (check(mid)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    boolean check(int len) {
        HashMap<Long, Integer> map = new HashMap<>();

        long hash = 0;

        for (int i = 0; i < len; i++) {
            hash = (hash * base + nums[i]) % mod;
        }

        map.put(hash, 1);

        for (int i = len; i < n; i++) {
            hash = (hash - (nums[i - len] * power[len - 1]) % mod + mod) % mod;
            hash = (hash * base + nums[i]) % mod;

            map.put(hash, map.getOrDefault(hash, 0) + 1);
        }

        for (int count : map.values()) {
            if (count == 1) {
                return true;
            }
        }

        return false;
    }
}
