import java.util.*;

class Solution {
    
    static int N;
    static List<Node>[] g;
    static Set<Integer> gSet;
    static Set<Integer> sSet;
    static PriorityQueue<int[]> pq;
    static class Node {
        int edge, cost;
        public Node(int edge, int cost) {
            this.edge = edge;
            this.cost = cost;
        }
    }
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        N = n;
        g = new ArrayList[N+1];
        for(int i=1; i<N+1; i++) {
            g[i] = new ArrayList<>();
        }
        
        for(int i=0; i<paths.length; i++) {
            int[] p = paths[i];
            g[p[0]].add(new Node(p[1], p[2]));
            g[p[1]].add(new Node(p[0], p[2]));
        }
        
        gSet = new HashSet<>();
        for(int gate: gates) {
            gSet.add(gate);
        }
        sSet = new HashSet<>();
        for(int summit: summits) {
            sSet.add(summit);
        }
        
        pq = new PriorityQueue<>((o1, o2) -> o1[1]==o2[1] ? o1[0]-o2[0] :o1[1]-o2[1]);
        
        // 산봉우리를 시작점으로 bfs
        for(int summit: summits) {
            bfs(summit);
        }
        
        int[] answer = pq.poll();
        
        return answer;
    }
    public static void bfs(int start) {
        // 큐를 우선순위 큐로 설정하여, 비용이 작은 순부터 우선 탐색하도록 함
        PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2)-> o1[0]-o2[0]);
        boolean[] visited = new boolean[N+1];
        
        // {비용, 노드}
        q.add(new int[] {0, start});
        visited[start] = true;
        
        int intensity = Integer.MAX_VALUE;
        while(!q.isEmpty()) {
            int[] now = q.poll();
            
            // 출입구를 만남
            if(gSet.contains(now[1])) {
                pq.add(new int[] {start, now[0]});
                break;
            }
            
            // 등산로 반복
            for(Node next: g[now[1]]) {
                if(sSet.contains(next.edge)) continue;
                if(visited[next.edge]) continue;
                
                visited[now[1]] = true;
                int max = Math.max(now[0], next.cost);
                q.add(new int[] {max, next.edge});
            }
        }
    }
}