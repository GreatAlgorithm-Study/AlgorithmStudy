import java.util.*;
import java.io.*;

public class JY_4781 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			double m = Double.parseDouble(st.nextToken());
			
			if(N == 0) break;
			
			// 실수 -> 정수화 할때, 오차범위 0.5
			int M = (int)(m * 100 + 0.5);	 
			int[] dp = new int[M+1];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				int kcal = Integer.parseInt(st.nextToken());
				int p = (int)(Double.parseDouble(st.nextToken())*100+0.5);
				
				// dp[j]: 돈 j로 살 수 있는 최대 칼로리
				// dp[j] = max(dp[j], dp[j-p]+kcal)
				for(int j=p; j<M+1; j++) {
					dp[j] = Math.max(dp[j], dp[j-p]+kcal);
				}
				
			}
			System.out.println(dp[M]);
		}

	}

}
