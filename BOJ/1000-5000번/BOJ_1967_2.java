import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import java.util.List;
import java.util.StringTokenizer;

class BOJ_1967_2{
    static List<Node>[] graph;
    static boolean[] visited;
    static int ans=0; // 트리의 지름
    static int farNode=0; // 가장 먼 노드
    static class Node{
        int e, weight;
        Node (int e, int weight){
            this.e = e;
            this.weight = weight;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine()); // 노드의 개수

        if(n==1){ // NullPointer, 노드가 1개일 때 트리 지름 0
            System.out.println(0);
            return;
        }

        graph = new ArrayList[n+1];
        visited = new boolean[n + 1];

        for(int i=1; i<=n; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i=0; i<n-1; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[s].add(new Node(e, weight)); // 양방향
            graph[e].add(new Node(s, weight));
        }
        dfs(1, 0); // 루트 노드에서 가장 먼 노드 찾기

        visited = new boolean[n + 1];
        visited[farNode] = true;
        dfs(farNode, 0);

        System.out.println(ans);
    }
    private static void dfs(int node, int dist){
        visited[node] = true;

        if(ans < dist){
            ans = dist;
            farNode = node;
        }

        // for(요소타입 변수명 : 배열 or 컬렉션)
        for(Node n : graph[node]){
            if(!visited[n.e]){
                dfs(n.e, dist + n.weight);
            }
        }
    }
}