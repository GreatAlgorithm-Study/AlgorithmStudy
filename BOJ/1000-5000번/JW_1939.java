import java.util.PriorityQueue;

public class JW_1939 {

    static int[] parent, rank;

    public static void main(String[] args) throws Exception {
        int n = read(), m = read();
        parent = new int[n + 1];
        rank = new int[n + 1];
        for (int i = 0; i < n + 1; i++)
            parent[i] = i;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[2] - o1[2]);
        for (int i = 0; i < m; i++)
            pq.offer(new int[] { read(), read(), read() });
        int s = read(), e = read();
        int maxWeight = 0;
        while (find(s) != find(e)) {
            int[] edge = pq.poll();
            if (union(edge[0], edge[1]))
                maxWeight = edge[2];
        }
        System.out.println(maxWeight);
    }

    private static boolean union(int y, int x) {
        int rootX = find(x), rootY = find(y);
        if (rootX != rootY) {
            if (rank[rootX] < rank[rootY])
                parent[rootX] = rootY;
            else if (rank[rootX] > rank[rootY])
                parent[rootY] = parent[rootX];
            else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
            return true;
        }
        return false;
    }

    private static int find(int x) {
        if (parent[x] == x)
            return parent[x];
        return parent[x] = find(parent[x]);
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