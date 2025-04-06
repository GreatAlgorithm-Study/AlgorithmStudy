import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SB_9694 {
    static ArrayList<ArrayList<Node>> adj;
    static int[] dist;
    static int[] path;
    static Integer INF = 987654321;

    private static int[] dijsktra(int M) {
        dist[0] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.cost > dist[cur.idx]) continue;

            for (Node nxt : adj.get(cur.idx)) {
                if (nxt.cost + dist[cur.idx] < dist[nxt.idx]) {
                    dist[nxt.idx] = nxt.cost + dist[cur.idx];
                    path[nxt.idx] = cur.idx;
                    pq.offer(new Node(nxt.idx, dist[nxt.idx]));
                }
            }
        }
        return path;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        int turn = 1;
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            dist = new int[M];
            Arrays.fill(dist, INF);
            path = new int[M];

            adj = new ArrayList<>();
            for (int i = 0; i < M; i++) {
                adj.add(new ArrayList<>());
            }

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                adj.get(u).add(new Node(v, c));
                adj.get(v).add(new Node(u, c));
            }

            int[] path = dijsktra(M);

            if (dist[M-1]==INF){
                sb.append("Case #").append(turn++).append(": ").append("-1\n");
            }else{
                List<Integer> ans = new ArrayList<>();
                int now = M-1;
                while (now != 0) {
                    ans.add(now);
                    now = path[now];
                }
                ans.add(0);
                Collections.reverse(ans);

                sb.append("Case #").append(turn++).append(": ");
                for (Integer i : ans) {
                    sb.append(i).append(" ");
                }
                sb.append('\n');
            }
        }

        System.out.println(sb);
    }

    static class Node implements Comparable<Node>{
        int idx, cost;

        Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}