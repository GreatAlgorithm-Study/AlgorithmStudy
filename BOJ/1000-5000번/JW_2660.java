import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JW_2660 {

    static final int INF = Integer.MAX_VALUE >> 2;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] graph = new int[n + 1][n + 1];
        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(graph[i], INF);
            graph[i][i] = 0;
        }
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            if (u == -1 && v == -1)
                break;
            graph[u][v] = 1;
            graph[v][u] = 1;
        }
        for (int k = 1; k < n + 1; k++)
            for (int i = 1; i < n + 1; i++)
                for (int j = 1; j < n + 1; j++)
                    if (graph[i][j] > graph[i][k] + graph[k][j]) {
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
        int max = 50;
        ArrayList<Integer> al = new ArrayList<>();
        for (int i = 1; i < n + 1; i++) {
            int score = 0;
            for (int j = 1; j < n + 1; j++)
                score = Math.max(score, graph[i][j]);
            if (max > score) {
                max = score;
                al.clear();
                al.add(i);
            } else if (max == score)
                al.add(i);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(max).append(" ").append(al.size()).append("\n");
        for (int i : al) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }
}