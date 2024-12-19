import java.util.HashSet;

public class JW_4803 {

    static int[] parent;

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        while (true) {
            int n = read(), m = read();
            if (n == 0 && m == 0)
                break;
            parent = new int[n + 1];
            for (int i = 0; i < n + 1; i++)
                parent[i] = i;
            boolean[] isCycle = new boolean[n + 1]; // 해당 노드가 사이클이 발생했는지 확인할 배열
            for (int i = 0; i < m; i++) {
                int x = read(), y = read();
                int rootX = find(x), rootY = find(y);
                // 부모가 같을 경우엔 사이클이 발생
                if (rootX == rootY)
                    isCycle[rootX] = true;
                parent[rootX] = rootY;
                isCycle[rootY] |= isCycle[rootX]; // 사이클 전파
            }
            HashSet<Integer> roots = new HashSet<>();
            for (int i = 1; i < n + 1; i++) {
                int root = find(i);
                if (!isCycle[root])
                    roots.add(root);
            }
            sb.append("Case ").append(++idx).append(": ");
            // 모두 사이클이 발생했을 경우
            if (roots.size() == 0)
                sb.append("No trees.\n");
            // 1개의 루트가 존재
            else if (roots.size() == 1)
                sb.append("There is one tree.\n");
            // n개의 루트가 존재
            else
                sb.append("A forest of ").append(roots.size()).append(" trees.\n");
        }
        System.out.println(sb);
    }

    // Find 함수
    private static int find(int x) {
        if (x == parent[x])
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