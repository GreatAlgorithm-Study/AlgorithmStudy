import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JM_2629 {
	static int N;
	static int[] W;
	static int M;
	static int maxW;
	static boolean[][] dp;

	private static void solve() {
		for (int i = 1; i <= N; i++) {
			dp[i][W[i]] = true;
			for (int j = 1; j <= maxW; j++) {
				if(dp[i - 1][j]) dp[i][j] = true;
				if(j + W[i] <= maxW && dp[i - 1][j + W[i]]) dp[i][j] = true;
				if(dp[i - 1][Math.abs(j - W[i])]) dp[i][j] = true;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		W = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			W[i] = Integer.parseInt(st.nextToken());
			maxW += W[i];
		}

		dp = new boolean[N + 1][maxW + 1];
		solve();

		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int bead = Integer.parseInt(st.nextToken());
			if(bead > maxW) sb.append("N").append(" ");
			else sb.append(dp[N][bead] ? "Y" : "N").append(" ");
		}
		System.out.println(sb);
	}
}
