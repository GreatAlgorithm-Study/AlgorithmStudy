import java.util.*;
import java.io.*;

public class JY_9084 {
	
	static int MAX_COST = 10001;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			
			int[] coin = new int[N+1];
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<N+1; i++) {
				coin[i] = Integer.parseInt(st.nextToken());
			}
			
			int M = Integer.parseInt(br.readLine());
			
			int[] dp = new int[MAX_COST];

			// 0원을 만드는 경우 : 1
			dp[0] = 1;
			// dp[j] : j원을 만들 수 있는 경우의 수
			// dp[j] : i-1번쨰 동전까지 확인했을 때 j를 만들 수 있는 경우의 수 + 새로운 i번쨰 동전으로 만들 수 있는 경우의수 
			for(int i=1; i<N+1; i++) {
				for(int j=coin[i]; j<M+1; j++) {
					dp[j] = dp[j] + dp[j-coin[i]];
				}
			}
			
			System.out.println(dp[M]);
		}

	}

}
