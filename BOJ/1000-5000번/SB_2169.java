import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SB_2169 {
    static int N, M;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[N][M];

        // 첫번째 행 초기화
        dp[0][0] = board[0][0];
        for (int j = 1; j < M; j++) {       // 오른쪽으로만 탐색 가능(이미 지나온 길 탐색 불가)
            dp[0][j] = board[0][j] + dp[0][j - 1];
        }

        for (int i = 1; i < N; i++) {
            int[] left = new int[M];
            int[] right = new int[M];

            left[0] = dp[i - 1][0] + board[i][0];   // 0번째열은 일단 이전 행에서 아래로 내려와야함 (위에서 내려오는 한가지 경우)
            for (int j = 1; j < M; j++) {           // 왼쪽에서 오는거, 위에서 내려오는 두가지 경우
                left[j] = Math.max(left[j - 1], dp[i - 1][j]) + board[i][j];
            }

            right[M-1] = dp[i- 1][M-1] + board[i][M-1];
            for (int j = M - 2; j >= 0; j--) {
                right[j] = Math.max(right[j+1], dp[i-1][j]) + board[i][j];
            }

            // 왼쪽 오른쪽 값 합침
            for (int j = 0; j < M; j++) {
                dp[i][j] = Math.max(left[j], right[j]);
            }
        }

        System.out.println(dp[N-1][M-1]);
    }
}
