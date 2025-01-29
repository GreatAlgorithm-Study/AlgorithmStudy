import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class SB_9205_2 {
    static int N;
    static Node home, target;
    static Node[] stores;

    private static boolean bfs() {
        Deque<Node> que = new ArrayDeque<>();
        boolean[] visited = new boolean[N];
        que.add(home);

        while (!que.isEmpty()) {
            Node cur = que.poll();
            if (canGo(cur.x, cur.y, target.x, target.y)) return true;
            for (int i = 0; i < N; i++) {
                Node nxt = stores[i];
                if (visited[i] || !canGo(cur.x, cur.y, nxt.x, nxt.y)) continue;
                que.add(nxt);
                visited[i] = true;
            }
        }
        return false;
    }

    private static boolean canGo(int x, int y, int x2, int y2) {
        return Math.abs(x - x2) + Math.abs(y - y2) <= 1000;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            stores = new Node[N];
            for (int i = 0; i < N + 2; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                if (i==0) home = new Node(x, y);
                else if (i==N+1) target = new Node(x, y);
                else stores[i - 1] = new Node(x, y);
            }

            if (bfs()) sb.append("happy").append('\n');
            else sb.append("sad").append('\n');
        }
        System.out.println(sb);
    }
    static class Node{
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
