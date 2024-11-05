public class JW_1535 {

    public static void main(String[] args) throws Exception {
        int n = read();
        int[] cost = new int[n];
        for (int i = 0; i < n; i++)
            cost[i] = read();
        int[] get = new int[n];
        for (int i = 0; i < n; i++)
            get[i] = read();
        int[] dp = new int[100];
        // 중복 선택이 불가능한 0-1 Knapsack
        for (int i = 0; i < n; i++) {
            for (int j = 99; j >= cost[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - cost[i]] + get[i]);
            }
        }
        System.out.println(dp[99]);
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