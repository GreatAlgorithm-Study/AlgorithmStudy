public class JM_389480 {

    public static int solution(int[][] info, int n, int m) {
		int L = info.length + 1;
		boolean[][][] dp = new boolean[L + 1][n][m];

		dp[0][0][0] = true;
		for (int k = 1; k < L; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if(!dp[k - 1][i][j]) continue;
					if(i + info[k - 1][0] < n) {
						dp[k][i + info[k - 1][0]][j] = true;
					}
					if(j + info[k - 1][1] < m) {
						dp[k][i][j + info[k - 1][1]] = true;
					}
				}
			}
		}

		int answer = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(dp[L - 1][i][j]) {
					answer = Math.min(answer, i);
				}
			}
		}
		return answer == Integer.MAX_VALUE ? -1 : answer;
	}

}
