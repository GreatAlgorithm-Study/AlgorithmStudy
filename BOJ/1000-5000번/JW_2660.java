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
        // 그래프 선언 및 초기화
        int[][] graph = new int[n + 1][n + 1];
        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(graph[i], INF);
            graph[i][i] = 0;
        }
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            // 종료 조건
            if (u == -1 && v == -1)
                break;
            // 양방향 그래프
            graph[u][v] = 1;
            graph[v][u] = 1;
        }
        // 플로이드 와샬
        for (int k = 1; k < n + 1; k++)
            for (int i = 1; i < n + 1; i++)
                for (int j = 1; j < n + 1; j++)
                    if (graph[i][j] > graph[i][k] + graph[k][j]) {
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
        int min = 50; // 최소 스코어
        ArrayList<Integer> al = new ArrayList<>();
        for (int i = 1; i < n + 1; i++) {
            int score = 0;
            // 최단 거리 중 최댓값 찾기
            for (int j = 1; j < n + 1; j++)
                score = Math.max(score, graph[i][j]);
            // 최솟값을 갱신 할 수 있다면
            if (min > score) {
                min = score;    // 최솟값 갱신
                al.clear();     // 리스트 비우기
                al.add(i);      // 리스트에 추가
            // 최솟값과 동일하면
            } else if (min == score)
                al.add(i);      // 리스트에 추가
        }
        StringBuilder sb = new StringBuilder();
        sb.append(max).append(" ").append(al.size()).append("\n");
        for (int i : al) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }
}