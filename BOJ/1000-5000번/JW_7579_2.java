public class JW_7579_2 {

    static int n, m;
    static int[] mArr;
    static int[] cArr;

    public static void main(String[] args) throws Exception {
        n = read();
        m = read();
        mArr = new int[n];
        cArr = new int[n];
        for (int i = 0; i < n; i++)
            mArr[i] = read();
        int maxCost = 0;
        for (int i = 0; i < n; i++) {
            cArr[i] = read();
            maxCost += cArr[i];
        }
        int[] dp = new int[maxCost + 1]; // 냅색 DP
        int minCost = Integer.MAX_VALUE;
        // 1차원 냅색
        for (int i = 0; i < n; i++)
            for (int j = maxCost; j >= cArr[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - cArr[i]] + mArr[i]);
                if (dp[j] >= m)
                    minCost = Math.min(minCost, j);
            }
        System.out.println(minCost);
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48)
            n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13)
            System.in.read();
        return n;
    }
}