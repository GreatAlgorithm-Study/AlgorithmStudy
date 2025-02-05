import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class SB_118669 {
    static int[] info;
    static int[] intensity;
    static ArrayList<ArrayList<Node>> adj = new ArrayList<>();
    private static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 0; i < info.length; i++) {
            if (info[i] == 1) {                     // 입구일때
                pq.offer(new Node(i, 0));    // 우선순위큐에 넣고
                intensity[i] = 0;                   // 쉬는구간 0 초기화
            }
        }

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (intensity[cur.u] < cur.cost) continue;      // 이미 더 작은 값으로 방문했으면 패쓰
            for (Node nxt : adj.get(cur.u)) {
                if (intensity[nxt.u] > Math.max(intensity[cur.u], nxt.cost)) {      // 이동과정 중 최대값이 intensity값이 됨
                    intensity[nxt.u] = Math.max(intensity[cur.u], nxt.cost);
                    pq.offer(nxt);
                }
            }
        }

    }
    public static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        // 지점 정보 세팅
        info = new int[n + 1];
        intensity = new int[n + 1];
        for (int g : gates) {
            info[g] = 1;            // 출입구 1
        }
        for (int s : summits) {
            info[s] = 2;            // 산봉우리 2
        }
        Arrays.fill(intensity, Integer.MAX_VALUE);

        // adj만들기 (입구는 나가는 방향, 봉우리는 들어오는 방향, 쉼터는 양방향)
        for (int i = 0; i < n + 1; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] path : paths) {
            int u = path[0];
            int v = path[1];
            int c = path[2];
            if (info[u] == 1 || info[v] == 2) adj.get(u).add(new Node(v, c));       // 시작이 산봉우리거나 끝이 입구면 각자 들어오고 나가는거 저장안해도 됨
            else if (info[v] == 1 || info[u] == 2) adj.get(v).add(new Node(u, c));  // 시작이 입구거나 끝이 산봉우리면 각자 나가거나 들어오는거 저장안해도 됨
            else {                                                              // 쉼터는 양방향으로 저장
                adj.get(u).add(new Node(v, c));
                adj.get(v).add(new Node(u, c));
            }
        }

        dijkstra();

        Arrays.sort(summits);

        int mnP = 0;
        int mnC = Integer.MAX_VALUE;
        for (int s : summits) {                         // 구한 쉬는 구간 중 가작 짧은 값 구하기
            if (intensity[s] < mnC) {
                mnC = Math.min(mnC, intensity[s]);
                mnP = s;
            }
        }
        return new int[]{mnP, mnC};
    }

    static class Node implements Comparable<Node>{
        int u, cost;

        public Node(int u, int cost) {
            this.u = u;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost-o.cost;
        }
    }
}
