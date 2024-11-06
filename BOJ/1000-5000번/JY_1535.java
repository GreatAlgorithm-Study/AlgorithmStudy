import java.io.*;
import java.util.*;

public class JY_1535 {
	
	static int MAX_HP = 100;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		int[] h = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<N+1; i++) {
			h[i] = Integer.parseInt(st.nextToken());
		}
		int[] v = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<N+1; i++) {
			v[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[MAX_HP+1];
		for(int i=1; i<N+1; i++) {
			for(int j=MAX_HP; j>h[i]; j--) {
				dp[j] = Math.max(dp[j], dp[j-h[i]]+v[i]);
			}
		}
		
//		System.out.println(Arrays.toString(dp));
		
		// 최대 기쁨 찾기
		int maxHappy = Integer.MIN_VALUE;
		for(int j=1; j<101; j++) {
			maxHappy = Math.max(maxHappy, dp[j]);
		}
		
		System.out.println(maxHappy);
	}

}
