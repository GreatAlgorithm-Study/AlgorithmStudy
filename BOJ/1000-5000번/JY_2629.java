import java.io.*;
import java.util.*;
public class JY_2629 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int[] wrr = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<N+1; i++) {
			wrr[i] = Integer.parseInt(st.nextToken());
		}
				
		st = new StringTokenizer(br.readLine());
		int C = Integer.parseInt(st.nextToken());
		int[] coins = new int[C+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<C+1; i++) {
			coins[i] = Integer.parseInt(st.nextToken());
		}
		
		// 만들 수 있는 최대 구슬의 무게 == (가장큰추의무게 * 추의 개수)
		int maxW = wrr[N] * N;
		
		
		// case2) 1차원
		int[] dp = new int[maxW+1];
		dp[0] = 1;
		
		for(int i=1; i<N+1; i++) {
			for(int j=maxW; j>=wrr[i]; j--) {
				dp[j] = dp[j] + dp[j-wrr[i]];
			}
		}
		
		for(int i=1; i<C+1; i++) {
			int c = coins[i];
			// 구슬이 최소 추의 무게보다 작다면 X
			if(c < wrr[0]) {
				sb.append("N ");
				continue;
			}
			// 구슬이 만들 수 있는 최대 무게보다 크다면 X
			if(c > maxW) {
				sb.append("N ");
				continue;
			}
			
			// 구슬 --- 추(n개)
			if(dp[c] != 0) {
				sb.append("Y ");
			} 
			// 구슬+추(n개) --- 추(m개) 
			else {
				boolean isOk = false;
				// 가지고 있는 추 n개로 만들수 있는 무게에 구슬을 더한 값이
				// 또 다른 추 m개로 만들 수 있는 무게값과 같은 것이 있는지 체크
				for(int j=1; j<maxW+1; j++) {
					if(j + c > maxW) break;
					if(dp[j] != 0 && dp[j+c] != 0) {
						sb.append("Y ");
						isOk = true;
						break;
					}
				}
				if(!isOk) sb.append("N ");
			}
		}
		
		System.out.println(sb.toString());
		
		// case1) 2차원
//		int[][] dp = new int[N+1][maxW+1];
//		for(int i=0; i<N+1; i++)  {
//			dp[i][0] = 1;
//		}
//		
//		
//		for(int i=1; i<N+1; i++) {
//			for(int j=1; j<maxW+1; j++) {
//				if(wrr[i] > j) dp[i][j] = dp[i-1][j];
//				else {
//					dp[i][j] = dp[i-1][j] + dp[i-1][j-wrr[i]];
//				}
//			}
//		}
//		
//		for(int i=1; i<N+1; i++) {
//			System.out.println(Arrays.toString(dp[i]));
//		}

	}

}
