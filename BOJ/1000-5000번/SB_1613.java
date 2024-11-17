import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SB_1613 {
    static int N, K;
    static int[][] order;
    static int INF = Integer.MAX_VALUE >> 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        order = new int[N + 1][N + 1];
        for (int i = 1; i < N + 1; i++) {
            Arrays.fill(order[i], INF);
            order[i][i] = 0;
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            order[a][b] = 1;
        }

        for (int k = 1; k < N + 1; k++) {
            for (int i = 1; i < N + 1; i++) {
                for (int j = 1; j < N + 1; j++) {
                    if (order[i][j] > order[i][k] + order[k][j]) {
                        order[i][j] = order[i][k] + order[k][j];
                    }
                }
            }
        }

        int S = Integer.parseInt(br.readLine());
        while (S-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (order[a][b] != INF) sb.append(-1);
            else if (order[b][a] != INF) sb.append(1);
            else sb.append(0);
            sb.append('\n');
        }

        System.out.println(sb);
    }
}
