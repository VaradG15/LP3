public class DynamicProgramming {
    public static int knapsackDP(int W, int[] weights, int[] profits, int n) {
        int[][] dp = new int[n + 1][W + 1];

        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                } else if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(profits[i - 1] + dp[i - 1][w - weights[i - 1]], dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        return dp[n][W];
    }

    public static void main(String[] args) {
        int[] profits = {60, 100, 120};
        int[] weights = {10, 20, 30};
        int W = 50;
        int n = profits.length;

        System.out.println("Maximum value in Knapsack (DP): " + knapsackDP(W, weights, profits, n));
    }
}
