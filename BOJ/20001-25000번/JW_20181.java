public class JW_20181{

    public static void main(String[] args) throws Exception {
        int n = read(), k = read();
        long[] arr = new long[n];
        for (int i = 0; i < n; i++)
            arr[i] = read();
        long[] dp = new long[n + 1];
        int l = 0, r = 0;
        long sum = 0;
        // 투 포인터 누적합
        while (r < n) {
            sum += arr[r];
            dp[r + 1] = dp[r];
            // sum이 k이상이면 r값을 결정
            // arr[l]을 빼도 계속 k이상일 수 있으므로 while로 반복 진행
            while (sum >= k) {
                // 이전 dp에서 현재 탈피 에너지로 최댓값 갱신
                dp[r + 1] = Math.max(dp[r + 1], dp[l] + sum - k);
                sum -= arr[l];
                l++;
            }
            r++;
        }
        System.out.println(dp[n]);
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48)
            n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13)
            System.in.read();
        return n;
    }
}