import java.io.*;
import java.util.*;

/*
 * 행렬 곱셈 순서
 */

public class DH_11049 {
	
	static class Matrix {
		int r, c;
		public Matrix(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int[][] dp;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/BOJ11049.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Matrix[] matrix = new Matrix[N];
		dp = new int[N][N]; // dp[i][j]: i번째 행렬에서 j번째 행렬까지 곱할 때, 최소 연산 횟수
		
		StringTokenizer st;

		for(int i = 0; i < matrix.length; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			matrix[i] = new Matrix(r, c);
		}
		
		System.out.println(getMinMultifly(matrix, 0, N - 1));
	}
	
	// s번째에서 e번째까지 행렬을 곱했을 때 최솟값을 구하는 함수
	static int getMinMultifly(Matrix[] matrix, int s, int e) {
		if(s == e) return 0; // 한 개의 행렬이 있을 때 → 연산횟수: 0t
		if(dp[s][e] != 0) return dp[s][e];
		if(s + 1 == e) return matrix[s].r * matrix[e].r * matrix[e].c;

		int result = Integer.MAX_VALUE;
		
		for(int i = s; i < e; i++) {

			// getMinMultifly(matrix, s, i): s부터 i까지
			// getMinMultifly(matrix, i + 1, e): i + 1부터 e까지
			// matrix[s].r * matrix[i + 1].r * matrix[e].c: i를 기준으로 나눈 다음 최종적으로 배열이 2개의 배열이 되었을 때, 배열 연산 횟수
			result = Math.min(result, 
					getMinMultifly(matrix, s, i) + getMinMultifly(matrix, i + 1, e) + (matrix[s].r * matrix[i + 1].r * matrix[e].c));
		}
		return dp[s][e] = result;
	}
	
}
