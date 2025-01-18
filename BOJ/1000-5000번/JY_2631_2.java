import java.io.*;
import java.util.*;

public class JY_2631_2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		int[] dp = new int[N];
		
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			dp[i] = 1;
		}
		
		// i번쨰 보다 작은 것들 중에 arr[i]보다 작은 것이 있다면 LIS 길이 갱신
		for(int i=0; i<N; i++) {
			for(int j=0; j<i; j++) {
				if(arr[i] > arr[j]) {
					dp[i] = Math.max(dp[i], dp[j]+1);
				}
			}
		}
		
		int ans = 0;
		for(int i=0; i<N; i++) {
			ans = Math.max(dp[i], ans);
		}
		
		System.out.println(N-ans);

	}

}
