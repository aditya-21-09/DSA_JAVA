class Solution {
    double favorable = 0;
    double totalWays = 0;
    int[] balls;
    int n;

    public double getProbability(int[] balls) {
        this.balls = balls;
        n = balls.length;

        int total = 0;
        for (int x : balls) total += x;

        dfs(0, 0, 0, 1.0, total / 2);

        return favorable / totalWays;
    }

    void dfs(int i, int count, int diff, double ways, int half) {
        if (count > half || count + remaining(i) < half) return;

        if (i == n) {
            if (count == half) {
                totalWays += ways;
                if (diff == 0) favorable += ways;
            }
            return;
        }

        for (int j = 0; j <= balls[i]; j++) {
            double c = combination(balls[i], j);

            int d = (j > 0 ? 1 : 0) -
                    (j < balls[i] ? 1 : 0);

            dfs(i + 1, count + j, diff + d, ways * c, half);
        }
    }

    int remaining(int i) {
        int sum = 0;
        for (int j = i; j < n; j++) {
            sum += balls[j];
        }
        return sum;
    }

    double combination(int n, int r) {
        double res = 1;
        for (int i = 1; i <= r; i++) {
            res = res * (n - i + 1) / i;
        }
        return res;
    }
}
