import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 꿀벌(0), 꿀벌(i), 벌통(N-1)
 * 벌통(0), 꿀벌(i), 꿀벌(N-1)
 * 꿀벌(0), 벌통(i), 꿀벌(N-1)
 */
public class JM_21758 {
	static int N;
	static int[] honey;

	private static long solve() {
		int[] psum = new int[N];

		psum[0] = honey[0];
		for (int i = 1; i < N; i++) {
			psum[i] += psum[i - 1] + honey[i];
		}

		long maxSum = 0;
		for (int i = 1; i < N - 1; i++) {
			maxSum = Math.max(maxSum, (psum[N - 1] - honey[0] - honey[i]) + (psum[N - 1] - psum[i]));
			maxSum = Math.max(maxSum, (psum[i - 1]) + (psum[N - 1] - honey[i] - honey[N - 1]));
			maxSum = Math.max(maxSum, (psum[i] - honey[0]) + (psum[N - 1] - psum[i - 1] - honey[N - 1]));
		}
		return maxSum;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		honey = new int[N];


		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			honey[i] = Integer.parseInt(st.nextToken());
		}
		System.out.println(solve());
	}
}
