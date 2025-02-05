public class JW_20002 {

    public static void main(String[] args) throws Exception {
        int n = read();
        int maxArea = Integer.MIN_VALUE;
        int[][] dp = new int[n + 1][n + 1]; // 누적합 배열
        for (int i = 1; i < n + 1; i++)
            for (int j = 1; j < n + 1; j++)
                dp[i][j] = read() + dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1];
        for (int i = 1; i < n + 1; i++)
            for (int j = 1; j < n + 1; j++)
                // 정사각형으로 계산하기 위함
                for (int k = 0; k < j && k < i; k++) {
                    int a1 = dp[i - k - 1][j];
                    int a2 = dp[i][j - k - 1];
                    int a3 = dp[i - k - 1][j - k - 1]; // 겁치는 영역
                    maxArea = Math.max(maxArea, dp[i][j] - a1 - a2 + a3);
                }
        System.out.println(maxArea);
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