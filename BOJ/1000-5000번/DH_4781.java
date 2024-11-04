import java.io.*;
import java.util.*;

/*
사탕 가게
 */

public class DH_4781 {
	static class Candy {
		int c, p;
		public Candy(int c, int p) {
			this.c = c;
			this.p = p;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		while(true) {

			st = new StringTokenizer(br.readLine());

			int n = Integer.parseInt(st.nextToken()); // 사탕 종류의 수
			int m = (int) (Math.round(Double.parseDouble(st.nextToken()) * 100)); // 돈의 양
			if(n == 0 && m == 0) break;

			Candy[] candys = new Candy[n + 1];

			for(int i = 1; i < n + 1; i++) {
				st = new StringTokenizer(br.readLine());
				int c = Integer.parseInt(st.nextToken());
				int p = (int) (Math.round(Double.parseDouble(st.nextToken()) * 100));

				candys[i] = new Candy(c, p);
			}

			int[][] dp = new int[candys.length][m + 1];

			// dp[i][j]: j원을 쓰면서 i번 사탕까지 살 때, 살 수 있는 최대 칼로리
			for(int i = 1; i < dp.length; i++) {
				for(int j = 0; j < dp[0].length; j++) {
					if(j >= candys[i].p) {
						dp[i][j] = Math.max(candys[i].c + dp[i][j - candys[i].p], dp[i - 1][j]);
					} else {
						dp[i][j] = dp[i - 1][j];
					}
				}
			}
			sb.append(dp[n][m] + "\n");
		}

		System.out.print(sb);
	}
}