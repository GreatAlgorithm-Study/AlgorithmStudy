import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 알고리즘: DFS
 * 시간복잡도: 1 ≤ n ≤ 10,000
 * 아이디어:
 * 트리에 존재하는 모든 경로들 중에서 가장 긴 것의 길이 구하기
 * DFS 1번: 루트에서 가장 멀리 있는 노드 탐색 (루트는 항상 존재하기 때문에 루트에서 시작)
 * DFS 2번: 해당 노드에서 가장 먼 노드 탐색 > 가장 긴 길이 계산
 */

class Node {
    int node;
    int weight;

    public Node(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }
}

public class YJ_1967 {
    static List<Node>[] TREE = null;
    static boolean[] VISITED = null;
    static int FAR_NODE = 0;
    static int longLength = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        VISITED = new boolean[N+1];

        //List index 가 하나의 노드 단위
        TREE = new ArrayList[N+1];
        for(int i=1; i<N+1; i++){
            TREE[i] = new ArrayList<>();
        }

        //트리 생성
        for(int i=1; i<N; i++){
            String[] line = br.readLine().split("\\s");
            int parent = Integer.parseInt(line[0]);
            int child = Integer.parseInt(line[1]);
            int weight = Integer.parseInt(line[2]);

            TREE[parent].add(new Node(child,weight));   //★무방향 (자식 노드로 계속 깊게 탐색)
            TREE[child].add(new Node(parent,weight));   //★양방향 (자식 노드에서 부모노드를 타고 다시 위로 올라오면 탐색)
        }
        //루트부터 시작하므로 방문처리
        int root = 1;
        VISITED[root] = true;

        //1.루트에서 가장 멀리 있는 노드 탐색
        dfs(root,0);

        //2.해당 노드에서 가장 먼 노드 탐색
        VISITED = new boolean[N+1];
        VISITED[FAR_NODE] = true;
        dfs(FAR_NODE,0);

        System.out.println(longLength);
        br.close();
    }

    private static void dfs(int start, int sum){
        longLength = Math.max(sum,longLength);
        if(sum == longLength){
            FAR_NODE = start;
        }

        for(Node data : TREE[start]){
            int node = data.node;
            int weight = data.weight;

            if(!VISITED[node]){
                VISITED[node] = true;
                dfs(node,sum+weight);
                VISITED[node] = false;
            }
        }
    }

}