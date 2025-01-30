import java.io.*;
import java.util.*;

/*
 * 동전
 */

public class DH_9084_2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine()); // 동전의 가짓수
			
			int[] arr = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < arr.length; i++) arr[i] = Integer.parseInt(st.nextToken());
			
			int M = Integer.parseInt(br.readLine());
			
			int[] dp = new int[M + 1];
			
			// dp[i]: i원을 만들 수 있는 가짓수
			dp[0] = 1;
			
			for(int i = 0; i < arr.length; i++) {
				for(int j = arr[i]; j < dp.length; j++) {
					dp[j] += dp[j - arr[i]];
				}
			}

			System.out.println(dp[M]);
		}
	}
}
