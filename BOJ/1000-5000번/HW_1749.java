import java.io.*;
import java.util.StringTokenizer;

public class HW_1749 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] board = new int[N+1][M+1];
        int[][] dp = new int[N+1][M+1];
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=M; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=1; i<=N; i++){
            for(int j=1; j<=M; j++){
                dp[i][j] = board[i][j] + dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1];
            }
        }

        int max = Integer.MIN_VALUE;
        for(int i=1; i<=N; i++){
            for(int j=1; j<=M; j++){
                for(int r=1; r<=i; r++){ // (r1, c1) ~ (r2, c2)
                    for(int c=1; c<=j; c++){
                        max = Math.max(max, dp[i][j]-dp[r-1][j] - dp[i][c-1] + dp[r-1][c-1]);
                    }
                }
            }
        }
        System.out.println(max);
    }
}