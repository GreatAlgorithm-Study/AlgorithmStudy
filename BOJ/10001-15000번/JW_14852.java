import java.util.Arrays;
import java.util.PriorityQueue;

public class JW_14852 {
    static long dp[];
    static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws Exception {
        int n = read();
        dp = new long[n + 1];
        dp[0] = 1;
        dp[1] = 2;
        if (n > 1)
            dp[2] = 7;
        long temp = 20;
        for (int i = 3; i <= n; i++) {
            dp[i] = (temp + dp[i - 2]) % MOD;
            temp = (temp + dp[i] * 2) % MOD;
        }
        System.out.println(dp[n]);
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
