import java.util.*;
import java.io.*;

public class JY_7579 {
	
	// 최대 비용(100개 * 100)
	static int MAX_COST = 10001;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] mrr = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<N+1; i++) {
			mrr[i] = Integer.parseInt(st.nextToken());
		}
		int[] crr = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<N+1; i++) {
			crr[i] = Integer.parseInt(st.nextToken());
		}
		
		// dp[i][j] : i번쨰까지 확인했을때, j비용으로 비활성화할 수 있는 최대메모리
		int[][] dp = new int[N+1][MAX_COST];
		for(int i=1; i<N+1; i++) {
			// 비용은 0이될 수 있음
			for(int j=0; j<MAX_COST; j++) {
				if(j < crr[i]) {
					dp[i][j] = dp[i-1][j];
				} else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-crr[i]]+mrr[i]);
				}
			}
		}
		
		int ans = 0;
		// 적은 비용부터 탐색하며서 비활성화한 최대메모리가 M이상이면, 그때 비용이 최소비용
		for(int j=0; j<MAX_COST; j++) {
			if(dp[N][j] >= M) {
				ans = j;
				break;
			}
		}
		
		System.out.println(ans);
	}

}
