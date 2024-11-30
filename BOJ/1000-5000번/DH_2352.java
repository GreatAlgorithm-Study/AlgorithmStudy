import java.io.*;
import java.util.*;

/*
 * 반도체 설계
 */

public class DH_2352 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		
		int[] arr = new int[n];
		int[] dp = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());
		
//		int result = getResultByDP(arr, dp);
		int result = getResultByBinarySearch(arr);
		
		System.out.println(result);
	}
	
	// 이분탐색을 통해 LIS의 길이 구하기
	static int getResultByBinarySearch(int[] arr) {
		int[] lis = new int[arr.length];
		
		int cnt = 1;
		
		lis[0] = arr[0];
		
		for(int i = 1; i < arr.length; i++) {
			if(lis[cnt - 1] < arr[i]) lis[cnt++] = arr[i];
			else {
				int idx = binarySearch(lis, arr[i], 0, cnt);
				lis[idx] = arr[i];
			}
		}
		
		return cnt;
	}
	
	// lower bound 구하기
	static int binarySearch(int[] lis, int value, int s, int e) {
		while(s <= e) {
			int m = (s + e) / 2;
			if(lis[m] < value) s = m + 1;
			else e = m - 1;
		}
		return s;
	}
	
	// 단순 DP를 통해 LIS 구하기
	static int getResultByDP(int[] arr, int[] dp) {
		int result = 0;
		
		for(int i = 0; i < arr.length; i++) {
			dp[i] = 1;
			
			for(int j = 0; j < i; j++) {
				if(dp[i] < dp[j] + 1 && arr[j] < arr[i]) {
					dp[i] = dp[j] + 1;
				}
			}
			
			result = Math.max(result, dp[i]);
		}
		
		return result;
	}
}
