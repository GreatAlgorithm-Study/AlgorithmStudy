import java.io.*;
import java.util.*;

// 시간복잡도 : O(V+E)
public class HW_4803 {
    static List<List<Integer>> graph;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test = 1;

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            if(N == 0 && M == 0) {
                break;
            }
            graph = new ArrayList<>();
            for(int i=0; i<=N; i++) {
                graph.add(new ArrayList<>());
            }

            for(int i=0; i<M; i++) { // 간선 입력 받기
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                graph.get(u).add(v); // 양방향
                graph.get(v).add(u);
            }
            visited = new boolean[N+1];
            int cnt = 0;

            for(int i=1; i<=N; i++) {
                if(!visited[i]) {
                    if(isCheck(i, -1)) {
                        cnt++;
                    }
                }
            }

            if(cnt==0) {
                System.out.printf("Case %d: No trees.", test);
            }
            else if(cnt==1) {
                System.out.printf("Case %d: There is one tree.", test);
            }
            else {
                System.out.printf("Case %d: A forest of %d trees.", test, cnt);
            }
            System.out.println();
            test++;
        }
    }
    static boolean isCheck(int node, int parent) { // BFS를 통해 트리 연결 부분 확인
        Queue<int[]> queue = new LinkedList<>(); // 큐 초기화
        queue.add(new int[] {node, parent}); // {탐색 시작할 정점, node의 부모 정점}
        visited[node] = true;
        int nodeCnt = 0; // 연결 요소에서 방문한 정점 개수 cnt
        int edgeCnt = 0; // 연결 요소에서 방문한 간선 개수 cnt

        while(!queue.isEmpty()) {
            int[] cur = queue.poll(); // 큐에서 현재 노드, 부모 노드를 가져옴
            int curNode = cur[0]; // 현재 노드
            int curParent = cur[1]; // 부모 노드

            nodeCnt++; // 정점 개수++

            for(int i=0; i<graph.get(curNode).size(); i++) { // 인접 노드 탐색
                int neighbor = graph.get(curNode).get(i);
                edgeCnt++; // 간선 cnt++

                if(!visited[neighbor]) { // 방문하지 않은 노드를 큐에 추가하고
                    visited[neighbor] = true; // 방문 표시
                    queue.add(new int[] {neighbor, curNode});
                } else if(neighbor != curParent) { // 사이클 발생(이미 방문한 노드가 부모 노드가 아니라면)
                    return false; // 트리X
                }
            }
        }
        return edgeCnt/2 == nodeCnt -1; // 양방향 간선 고려 endge/2, 트리의 간선 개수 조건 V-1
    }
}
