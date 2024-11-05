import java.util.PriorityQueue;

public class JW_1647 {

    // Union Find를 구현하기 위한 배열 선언
    static int[] parent;
    static int[] rank;

    public static void main(String[] args) throws Exception {
        int n = read(), m = read();
        // 우선순위 큐를 이용해서 가중치 순으로 입력을 받음
        PriorityQueue<int[]> edges = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
        for (int i = 0; i < m; i++) {
            int u = read(), v = read(), w = read();
            edges.add(new int[] { u, v, w });
        }
        // Union Find 초기화
        parent = new int[n + 1];
        rank = new int[n + 1];
        for (int i = 0; i < n + 1; i++)
            parent[i] = i;
        int mstWeight = 0;      // 최소 신장 트리의 가중치 합
        int maxWeight = 0;      // 가중치들 중 최댓값
        int cnt = 0;
        // 최소 신장 트리를 이루는 간선의 개수는 n - 1개면 충분
        while(cnt < n - 1) {
            int[] edge = edges.poll();      // 작은 가중치부터 꺼냄
            // 간선을 이을 수 있는 노드라면
            if (union(edge[0], edge[1])) {
                mstWeight += edge[2];       // 가중치 합
                maxWeight = edge[2];        // 작은 가중치부터 꺼냈으므로 기존 가중치보다 항상 큼
                cnt++;
            }
        }
        System.out.println(mstWeight - maxWeight);
    }

    // 서로소 집합의 root를 찾는 Find 함수
    private static int find(int v) {
        if (v != parent[v]) {
            // 경로 압축
            parent[v] = find(parent[v]);
        }
        return parent[v];
    }

    // Rank를 이용한 Union
    // Rank: 트리의 대략적인 높이
    // 높이가 작은 트리를 높이가 큰 트리 아래에 붙여 트리의 높이가 불필요하게 커지지 않도록 함
    // Rank가 작은 트리를 큰 트리에 붙임으로써, 트리의 깊이가 크게 증가하지 않으므로, Find 연산 최적화
    private static boolean union(int u, int v) {
        int rootU = find(u);
        int rootV = find(v);
        if (rootU != rootV) {
            if (rank[rootU] < rank[rootV]) {
                parent[rootU] = rootV;
            } else if (rank[rootV] < rank[rootU]) {
                parent[rootV] = rootU;
            } else {
                parent[rootV] = rootU;
                rank[rootU]++;
            }
            return true;
        }
        return false;
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