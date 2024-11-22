import java.io.*;
import java.util.*;

// 시간 복잡도 : O(N + M logN)
public class HW_1761 {
    static int N, M, maxDepth;
    static List<int[]>[] tree;
    static int[] depth; // 깊이 저장
    static int[][] parent; // 부모 노드 저장
    static int[] dist; // 거리 저장
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        maxDepth = (int) (Math.log(N) / Math.log(2)) + 1;

        // 트리 초기화
        tree = new ArrayList[N+1];
        for(int i=1; i<=N; i++) {
            tree[i] = new ArrayList<>();
        }

        // 간선 정보 입력
        for(int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken()); // 거리
            tree[u].add(new int[] {v, w});
            tree[v].add(new int[] {u, w});
        }

        depth = new int[N+1];
        dist = new int[N+1];
        parent = new int[N+1][maxDepth];
        Arrays.fill(depth, -1);

        // DFS
        depth[1] = 0;
        dfs(1, 0);

        // 부모 테이블
        for(int d=1; d<maxDepth; d++) {
            for(int i=1; i<=N; i++) {
                if(parent[i][d-1] != 0) { // 이전 단계에 부모가 존재할 경우
                    parent[i][d] = parent[parent[i][d-1]][d-1];
                }
            }
        }

        M = Integer.parseInt(br.readLine());

        // 공통 조상 구하기
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int lca = LCA(u, v);
            int distance = dist[u] + dist[v] - 2 * dist[lca];

            System.out.println(distance);
        }
    }
    static void dfs(int node, int d) {
        for(int[] edge: tree[node]) {
            int next = edge[0];
            int weight = edge[1];

            if(depth[next] == -1) { // 방문하지 않은 노드라면
                depth[next] = depth[node] + 1; // 깊이 설정
                dist[next] = dist[node] + weight; // 루트에서 거리 계산
                parent[next][0] = node; // 바로 위 부모 노드 설정
                dfs(next, d+1); // 다음 노드 탐색
            }
        }
    }

    static int LCA(int u, int v) {
        if(depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        // 깊이를 맞추기
        for(int i=maxDepth-1; i>=0; i--) {
            if(depth[u] - (1<<i) >= depth[v]) {
                u = parent[u][i];
            }
        }

        // 공통 조상 찾기
        if(u==v)
            return u;

        // 같은 조상이 나올 때까지 한 칸씩 올리기
        for(int i=maxDepth-1; i>=0; i--) {
            if(parent[u][i] != parent[v][i]){
                u = parent[u][i];
                v = parent[v][i];
            }
        }
        return parent[u][0];
    }
}
