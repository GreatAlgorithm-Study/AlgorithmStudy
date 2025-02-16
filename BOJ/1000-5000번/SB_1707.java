import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SB_1707 {
    static int[] visited;
    static ArrayList<ArrayList<Integer>> adj;

    private static boolean bfs(int node) {
        Deque<Integer> que = new ArrayDeque<>();
        que.offer(node);
        visited[node] = 0;        // 처음은 0으로 시작

        while (!que.isEmpty()) {
            int cur = que.poll();
            for (int nxt : adj.get(cur)) {
                if (visited[nxt] == -1) {
                    visited[nxt] = visited[cur] ^ 1;  // 다른 팀 배정
                    que.offer(nxt);
                } else if (visited[nxt] == visited[cur]) {
                    return false;  // 같은 그룹이면 이분 그래프가 아님
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int K = Integer.parseInt(br.readLine());
        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            adj = new ArrayList<>();
            for (int i = 0; i <= V; i++) {
                adj.add(new ArrayList<>());
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                adj.get(u).add(v);
                adj.get(v).add(u);
            }

            visited = new int[V + 1];
            Arrays.fill(visited, -1);

            boolean isBipartite = true;
            for (int i = 1; i <= V; i++) {
                if (visited[i] == -1) {  // 방문하지 않은 노드가 있다면 새롭게 탐색
                    if (!bfs(i)) {
                        isBipartite = false;
                        break;
                    }
                }
            }

            sb.append(isBipartite ? "YES\n" : "NO\n");
        }

        System.out.print(sb);
    }
}
