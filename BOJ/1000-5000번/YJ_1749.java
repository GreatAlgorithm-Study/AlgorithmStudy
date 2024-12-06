import java.io.*;
import java.util.*;

public class YJ_1749 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken())+1;
        int M = Integer.parseInt(st.nextToken())+1;
        int [][] game = new int[N][M];

        for(int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j < M; j++) {
                game[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //누적합
        for(int i = 1; i < N; i++) {
            for(int j = 1; j < M; j++) {
                game[i][j] = game[i-1][j] + game[i][j-1] - game[i-1][j-1] + game[i][j];
            }
        }

        int max = game[1][1];
        for(int i = 1; i < N; i++) {
            for(int j = 1; j < M; j++) {
                for(int r = 0; r < i; r++) {
                    for(int k = 0; k < j; k++) {
                        max = Math.max(max, game[i][j] - game[r][j] - game[i][k] + game[r][k]);
                    }
                }
            }
        }
        System.out.println(max);
    }
}