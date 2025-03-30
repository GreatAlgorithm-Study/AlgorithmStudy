import java.util.ArrayList;

public class JW_1644 {

    public static void main(String[] args) throws Exception {
        int n = read();
        int cnt = 0;
        boolean[] isPrime = new boolean[n + 1];
        isPrime[0] = isPrime[1] = true;
        ArrayList<Integer> prime = new ArrayList<>();
        for (int i = 2; i < n + 1; i++) {
            if (isPrime[i])
                continue;
            prime.add(i);
            for (int j = i * 2; j < n + 1; j += i)
                isPrime[j] = true;
        }
        int l = 0, r = 0, sum = 0;
        while (r < prime.size()) {
            sum += prime.get(r);
            while (sum > n && l <= r)
                sum -= prime.get(l++);
            if (sum == n)
                cnt++;
            r++;
        }
        System.out.println(cnt);
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}