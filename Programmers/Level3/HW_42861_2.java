// 섬을 통행 가능하도록 연결할 때 필요한 최소 비용
// 시간복잡도 : n<=100, O(N logN) MST 가능
// MST : 그래프에서 모든 노드를 연결할 때 사용된 에지들의 가중치 합을 최소로 하는 트리
import java.util.*;
class HW_42861_2 {
    static int[] parent;
    static class Edge implements Comparable<Edge>{
        int s, e, w;
        public Edge(int s, int e, int w){
            this.s = s;
            this.e = e;
            this.w = w;
        }
        @Override
        public int compareTo(Edge o){
            return Integer.compare(this.w, o.w);
        }
    }

    public int solution(int n, int[][] costs) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        // 우선순위큐에 간선 정보 저장
        for(int[] cost: costs){
            pq.add(new Edge(cost[0], cost[1], cost[2]));
        }
        // union-find
        parent = new int[n];
        for(int i=0; i<n; i++){
            parent[i] = i;
        }

        int answer = 0;
        int cnt = 0; // 추가된 간선 수
        // MST
        while(!pq.isEmpty() && cnt <n-1){ // 간선 n-1
            Edge edge = pq.poll(); // 최소 비용 간선 선택
            if(find(edge.s) != find(edge.e)){
                union(edge.s, edge.e);
                answer += edge.w;
                cnt++;
            }
        }
        return answer;
    }
    private static void union(int a, int b){
        a = find(a);
        b = find(b);
        if (a!=b){
            parent[b] = a;
        }
    }
    private static int find(int a){
        if(a==parent[a]){
            return a;
        }
        return parent[a] = find(parent[a]); // 경로 압축
    }
}