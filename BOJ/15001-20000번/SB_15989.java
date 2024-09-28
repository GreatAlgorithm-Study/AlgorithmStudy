import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 1, 2, 3 더하기 4

public class SB_15989 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        int[] nums = new int[T];
        int mx = -1;
        for (int i = 0; i < T; i++) {
            nums[i] = Integer.parseInt(br.readLine());
            if (nums[i] > mx) mx = nums[i];
        }

        int[][] dp = new int[4][mx + 1];

        Arrays.fill(dp[1], 1);
        dp[1][0] = 0;

        for (int i = 2; i <= 3; i++) {
            for (int j = i; j <= mx; j++) {
                if (i==j) {
                    dp[i][j] = 1;
                    continue;
                }
                for (int k = i; k > 0; k--) {
                    dp[i][j] += dp[k][j-i];
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int n : nums) {
            sb.append(dp[1][n] + dp[2][n] + dp[3][n]).append('\n');
        }
        System.out.println(sb);
    }
}