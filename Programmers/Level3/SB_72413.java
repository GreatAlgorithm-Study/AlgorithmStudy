import java.util.*;

class SB_72413 {
    static ArrayList<ArrayList<Node>> adj = new ArrayList<>();
    static int INF = Integer.MAX_VALUE;

    private static int[] dijsktra(int n, int start) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.v > dist[cur.idx]) continue;

            for (Node nxt : adj.get(cur.idx)) {
                if (dist[nxt.idx] > dist[cur.idx] + nxt.v) {
                    dist[nxt.idx] = dist[cur.idx] + nxt.v;
                    pq.offer(new Node(nxt.idx, dist[nxt.idx]));
                }
            }
        }
        return dist;
    }
    public static int solution(int n, int s, int a, int b, int[][] fares) {
        for (int i = 0; i < n + 1; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] f : fares) {
            adj.get(f[0]).add(new Node(f[1], f[2]));
            adj.get(f[1]).add(new Node(f[0], f[2]));
        }

        int[] costS = dijsktra(n, s);
        int[] costA = dijsktra(n, a);
        int[] costB = dijsktra(n, b);

        int ans = INF;
        for (int i = 1; i <= n; i++) {
            ans = Math.min(ans, costS[i] + costA[i] + costB[i]);
        }
        return ans;
    }
}
class Node implements Comparable<Node>{
    int idx, v;

    public Node(int idx, int v) {
        this.idx = idx;
        this.v = v;
    }

    @Override
    public int compareTo(Node o) {
        return this.v - o.v;
    }
}