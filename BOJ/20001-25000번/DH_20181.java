import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DH_20181 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N + 1];
		long[] dp = new long[N + 1];
		
		st = new StringTokenizer(br.readLine());
	
		for(int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		long sum = 0, tmp = 0, result = 0;
		int s = 0, e = 0;
		
		// dp[i]: i 지점까지 최선을 다했을 때, 얻을 수 있는 점수
		while(e < N + 1) {
			if(sum >= K) {
				tmp = s == 0 ? 0: Math.max(tmp, dp[s - 1]);
				// tmp + sum - K
				// : (s전까지 최선을 다했을 때, 얻을 수 있는 점수) + (s ~ e까지 합계) - K
				// else 부분에서 e++가 되었으므로 dp[e - 1]을 해줘서 sum을 한 범위까지 확인할 수 있도록 함
				dp[e - 1] = Math.max(dp[e - 1], tmp + sum - K);
				result = Math.max(result, dp[e - 1]);
				sum -= arr[s++];

			} else {
				sum += arr[e++];
			}
		}
		System.out.println(result);
	}
}
