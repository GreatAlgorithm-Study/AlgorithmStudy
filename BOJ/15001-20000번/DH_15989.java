import java.io.*;

/*
1, 2, 3 더하기 4
 */

public class DH_15989 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] dp = new int[10_001];

        dp[1] = 1; dp[2] = 2; dp[3] = 3;
        for(int i = 4; i < dp.length; i++) dp[i] = dp[i - 3] + i / 2 + 1;

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < T; i++) {
            int num = Integer.parseInt(br.readLine());
            sb.append(dp[num]).append("\n");
        }

        System.out.println(sb);
    }
}
