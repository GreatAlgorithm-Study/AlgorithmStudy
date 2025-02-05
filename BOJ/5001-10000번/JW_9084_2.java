public class JW_9084_2 {

    public static void main(String[] args) throws Exception {
        int t = read();
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int n = read();
            int[] money = new int[n];
            for (int i = 0; i < n; i++)
                money[i] = read();
            int m = read();
            int[] dp = new int[m + 1];
            dp[0] = 1;
            for (int i = 0; i < n; i++)
                for (int j = money[i]; j <= m; j++)
                    dp[j] += dp[j - money[i]];
            sb.append(dp[m]).append("\n");
        }
        System.out.println(sb);
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