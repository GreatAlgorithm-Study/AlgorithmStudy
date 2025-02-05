import java.io.*;
import java.util.*;
public class JY_2169 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] g = new int[N+1][M+1];
		int[][] dp = new int[N+1][M+1];
		for(int i=1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<M+1; j++) {
				g[i][j] = Integer.parseInt(st.nextToken());
			}
		} 
		
		
		// 첫행만 오른쪽으로 누적
		for(int j=1; j<M+1; j++) {
			dp[1][j] = dp[1][j-1] + g[1][j];
		}
		
		
		for(int i=2; i<N+1; i++) {
			// t1: 왼->오
			int[] t1 = new int[M+1];
			// t2: 오-> 왼
			int[] t2 = new int[M+1];
			
			t1[1] = dp[i-1][1] + g[i][1];
			for(int j=2; j<M+1; j++) {
				t1[j] = Math.max(dp[i-1][j], t1[j-1]) + g[i][j];
			}
			
			t2[M] = dp[i-1][M] + g[i][M]; 
			for(int j=M-1; j>=0; j--) {
				t2[j] = Math.max(dp[i-1][j], t2[j+1]) + g[i][j];
			}
			
			// dp에 반영
			for(int j=1; j<M+1; j++) {
				dp[i][j] = Math.max(t1[j], t2[j]);
			}
			
		}
		
		
		System.out.println(dp[N][M]);
		
		
		
		

	}

}