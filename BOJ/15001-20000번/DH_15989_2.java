import java.io.*;

/*
 * 1, 2, 3 더하기 4
 */

public class DH_15989_2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		int[] dp = new int[10_001];
		setDp(dp);		
		
		
		for(int i = 0; i < T; i++) {
			int n = Integer.parseInt(br.readLine());
			
			sb.append(dp[n]).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void setDp(int[] dp) {
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 3;

		// 점화식: dp[i] = 1 + (i / 2) + dp[i - 3]
		for(int i = 4; i < dp.length; i++) {
			dp[i] = 1 + (i / 2) + dp[i - 3];
		}
	}
}
