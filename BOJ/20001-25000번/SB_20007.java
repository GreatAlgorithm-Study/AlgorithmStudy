import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SB_20007 {
    static int N, M, X, Y;      // 집 수, 도로 수, 하루 걸을 거리, 성현이 집
    static List<List<Node>> adj = new ArrayList<>();
    static long[] dist;
    static Long INF = Long.MAX_VALUE;

    private static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.cost > dist[cur.idx]) continue;
            for (Node nxt : adj.get(cur.idx)) {
                if (nxt.cost + dist[cur.idx] < dist[nxt.idx]) {
                    dist[nxt.idx] = nxt.cost + dist[cur.idx];
                    pq.offer(new Node(nxt.idx, dist[nxt.idx]));
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adj.get(a).add(new Node(b, c));
            adj.get(b).add(new Node(a, c));
        }

        dist = new long[N];
        Arrays.fill(dist, INF);
        dijkstra(Y);

        Arrays.sort(dist);

        long tmp = 0;
        int cnt = 0;
        for (int i = 0; i < N; i++) {                           // 왕복 거리로 업데이트
            if (dist[i]==INF || dist[i]*2 > X){                 // 왕복거리가 X보다 크면 아예 못감
                System.out.println(-1);
                return;
            }
            if (tmp + dist[i] * 2 > X) {
                cnt++;
                tmp = dist[i] * 2;      // 현재 집으로 누적 거리 초기화
            } else {
                tmp += dist[i] * 2;     // 누적 거리 증가
            }
        }
        if (tmp > 0) cnt++;             // 마지막 집 배달 확인

        System.out.println(cnt);
    }


    static class Node implements Comparable<Node>{
        int idx;
        long cost;
        public Node(int idx, long cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.cost, o.cost);
        }
    }
}
