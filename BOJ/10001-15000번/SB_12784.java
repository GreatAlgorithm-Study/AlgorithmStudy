import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SB_12784 {
    static List<List<Node>> adj;
    static boolean[] parents;

    private static int dfs(int node) {
        int cnt = 0;
        parents[node] = true;

        for (Node nxt : adj.get(node)) {
            if (parents[nxt.idx]) continue;     // 부모는 패쓰
            cnt += Math.min(nxt.val, dfs(nxt.idx));
        }

        if (cnt==0 && adj.get(node).size()==1) return adj.get(node).get(0).val; // 리프노드면 부모와 이어진 값 반환
        return cnt;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            if (N==1) {
                sb.append(0).append("\n");
                continue;
            }
            adj = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                adj.add(new ArrayList<>());
            }
            parents = new boolean[N + 1];
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                adj.get(u).add(new Node(v, c));
                adj.get(v).add(new Node(u, c));
            }
            sb.append(dfs(1)).append("\n");
        }

        // 1과 연결된 부모 노드 구하기 (재귀)
        // 해당 노드와 연결된 리프노드들에서 해당 노드로 오는 값의 합, 해당 노드에서 부모노드까지 합 중 최소값 선택
        System.out.println(sb);
    }

    private static class Node{
        int idx, val;

        public Node(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }
    }
}
