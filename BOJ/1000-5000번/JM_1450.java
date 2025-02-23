import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 완전 틀림, 해결책 제시 X
 */
public class JM_1450 {
	static int N;
	static int C;
	static int[] W;

	private static int upperBound(int target, List<Integer> A) {
		int lo = 0;
		int hi = A.size() - 1;
		int ans = A.size();
		while (lo <= hi) {
			int mid = (lo + hi) / 2;
			if(target < A.get(mid)) {
				ans = mid;
				hi = mid - 1;
			}
			else lo = mid + 1;
		}
		return ans;
	}

	private static void combi(int start, int end, int sum, List<Integer> combiSums) {
		if(sum > C) return;
		if(start == end) {
			combiSums.add(sum);
			return;
		}
		combi(start + 1, end, sum, combiSums);
		combi(start + 1, end, sum + W[start], combiSums);
	}

	private static int solve() {
		List<Integer> left = new ArrayList<>();
		List<Integer> right = new ArrayList<>();

		combi(0, N / 2, 0, left);
		combi(N / 2, N, 0, right);

		Collections.sort(right);

		int count = 0;
		for(int leftSum : left) {
			count += upperBound(C - leftSum, right);
		}
		return count;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		W = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			W[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(solve());
	}
}
