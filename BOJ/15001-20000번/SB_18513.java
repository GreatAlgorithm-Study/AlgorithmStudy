import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.StringTokenizer;

public class SB_18513 {
    static int N, K;
    static Deque<Node> que = new ArrayDeque<>();
    static HashSet<Integer> set = new HashSet<>();
    static int[] dir = new int[]{-1, 1};

    private static long bfs() {
        long cnt = 0;

        int check = 0;
        while (!que.isEmpty()) {
            Node cur = que.poll();

            for (int d : dir) {
                int nxt = cur.i + d;
                if (set.contains(nxt)) continue;
                cnt+= cur.v+1;
                set.add(nxt);
                que.offer(new Node(nxt, cur.v + 1));
                check++;

                if (check==K) return cnt;
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int idx = Integer.parseInt(st.nextToken());
            que.offer(new Node(idx, 0));
            set.add(idx);
        }

        System.out.println(bfs());
    }
}

class Node {
    int i, v;
    public Node(int i, int v) {
        this.i = i;
        this.v = v;
    }
}
