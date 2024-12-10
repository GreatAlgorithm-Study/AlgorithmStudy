public class JW_1749 {

    public static void main(String[] args) throws Exception {
        int n = read(), m = read(), max = Integer.MIN_VALUE;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i < n + 1; i++)
            for (int j = 1; j < m + 1; j++)
                // (i, j)까지의 누적합
                dp[i][j] = read() + dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1];
        for (int i = 1; i < n + 1; i++)
            for (int j = 1; j < m + 1; j++)
                for (int k = 1; k < i + 1; k++)
                    for (int l = 1; l < j + 1; l++) {
                        // 부분 배열의 누적합 구하기
                        max = Math.max(max, dp[i][j] - dp[k - 1][j] - dp[i][l - 1] + dp[k - 1][l - 1]);
                    }
        System.out.println(max);
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