import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class HW_1967 {
    static int n;
    static ArrayList<Node> graph[];
    static boolean[] visited;
    static int max = 0;
    static int max_idx = 0;
    static class Node {
        int edge;
        int weight;

        Node(int edge, int weight) {
            this.edge = edge;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine()); // 노드의 개수
        graph = new ArrayList[n+1];

        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        // 트리 정보 입력받기
        for (int i = 0; i < n - 1; i++) {
            String[] t = br.readLine().split(" ");
            int parent = Integer.parseInt(t[0]);
            int child = Integer.parseInt(t[1]);
            int weight = Integer.parseInt(t[2]);
            graph[parent].add(new Node(child, weight));
            graph[child].add(new Node(parent, weight));
        }

        // 1. 첫 번째 DFS 수행 (임의의 노드에서 가장 먼 노드를 찾기)
        visited = new boolean[n + 1];
        visited[1] = true;
        dfs(1, 0); // 임의의 노드 1에서 출발, 현재 거리는 0

        // 2. 첫 번째 DFS에서 찾은 가장 먼 노드에서 다시 DFS 수행
        visited = new boolean[n + 1];
        visited[max_idx] = true;
        dfs(max_idx, 0); // 가장 먼 노드에서 출발

        // 3. 두 번째 DFS 결과가 트리의 지름
        System.out.println(max);
    }

    // DFS를 통해 가장 먼 노드를 찾기
    static void dfs(int edge, int weight) {
        if (max < weight) {
            max = weight;
            max_idx = edge;
        }
        for(Node a : graph[edge]){
            if(!visited[a.edge]){
                visited[a.edge] = true;
                dfs(a.edge, weight + a.weight);
            }
        }
    }
}