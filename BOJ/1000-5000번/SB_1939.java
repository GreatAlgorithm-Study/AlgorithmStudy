import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SB_1939 {
    static int N,M, start, end;
    static List<List<Node>> adj = new ArrayList<>();
    static int[] dist;

    private static int dijskra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, Integer.MAX_VALUE));       // 최소값(최소값 중 최대)을 찾는것이기 때문에 맥스값으로 시작
        dist[start] = Integer.MAX_VALUE;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.idx==end) return cur.c;     // 도착점 도달 시 최대 중량 반환

            if (cur.c < dist[cur.idx]) continue;
            for (Node nxt : adj.get(cur.idx)) {
                int weight = Math.min(cur.c, nxt.c);    // 현재까지 중량과 다음 간선의 중량 중 최소값
                if (dist[nxt.idx] < weight) {
                    dist[nxt.idx] = weight;
                    pq.offer(new Node(nxt.idx, weight));
                }
            }
        }
        return 0;       //  도달할 수 없는 경우
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dist = new int[N+1];
        Arrays.fill(dist, -1);
        for (int i = 0; i <= N; i++) {
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
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        System.out.println(dijskra());
    }

    private static class Node implements Comparable<Node>{
        int idx, c;

        public Node(int u, int c) {
            this.idx = u;
            this.c = c;
        }

        @Override
        public int compareTo(Node o) {
            return o.c - this.c;
        }
    }
}
