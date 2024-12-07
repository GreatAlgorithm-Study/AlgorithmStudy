import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SB_1749 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] board = new int[N][M];
        long[][] dp = new long[N + 1][M + 1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 누적합 계산
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < M + 1; j++) {
                dp[i][j] = board[i-1][j-1] + dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1];
            }
        }

        // 부분 행렬의 최대값 찾기
        long mx = Long.MIN_VALUE;
        for (int r1 = 1; r1 < N + 1; r1++) {
            for (int c1 = 1; c1 <= M + 1; c1++) {
                for (int r2 = r1; r2 <= N; r2++) {
                    for (int c2 = c1; c2 <= M; c2++) {
                        long tmp = dp[r2][c2] - dp[r1 - 1][c2] - dp[r2][c1 - 1] + dp[r1 - 1][c1 - 1];
                        mx = Math.max(mx, tmp);
                    }
                }
            }
        }

        System.out.println(mx);
    }
}
