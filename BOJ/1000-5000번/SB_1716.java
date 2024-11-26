import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SB_1716 {
    static int N, M;
    static List<List<Node>> adj = new ArrayList<>();
    static int[][] parents;
    static int mxDepth;
    static int[] depths;
    static int[] dist;      // 각 정점에서 루트로 부터 떨어진 거리

    private static int LCA(int a, int b) {      // 공통 조상 구하기
        if (depths[a] < depths[b]) {            // a의 깊이가 깊도록 설정
            int tmp = b;
            b = a;
            a = tmp;
        }

        for (int i = mxDepth - 1; i >= 0; i--) {            // 깊이 동일하기 맞추기
            if (depths[a] - depths[b] >= (1 << i)) {        // 깊이 차가 2제곱꼴만큼 크다면
                a = parents[a][i];                          // 더 깊은 노드를 i만큼 점프
            }
        }

        if (a==b) return a;                                 // 깊이 조정했을때 바로 부모가 같으면 끝

        for (int i = mxDepth - 1; i >= 0; i--) {            // 부모가 다르면 같이 부모찾으러 떠나기
            if (parents[a][i] != parents[b][i]) {
                a = parents[a][i];
                b = parents[b][i];
            }
        }
        return parents[a][0];           // 최종적으로 찾은 부모 값 반환
    }
    private static void setTree(int depth, int x, int p, int cost) {
        depths[x] = depth;
        dist[x] = cost;         // 루트에서 현재 노드x까지의 거리
        for (Node nxt : adj.get(x)) {
            if (nxt.u==p) continue;     // 다음 노드가 부모노드를 가르키면 패쓰
            parents[nxt.u][0] = x;      // 다음 노드의 바로 위 부모를 자신으로 설정
            setTree(depth + 1, nxt.u, x, cost + nxt.cost);
        }
    }

    private static void setParents() {      // 2제곱별 부모값 구하기, setTree에서 구한 바로 직전 부모값을 바탕으로 2^i번째 부모 값 구하기
        for (int i = 1; i <= mxDepth; i++) {
            for (int j = 1; j < N + 1; j++) {
                parents[j][i] = parents[parents[j][i - 1]][i - 1];      // j노드의 2^i번째 값은, 2^i의 조상의 조상 값
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        depths = new int[N + 1];
        dist = new int[N + 1];
        mxDepth = (int)Math.ceil(Math.log(N) / Math.log(2)) + 1;        // 최대높이 구하기
        parents = new int[N + 1][mxDepth+1];
        for (int i = 0; i < N + 1; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adj.get(u).add(new Node(v, c));
            adj.get(v).add(new Node(u, c));
        }

        // 트리 구성하기
        setTree(1, 1, 0, 0);    // 1번노드를 루트로 깊이1부터 시작
        setParents();

        M = Integer.parseInt(br.readLine());
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(dist[a] + dist[b] - (dist[LCA(a, b)] * 2)).append('\n');
        }
        System.out.println(sb);
    }
    static class Node{
        int u, cost;
        public Node(int u, int cost) {
            this.u = u;
            this.cost = cost;
        }
    }
}
