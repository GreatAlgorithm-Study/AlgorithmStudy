import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JM_3151 {
	static int N;
	static int[] A;

	private static int lowerBound(int startIndex, int target) {
		int lo = startIndex, hi = N - 1;
		int ans = N;
		while (lo <= hi){
			int mid = (lo + hi) / 2;
			if(target <= A[mid]) {
				ans = mid;
				hi = mid - 1;
			}
			else lo = mid + 1;
		}
		return ans;
	}

	private static int upperBound(int startIndex, int target) {
		int lo = startIndex, hi = N - 1;
		int ans = N;
		while (lo <= hi){
			int mid = (lo + hi) / 2;
			if(target < A[mid]) {
				ans = mid;
				hi = mid - 1;
			}
			else lo = mid + 1;
		}
		return ans;
	}

	private static long solve() {
		Arrays.sort(A);
		long count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				int target = 0 - (A[i] + A[j]);
				int lowerIndex = lowerBound(j + 1, target);
				int upperIndex = upperBound(j + 1, target);
				count += upperIndex - lowerIndex;
			}
		}
		return count;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		A = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		System.out.println(solve());
	}
}
