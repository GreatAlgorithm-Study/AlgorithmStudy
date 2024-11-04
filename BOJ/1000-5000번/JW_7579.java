public class JW_7579 {

    public static void main(String[] args) throws Exception {
        int n = read(), m = read();
        int[] memories = new int[n];
        for (int i = 0; i < n; i++)
            memories[i] = read();
        int[] costs = new int[n];
        int sumCost = 0;
        for (int i = 0; i < n; i++) {
            costs[i] = read();
            sumCost += costs[i];
        }
        int[][] dp = new int[n + 1][sumCost + 1];
        // 0-1 knapsack -> 중복 사용 불가
        for (int i = 1; i < n + 1; i++)
            // j 비용으로 만들 수 있는 최대 메모리 수 구하기
            for (int j = 0; j < sumCost + 1; j++) {
                dp[i][j] = dp[i - 1][j];
                // 이전 값에서 가져올 수 있다면
                if (j >= costs[i - 1]) {
                    dp[i][j] = Math.max(dp[i][j], memories[i - 1] + dp[i - 1][j - costs[i - 1]]);
                }
            }
        // 처음으로 m 이상이 나오는 금액이 최소 금액
        for (int i = 0; i < sumCost + 1; i++)
            for (int j = 1; j < n + 1; j++)
                if (dp[j][i] >= m) {
                    System.out.println(i);
                    return;
                }
    }

    // 빠른 입력 함수
    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48)
            n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13)
            System.in.read();
        return n;
    }
}