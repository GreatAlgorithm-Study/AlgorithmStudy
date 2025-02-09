public class JW_2169 {

    public static void main(String[] args) throws Exception {
        int n = read(), m = read();
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++)
                arr[i][j] = read();
        }
        int[][] dp = new int[3][m];
        // DP 배열 초기화
        dp[0][0] = arr[0][0];
        for (int i = 1; i < m; i++)
            dp[0][i] = dp[0][i - 1] + arr[0][i];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++)
                dp[1][j] = dp[2][j] = dp[0][j] + arr[i][j]; // 아래로 내려오는 경우 
            // 오른쪽 누적합 계산 및 최댓값 갱신
            for (int j = 1; j < m; j++)
                dp[1][j] = Math.max(dp[1][j], dp[1][j - 1] + arr[i][j]);
            // 왼쪽 누적합 계산 및 최댓값 갱신
            for (int j = m - 2; j >= 0; j--)
                dp[2][j] = Math.max(dp[2][j], dp[2][j + 1] + arr[i][j]);
            // 전체 최댓값 갱신 
            for (int j = 0; j < m; j++)
                dp[0][j] = Math.max(dp[1][j], dp[2][j]);
        }
        System.out.println(dp[0][m - 1]);
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean m = n == 13;
        if (m)
            n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48)
            n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13)
            System.in.read();
        return m ? ~n + 1 : n;
    }
}
