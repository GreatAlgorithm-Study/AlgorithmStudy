import java.io.*;

/*
 * 줄세우기
 * 옮겨야 되는 아이들의 수 = 전체 길이 - 가장 긴 증가하는 부분 수열
 */

public class DH_2631_2 {
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		
		for(int i = 0; i < arr.length; i++) arr[i] = Integer.parseInt(br.readLine());
		
		// 가장 긴 부분 수열을 구하기 위한 dp 배열
		int[] dp = new int[N];
		int max = 0;
		
		for(int i = 0; i < dp.length; i++) {
			dp[i] = 1;
			
			for(int j = 0; j < i; j++) {
				if(arr[j] < arr[i]) {
					dp[i] = Math.max(dp[j] + 1, dp[i]);
				}
			}

			max = Math.max(max, dp[i]);
		}
		
		System.out.println(N - max);
	}
}
