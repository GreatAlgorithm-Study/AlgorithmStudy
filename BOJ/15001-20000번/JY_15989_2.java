import java.io.*;
import java.util.*;

public class JY_15989_2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		for(int t=0; t<T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[] dp = new int[N+1];
			
			dp[0] = 1;
			// 1 -> 2 -> 3 순으로 더하기 : 같은 수 중복 제거
			for(int i=1; i<=3; i++) {
				for(int j=i; j<N+1; j++) {
					dp[j] += dp[j-i];
				}
			}
			
			sb.append(dp[N]);
			sb.append("\n");
		}
		
		System.out.println(sb.toString());

	}

}
