import java.io.*;
import java.util.*;

public class JY_20181 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		long[] arr = new long[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<N+1; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		
		long[] dp = new long[N+1];
		int s = 1;
		int e = 1;
		int sum = 0;
		
		while(e <= N) {
			sum += arr[e];
			dp[e] = dp[e-1];	// 이전 값을 최대값으로 가져옴
			
			// K보다 작아질 떄까지 s증가
			while(sum >= K) {
				// dp[s-1]+sum-K : 시작 위치
				dp[e] = Math.max(dp[e], dp[s-1]+sum-K);
				sum -= arr[s];
				s++;
			}
			e++;
		}
		System.out.println(dp[N]);

	}

}
