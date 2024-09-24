public class Main {

    public static void main(String[] args) throws Exception {
        int t = read();
        int[] dp = new int[10_001];
        dp[0] = 1;
        // 중복을 허용하지 않으므로 현재 값에서 -1, -2, -3의 인덱스에 있는 값을 더해줌
        for (int i = 1; i <= 3; i++)
            for (int j = i; j < 10_001; j++)
                dp[j] += dp[j - i];
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int n = read();
            sb.append(dp[n]).append("\n");
        }
        System.out.println(sb);
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