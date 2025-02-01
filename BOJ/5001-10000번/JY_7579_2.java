import java.io.*;
import java.util.*;

public class JY_7579_2 {

	static final int MAX = 10001;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// 메모리
		int[] mrr = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<N+1; i++) {
			mrr[i] = Integer.parseInt(st.nextToken());
		}
		// 비용
		int[] crr = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<N+1; i++) {
			crr[i] = Integer.parseInt(st.nextToken());
		}
		
		// dp[i][j] : i번째 앱까지 확인했을 떄, j원을 사용했을 때의 최소 메모리크기
		int[][] dp = new int[N+1][MAX];
		for(int i=1; i<N+1; i++) {
			for(int j=0; j<MAX; j++) {
				if(j < crr[i]) {
					dp[i][j] = dp[i-1][j];
				} else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-crr[i]]+mrr[i]);
				}
			}
		}
		
		int ans = 0;
		for(int j=0; j<MAX; j++) {
			// 메모리 크기가 M보다 첫 번째로 크거나 같을 때의 비용
			if(dp[N][j] >= M) {
				ans = j;
				break;
			}
		}
		
		System.out.println(ans);
	}

}
