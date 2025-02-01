import java.util.*;

class Solution {
    
    static class Edge implements Comparable<Edge> {
        int v, cost;
        public Edge(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
        @Override
        public int compareTo(Edge other) {
            return this.cost - other.cost;
        }
        @Override
        public String toString() {
            return ">> v:"+this.v+" cost:"+this.cost;
        }
    }
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        // 그래프 만들기
        List<Edge>[] g = new ArrayList[n];
        for(int i=0; i<n; i++) {
            g[i] = new ArrayList<>();
        }
        for(int[] c: costs){
            int v1 = c[0];
            int v2 = c[1];
            g[v1].add(new Edge(v2, c[2]));
            g[v2].add(new Edge(v1, c[2]));
        }
        
        
        boolean[] visited = new boolean[n];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(0, 0));
        
        while(!pq.isEmpty()) {
            Edge now = pq.poll();
            
            if(visited[now.v]) continue;
            visited[now.v] = true;
            answer += now.cost;
            
            // now와 연결된 섬들 탐색
            for(Edge e: g[now.v]) {
                if(visited[e.v]) continue;
                pq.add(e);
            }
        }
        
        
        return answer;
    }
}