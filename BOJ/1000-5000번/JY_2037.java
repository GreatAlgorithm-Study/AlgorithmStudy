import java.io.*;
import java.util.*;

public class JY_2037 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int D = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		
		long[] L = new long[P+1];
		long[] C = new long[P+1];
		long[] dp = new long[D+1];		
		for(int i=1; i<P+1; i++) {
			st = new StringTokenizer(br.readLine());
			L[i] = Long.parseLong(st.nextToken());
			C[i] = Long.parseLong(st.nextToken());
			
		}
		
		// 최솟값을 찾기 위해 
		// j==L[i] 즉, i번쨰 하나만 선택했을 떄는 C[i]로 초기화하기 위해 MAX값으로 초기화
		dp[0] = Integer.MAX_VALUE;
		
		for(int i=1; i<P+1; i++) {
			for(int j=D; j>=L[i]; j--) {
				dp[j] = Math.max(dp[j], Math.min(dp[(int)(j-L[i])], C[i]));
			}
		}
//		System.out.println(Arrays.toString(dp));
		
		System.out.println(dp[D]);
		
	}

}
