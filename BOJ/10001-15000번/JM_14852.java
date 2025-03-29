import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JM_14852 {
	static int N;
	static final int MOD = 1_000_000_007;

	private static long solve() {
		if(N == 1) return 2;
		else if(N == 2) return 7;

		long[] dp = new long[N + 1];
		long[] sum = new long[N + 1];

		dp[1] = 2;
		dp[2] = 7;
		sum[2] = 1;

		for (int i = 3; i <= N; i++) {
			sum[i] = (dp[i - 3] + sum[i - 1]) % MOD;
			dp[i] = (2 * dp[i - 1] + 3 * dp[i - 2] + 2 * sum[i]) % MOD;
		}

		return dp[N];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		System.out.println(solve());
	}
}
