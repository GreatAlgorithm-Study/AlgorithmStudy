import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class JW_1761 {

    static int n;
    static ArrayList<ArrayList<Node>> tree = new ArrayList<>();
    static int[] depth, dist;
    static int[] parent;

    static class Node {
        int v;
        int w;

        Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void main(String[] args) throws Exception {
        n = read();
        parent = new int[n + 1];
        depth = new int[n + 1];
        dist = new int[n + 1];
        for (int i = 0; i < n + 1; i++)
            tree.add(new ArrayList<>());
        for (int i = 0; i < n - 1; i++) {
            int u = read(), v = read(), w = read();
            tree.get(u).add(new Node(v, w));
            tree.get(v).add(new Node(u, w));
        }
        bfs();
        StringBuilder sb = new StringBuilder();
        int t = read();
        while (t-- > 0) {
            int a = read(), b = read();
            sb.append(LCA(a, b)).append("\n");
        }
        System.out.println(sb);
    }

    static void bfs() {
        boolean[] visited = new boolean[n + 1];
        Deque<Node> dq = new ArrayDeque<>();
        dq.offer(new Node(1, 0));
        visited[1] = true;
        while (!dq.isEmpty()) {
            Node cur = dq.poll();
            for (Node next : tree.get(cur.v)) {
                if (visited[next.v])
                    continue;
                parent[next.v] = cur.v;
                depth[next.v] = depth[cur.v] + 1;
                dist[next.v] = dist[cur.v] + next.w;
                visited[next.v] = true;
                dq.offer(next);
            }
        }
    }

    static long LCA(int a, int b) {
        long result = dist[a] + dist[b];
        int depthA = depth[a];
        int depthB = depth[b];

        if (depthB < depthA) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        int diff = Math.abs(depthA - depthB);
        for (int i = 0; i < diff; i++)
            b = parent[b];

        while (a != b) {
            a = parent[a];
            b = parent[b];
        }
        return result - 2 * dist[a];
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48)
            n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13)
            System.in.read();
        return n;
    }
}