import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SB_9084 {
    private static int calMethod(int N, int[] coin, int M) {
        int[] dp = new int[M + 1];
        dp[0] = 1;

        for (int c : coin) {
            for (int i = c; i <= M; i++) {
                dp[i] += dp[i - c];
            }
        }
        return dp[M];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] coin = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                coin[i] = Integer.parseInt(st.nextToken());
            }
            int M = Integer.parseInt(br.readLine());

            sb.append(calMethod(N, coin, M)).append('\n');
        }
        System.out.println(sb);
    }
}
