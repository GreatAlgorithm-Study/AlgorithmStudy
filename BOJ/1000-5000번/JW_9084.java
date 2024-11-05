public class JW_9084 {

    public static void main(String[] args) throws Exception {
        int t = read();
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int n = read();
            int[] coins = new int[n]; // 동전 단위를 저장하기 위한 배열
            for (int i = 0; i < n; i++)
                coins[i] = read();
            int m = read();
            int[] dp = new int[m + 1]; // i의 돈을 만들기 위한 경우의 수를 저장할 배열
            dp[0] = 1;
            // knapsack - 중복 사용 가능
            for (int coin : coins)
                // 현재 동전으로 만들 수 있는 조합의 수 계산
                for (int i = coin; i < m + 1; i++)
                    dp[i] += dp[i - coin];
            sb.append(dp[m]).append("\n");
        }
        System.out.println(sb);
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