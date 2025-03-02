public class JW_2629 {

    public static void main(String[] args) throws Exception {
        int n = read();
        int[] weights = new int[n + 1];
        for (int i = 1; i < n + 1; i++)
            weights[i] = read();
        int m = read(), maxTarget = 0;
        int[] targets = new int[m];
        for (int i = 0; i < m; i++) {
            targets[i] = read();
            maxTarget = Math.max(maxTarget, targets[i]);
        }
        // 가능한 범위까지 DP 배열 생성
        boolean[] dp = new boolean[maxTarget + 501];
        dp[0] = true;
        int w;
        for (int i = 1; i < n + 1; i++) {
            w = weights[i];
            boolean[] temp = dp.clone(); // 영향을 주지 않기 위해 새로운 배열 생성
            for (int j = dp.length - 1; j >= 0; j--) {
                if (dp[j]) {
                    if (j + w < dp.length)
                        temp[j + w] = true; // 더하기
                    if (j - w >= 0)
                        temp[j - w] = true; // 뺴기
                    if (w - j >= 0)
                        temp[w - j] = true; // 반대편에 놓기
                }
            }
            dp = temp;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++)
            sb.append(dp[targets[i]] ? 'Y' : 'N').append(' ');
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