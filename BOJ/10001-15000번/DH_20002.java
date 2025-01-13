import java.io.*;
import java.util.*;

/*
 * 사과나무
 */

public class DH_20002 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] S = new int[N + 1][N + 1];
		
		int result = Integer.MIN_VALUE;
		
		for(int r = 1; r < N + 1; r++) {
			st = new StringTokenizer(br.readLine());
			
			for(int c = 1; c < N + 1; c++) {
				int current = Integer.parseInt(st.nextToken());
				S[r][c] = S[r - 1][c] + S[r][c - 1] - S[r - 1][c - 1] + current;
				
				for(int k = 0; r - k > 0 && c - k > 0; k++) {
					int s1 = S[r][c - k - 1]; // 왼쪽
					int s2 = S[r - k - 1][c]; // 아래
					int s3 = S[r - k - 1][c - k - 1]; // 공통된 작은
					
					result = Math.max(result, S[r][c] - s1 - s2 + s3);
				}
			}
		}
		
		System.out.println(result);
	}
}
