import java.util.ArrayList;

public class JW_12784 {

    static final int INF = Integer.MAX_VALUE;

    static ArrayList<ArrayList<int[]>> graph;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        int t = read();
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int n = read(), m = read();
            if(n == 1) {
                sb.append(0).append("\n");
                continue;
            }
            graph = new ArrayList<>();
            for (int i = 0; i < n + 1; i++)
                graph.add(new ArrayList<>());
            visited = new boolean[n + 1];
            int u, v, d;
            for (int i = 0; i < m; i++) {
                u = read();
                v = read();
                d = read();
                graph.get(u).add(new int[] { v, d });
                graph.get(v).add(new int[] { u, d });
            }
            sb.append(recursive(new int[] { 1, INF })).append("\n");
        }
        System.out.println(sb);
    }

    private static int recursive(int[] u) {
        visited[u[0]] = true;
        int minD = 0;
        // 자식이 가지는 모든 값
        for (int[] v : graph.get(u[0]))
            if (!visited[v[0]])
                minD += recursive(v);
        // 현재 섬으로 오는 가중치와 자식으로 가는 간선 가중치의 합을 비교
        return Math.min(minD == 0 ? INF : minD, u[1]); // 갱신되지 않았을 경우 INF 반환
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48)
            n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13)
            System.in.read();
        return n;
    }
}
