import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class JM_1644 {
	static int N;
	static List<Integer> primeNumbers;

	private static void findPrimeNumber() {
		boolean[] isPrime = new boolean[N + 1];
		Arrays.fill(isPrime, true);

		for (int i = 2; i <= Math.sqrt(N); i++) {
			if(!isPrime[i]) continue;
			for(int j = i * i; j <= N; j += i) {
				isPrime[j] = false;
			}
		}

		for (int i = 2; i <= N; i++) {
			if(isPrime[i]) primeNumbers.add(i);
		}
	}

	private static int solve() {
		primeNumbers = new ArrayList<>();
		findPrimeNumber();

		int count = 0;
		int lo = 0, hi = 0;
		int sum = 0;
		while (true) {
			if(sum < N) {
				if(hi == primeNumbers.size()) break;
				sum += primeNumbers.get(hi++);
			}
			else {
				sum -= primeNumbers.get(lo++);
			}

			if(sum == N) count++;
		}
		return count;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		System.out.println(solve());
	}
}
