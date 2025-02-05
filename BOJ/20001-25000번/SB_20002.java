import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SB_20002 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {          // 누적합
            for (int j = 1; j <= N; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + board[i - 1][j - 1];
            }
        }

        int mx = Integer.MIN_VALUE;
        for (int k = 0; k < N; k++) {
            for (int i = 1; i < N - k + 1; i++) {
                for (int j = 1; j < N - k + 1; j++) {
                    int box = dp[i+k][j+k]-dp[i-1][j+k]-dp[i+k][j-1]+dp[i-1][j-1];
                    mx = Math.max(mx, box);
                }
            }
        }

        System.out.println(mx);

    }
}
