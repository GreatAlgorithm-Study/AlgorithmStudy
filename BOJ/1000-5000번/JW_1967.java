import java.util.ArrayList;

public class Main {

    static ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
    static boolean[] visited;
    static int maxNode;
    static int maxWeight;

    public static void main(String[] args) throws Exception {
        int n = read();
        for (int i = 0; i < n + 1; i++)
            graph.add(new ArrayList<>());
        for (int i = 0; i < n - 1; i++) {
            int u = read(), v = read(), w = read();
            graph.get(u).add(new int[] { v, w });
            graph.get(v).add(new int[] { u, w });
        }
        // 한 리프노드에서 다른 리프노드까지의 최대 가중치
        // 시작할 리프 노드를 선택
        visited = new boolean[n + 1];
        dfs(1, 0);
        // 선택된 리프 노드에서 다른 리프노드까지의 최대 가중치를 찾아서 반환
        visited = new boolean[n + 1];
        dfs(maxNode, 0);
        System.out.println(maxWeight);
    }

    // 노드가 가질 수 있는 최대 가중치 및 해당 노드의 인덱스를 구하는 함수
    private static void dfs(int node, int weight) {
        visited[node] = true;
        if (weight > maxWeight) {
            maxWeight = weight;
            maxNode = node;
        }
        for (int[] edge : graph.get(node)) {
            if (!visited[edge[0]]) {
                dfs(edge[0], weight + edge[1]);
            }
        }
    }

    // 빠른 입력을 위한 함수
    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48)
            n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13)
            System.in.read();
        return n;
    }
}