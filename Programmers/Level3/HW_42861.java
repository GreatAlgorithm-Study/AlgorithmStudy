// 시간 복잡도 : v=100, O(E logV)
// 최소 비용으로 모든 섬이 서로 통행 가능하도록 만들 때 필요한 최소 비용 구하는 문제
// 다리를 여러 번 건너더라도, 도달할 수만 있으면 통행 가능 -> 모든 섬을 하나의 연결된 그래프로 만들기
// 프림 알고리즘
import java.util.*;

class Solution {
    public class Point implements Comparable<Point>{
        int node, cost;

        public Point(int node, int cost){
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Point o){
            return this.cost - o.cost; // 비용을 오름차순 정렬
        }
    }

    public List<List<Point>> map = new ArrayList<>();

    public int solution(int n, int[][] costs) {
        int answer = 0;
        for(int i=0; i<n; i++){
            map.add(new ArrayList<>());
        }
        for(int i=0; i<costs.length; i++){
            int from = costs[i][0];
            int to = costs[i][1];
            int val = costs[i][2];
            map.get(from).add(new Point(to, val));
            map.get(to).add(new Point(from, val));
        }

        boolean[] visited = new boolean[n];
        PriorityQueue<Point> queue = new PriorityQueue<>();
        queue.add(new Point(0, 0));

        while(!queue.isEmpty()){ // 최소 비용 간선 선택
            Point cur = queue.poll();

            if(visited[cur. node])
                continue;
            visited[cur.node] = true;
            answer +=cur.cost;

            for(int i=0; i<map.get(cur.node).size(); i++){ // 모든 인접 노드 확인
                int next = map.get(cur.node).get(i).node;
                int cost = map.get(cur.node).get(i).cost;
                if(visited[next]) continue;
                queue.add(new Point(next, cost));
            }
        }
        return answer;
    }
}