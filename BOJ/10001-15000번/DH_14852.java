import java.io.*;

/*
 * 타일 채우기 3
 */

public class DH_14852 {
	static final int MOD = 1_000_000_007;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		long[] dp = new long[Math.max(4, N + 1)];
		
		dp[1] = 2;
		dp[2] = 7;
		
		long sum = 2; // 기본으로 더해지는거
		
		for(int i = 3; i < dp.length; i++) {
			// sum 에는 dp[i - 3] * 2 + dp[i - 4] * 2 + dp[i - 5] * 2 ...가 저장되어 있음
			dp[i] = (dp[i - 1] * 2 + dp[i - 2] * 3 + sum) % MOD;
			sum += dp[i - 2] * 2;
		}

		System.out.println(dp[N] % MOD);
	}
}
