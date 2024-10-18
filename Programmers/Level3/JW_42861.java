import java.util.ArrayList;
import java.util.PriorityQueue;
class Solution {
    
    // 간선의 정보를 저장할 오브젝트
    class Edge {
        int to, weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public int solution(int n, int[][] costs) {
        int answer = 0;
        ArrayList<ArrayList<Edge>> edges = new ArrayList<>();
        for (int i = 0; i < n; i++)
            edges.add(new ArrayList<>());
        // 주어진 간선의 정보를 가공하여 저장
        for (int[] cost : costs) {
            edges.get(cost[0]).add(new Edge(cost[1], cost[2]));
            edges.get(cost[1]).add(new Edge(cost[0], cost[2]));
        }
        // MST - Prim Algorithm
        // 간선 비용을 기준으로 오름차순
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
        boolean[] visited = new boolean[n];
        pq.offer(new Edge(0, 0));
        int cnt = 0; // MST를 구성하는 간선은 n-1개면 충분
        while (!pq.isEmpty() && cnt < n) {
            Edge cur = pq.poll();
            if (visited[cur.to])
                continue;
            visited[cur.to] = true;
            cnt++;
            answer += cur.weight;
            // 방문하지 않은 노드로가는 간선 추가
            for (Edge edge : edges.get(cur.to)) {
                if (!visited[edge.to])
                    pq.offer(edge);
            }
        }
        return answer;
    }
}