import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] dp = new int[N][3];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                dp[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] min = new int[N][3];
        int[][] max = new int[N][3];

        for (int i = 0; i < 3; i++) {
            min[0][i] = dp[0][i];
            max[0][i] = dp[0][i];
        }

        for (int i = 1; i < N; i++) {
            max[i][0] = Math.max(max[i-1][0], max[i-1][1]) + dp[i][0];
            max[i][1] = Math.max(Math.max(max[i-1][0], max[i-1][1]), max[i-1][2]) + dp[i][1];
            max[i][2] = Math.max(max[i-1][1], max[i-1][2]) + dp[i][2];

            min[i][0] = Math.min(min[i-1][0], min[i-1][1]) + dp[i][0];
            min[i][1] = Math.min(Math.min(min[i-1][0], min[i-1][1]), min[i-1][2]) + dp[i][1];
            min[i][2] = Math.min(min[i-1][1], min[i-1][2]) + dp[i][2];
        }

        int maxValue = Math.max(Math.max(max[N-1][0], max[N-1][1]), max[N-1][2]);
        int minValue = Math.min(Math.min(min[N-1][0], min[N-1][1]), min[N-1][2]);

        System.out.println(maxValue + " " + minValue);
    }
}
