import java.io.*;
import java.util.*;

/*
 * 로봇 조종하기
 */

public class DH_2169 {
	static int INF = - 1_000 * 1_000 * 10;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/BOJ2169.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N + 1][M + 1];
		int[][][] dp = new int[3][N + 1][M + 1];
		
		for(int k = 0; k < 3; k++) {
			for(int r = 0; r < dp[0].length; r++) Arrays.fill(dp[k][r], INF);
		}
		for(int r = 1; r < arr.length; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 1; c < arr[0].length; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		// dp 테이블 채우기
		for(int r = 1; r < dp[0].length; r++) {
			if(r == 1) {
				dp[0][r][0] = 0;
				for(int c = 1; c < dp[0][0].length; c++) {
					dp[0][r][c] = arr[r][c] + dp[0][r][c - 1];
				}
				continue;
			}
			
			// 위에서 오는 경우
			for(int c = 1; c < dp[0][0].length; c++) {
				dp[1][r][c] = Math.max(dp[0][r - 1][c], Math.max(dp[1][r - 1][c], dp[2][r - 1][c])) + arr[r][c];
			}
			
			// 왼쪽에서 오는 경우
			for(int c = 1; c < dp[0][0].length; c++) {
				dp[0][r][c] = Math.max(dp[0][r][c - 1], dp[1][r][c - 1]) + arr[r][c];
			}
			
			// 오른쪽에서 오는 경우
			for(int c = dp[0][0].length - 2; c > 0; c--) {
				dp[2][r][c] = Math.max(dp[1][r][c + 1], dp[2][r][c + 1]) + arr[r][c];
			}
			
			
		}
		System.out.println(Math.max(dp[0][N][M], Math.max(dp[1][N][M], dp[2][N][M])));
	}
}
