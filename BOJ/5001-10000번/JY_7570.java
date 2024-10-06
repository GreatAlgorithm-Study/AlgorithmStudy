package day1002;

import java.util.*;
import java.io.*;

public class JY_7570 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int[] crr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			crr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 내번호 num 보다 작은 수중 먼저 나온 수가 있으면 LIS 길이 증가
		// 즉, dp[num]은 num까지 번호가 연속된 아이들의 길이
		int[] dp = new int[N+1];
		for(int i=0; i<N; i++) {
			int num = crr[i];
			
			if(dp[num-1] == 0) dp[num]++;
			else dp[num] = dp[num-1] +1;
		}
//		System.out.println(Arrays.toString(dp));
		
		int ans = 0;
		for(int i=0; i<N+1; i++) {
			ans = Math.max(ans, dp[i]);
		}
		
		// 전체 길이 - LIS 길이
		System.out.println(N - ans);

	}


}
