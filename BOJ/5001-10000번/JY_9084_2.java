import java.io.*;
import java.util.*;

public class JY_9084_2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		
		for(int t=0; t<T; t++) {
			int N = Integer.parseInt(br.readLine());
			
			st = new StringTokenizer(br.readLine());
			int[] coins = new int[N+1];
			for(int i=1; i<N+1; i++) {
				// 금액이 오름차순 입력
				coins[i] = Integer.parseInt(st.nextToken());
			}
			int cost = Integer.parseInt(br.readLine());
			
			int[] dp = new int[cost+1];
			dp[0] = 1;
			// 동전 반복
			for(int i=1; i<N+1; i++) {
				int coin = coins[i];
				for(int j = coin; j<cost+1; j++) {
					// 금액 j를 만들기 위한 경우의 수 
					dp[j] += dp[j - coin];
				}
			}
			
			sb.append(dp[cost]);
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

}
