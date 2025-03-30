import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class SB_1644 {
    static int N;
    static ArrayList<Integer> primes = new ArrayList<>();

    private static void getPrimeLst() {
        boolean[] isPrime = new boolean[N + 1];
        Arrays.fill(isPrime, true);

        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i <= Math.sqrt(N); i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= N; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        for (int i = 2; i <= N; i++) {
            if (isPrime[i]) primes.add(i);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        getPrimeLst();

        int s = 0, e = 0;
        int cnt = 0;
        int sum = 0;

        while (true) {
            if (sum==N) cnt++;
            if (sum < N) {
                if (e == primes.size()) break;
                sum += primes.get(e++);
            }
            else{
                sum -= primes.get(s++);
            }
        }

        System.out.println(cnt);
    }
}
