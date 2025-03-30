import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SB_14852 {
    static int MOD = 1_000_000_007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        if (N==1) {
            System.out.println(2);
            return;
        }
        if (N==2) {
            System.out.println(7);
            return;
        }
        
        long[] dp = new long[N + 1];

        dp[1] = 2;
        dp[2] = 7;
        
        long sum = 2;

        for (int i = 3; i <= N; i++) {
            dp[i] = (2 * dp[i - 1] + 3 * dp[i - 2] + sum) % MOD;
            sum += dp[i - 2] * 2 % MOD;
        }
        System.out.println(dp[N]);
    }
}
