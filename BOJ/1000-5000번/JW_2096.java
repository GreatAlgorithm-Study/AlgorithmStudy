public class JW_2096 {

    public static void main(String[] args) throws Exception {
        int n = read();
        int[] maxDp = new int[3];
        int[] minDp = new int[3];
        for (int i = 0; i < 3; i++) {
            int p = read();
            maxDp[i] = p;
            minDp[i] = p;
        }
        // DP: 현재 값으로 다음 값 결정하지
        for (int i = 1; i < n; i++) {
            int[] next = { read(), read(), read() };
            int[] nextMaxDp = new int[3];
            int[] nextMinDp = new int[3];

            // 가능한 범위 내에서 최댓값 찾기
            nextMaxDp[0] = next[0] + Math.max(maxDp[0], maxDp[1]);
            nextMaxDp[1] = next[1] + Math.max(Math.max(maxDp[0], maxDp[1]), maxDp[2]);
            nextMaxDp[2] = next[2] + Math.max(maxDp[1], maxDp[2]);

            // 가능한 범위 내에서 최솟값 찾기
            nextMinDp[0] = next[0] + Math.min(minDp[0], minDp[1]);
            nextMinDp[1] = next[1] + Math.min(Math.min(minDp[0], minDp[1]), minDp[2]);
            nextMinDp[2] = next[2] + Math.min(minDp[1], minDp[2]);

            // DP 배열 갱신
            maxDp = nextMaxDp;
            minDp = nextMinDp;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Math.max(maxDp[0], Math.max(maxDp[1], maxDp[2])));
        sb.append(" ").append(Math.min(minDp[0], Math.min(minDp[1], minDp[2])));
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