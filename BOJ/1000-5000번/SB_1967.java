import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SB_1967 {
    static int N;
    static ArrayList<Node>[] tree;
    static boolean[] visited;
    static int tmpNode;
    static int mx = -1;

    private static void dfs(int cur, int dist) {
        if (dist > mx){
            mx = dist;
            tmpNode = cur;
        }

        visited[cur] = true;

        for (int i = 0; i < tree[cur].size(); i++) {        // 현재 노드의 자식들 돌며
            Node nxt = tree[cur].get(i);
            if (visited[nxt.idx]) continue;
            dfs(nxt.idx, nxt.w + dist);
            visited[nxt.idx] = true;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        tree = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            tree[p].add(new Node(c, w));
            tree[c].add(new Node(p, w));
        }

        visited = new boolean[N + 1];
        dfs(1, 0);

        visited = new boolean[N + 1];
        dfs(tmpNode, 0);

        System.out.println(mx);
    }

    private static class Node{
        int idx, w;

        Node(int idx, int w) {
            this.idx = idx;
            this.w = w;
        }
    }
}
