public class JW_2073 {

    public static void main(String[] args) throws Exception {
        int d = read(), p = read();
        int[] length = new int[p];
        int[] width = new int[p];
        for (int i = 0; i < p; i++) {
            length[i] = read();
            width[i] = read();
        }
        int[] dp = new int[d + 1];
        dp[0] = Integer.MAX_VALUE; // 최솟값을 찾기 위해 첫 시작은 최대로 초기화
        // 중복 선택이 불가능한 0-1 Knapsack
        for (int i = 0; i < p; i++) {
            for (int j = d; j >= length[i]; j--) {
                // j길이를 만들었을 때 가질 수 있는 파이프의 최대 넓이
                dp[j] = Math.max(dp[j], Math.min(dp[j - length[i]], width[i]));
            }
        }
        System.out.println(dp[d]);
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