import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
* 트리의 지름
* - 루트로부터 가장 먼 노드(k) 찾기
* - k로부터 가장 먼 노드 찾기
* */
public class SB_1967_2 {
    static int N;
    static List<List<Node>> adj = new ArrayList<>();
    static boolean[] visited;
    static int tmp;
    static int mx = -1;

    private static void dfs(int cur, int dist) {
        if (dist > mx) {        // 먼 노드가 업데이트 됐을때
            mx = dist;
            tmp = cur;          // tmp 찾기위한 노드 업데이트
        }

        visited[cur] = true;
        for (Node nxt : adj.get(cur)) {
            if (visited[nxt.idx]) continue;
            dfs(nxt.idx, dist+ nxt.cost);
            visited[nxt.idx] = true;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N + 1; i++) {
            adj.add(new ArrayList<>());
        }
        visited = new boolean[N + 1];

        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adj.get(p).add(new Node(c, w));             // 부모와 자식에 각각 정보 넣어줒기
            adj.get(c).add(new Node(p, w));
        }

        dfs(1, 0);

        visited = new boolean[N + 1];
        dfs(tmp, 0);

        System.out.println(mx);
    }

    static class Node{
        int idx, cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }
}
