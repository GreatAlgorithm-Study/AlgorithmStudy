import java.io.*;

public class JY_14852 {
    static final int INF = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());


        long[] dp = new long[N + 1];
        dp[0] = 1;
        dp[1] = 2;

        long sum = dp[0] + dp[1];

        for (int i = 2; i <= N; i++) {
            dp[i] = (2 * sum + dp[i - 2]) % INF;
            sum = (sum + dp[i]) % INF;  
        }

        System.out.println(dp[N]);
    }
}
