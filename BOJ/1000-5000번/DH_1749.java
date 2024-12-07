import java.io.*;
import java.util.*;

/*
 * 점수따먹기
 */

public class DH_1749 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] S = new int[N + 1][M + 1];
		
		for(int r = 1; r < N + 1; r++) {
			st = new StringTokenizer(br.readLine());
			
			for(int c = 1; c < M + 1; c++) {
				int current = Integer.parseInt(st.nextToken());
				S[r][c] = S[r - 1][c] + S[r][c - 1] - S[r - 1][c - 1] + current;
			}
		}
		
		int max = Integer.MIN_VALUE;
		for(int r = 1; r < S.length; r++) {
			for(int c = 1; c < S[0].length; c++) {
				for(int rr = 1; rr < r + 1; rr++) {
					for(int cc = 1; cc < c + 1; cc++) {
						// arr[rr][cc]에서 arr[r][c] 까지 누적합 구하기
						// 제일 큰 직사각형
						int s1 = S[r][c];
						
						// 아래 직사각형
						int s2 = S[rr - 1][c];
						
						// 왼쪽 직사각형
						int s3 = S[r][cc - 1];
						
						// 공통 직사각형
						int s4 = S[rr - 1][cc - 1];
						
						int sum = s1 - s2 - s3 + s4;
						max = Math.max(max, sum);
					}
				}
			}
		}
		
		System.out.println(max);
	}
}
