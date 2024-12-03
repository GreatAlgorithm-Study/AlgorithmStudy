import java.util.ArrayList;

public class JW_2533 {

    static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        int n = read();
        dp = new int[n + 1][2];
        for (int i = 0; i < n + 1; i++)
            tree.add(new ArrayList<>());
        for (int i = 0; i < n - 1; i++) {
            int u = read(), v = read();
            tree.get(u).add(v);
            tree.get(v).add(u);
        }
        recursive(0, 1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    private static void recursive(int parent, int u) {
        dp[u][0] = 0;
        dp[u][1] = 1;
        for (int v : tree.get(u)) {
            if (v == parent)
                continue;
            recursive(u, v);
            dp[u][0] += dp[v][1];
            dp[u][1] += Math.min(dp[v][0], dp[v][1]);
        }
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