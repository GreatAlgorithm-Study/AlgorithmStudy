package day0918;

import java.util.*;
import java.io.*;

public class JY_2631 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int[] dp = new int[N];
		
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			dp[i] = 1;	// 자기자신의 길이
		}
//		System.out.println(Arrays.toString(arr));
		
		// 가장 증가하는 부분 수열을 제외한 나머지를 옮겨야 함
		for(int i=0; i<N; i++) {
			for(int j=0; j<i; j++) {
				if(arr[j] < arr[i]) {
					dp[i] = Math.max(dp[i], dp[j]+1);
				}
			}
		}
		int mLen = 0;
		for(int i=0; i<N; i++) {
			mLen = Math.max(mLen, dp[i]);
		}
//		System.out.println(Arrays.toString(dp));
		System.out.println(N-mLen);
		
	}


}
