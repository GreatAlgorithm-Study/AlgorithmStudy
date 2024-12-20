import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SB_4803 {
    static int[] parent;
    static boolean[] isCycle;
    static String cnt0 = "No trees.";
    static String cnt1 = "There is one tree.";

    private static int find(int x) {
        if (parent[x] != x) return find(parent[x]);
        return parent[x];
    }

    private static void union(int u, int v) {
        int pu = find(u);
        int pv = find(v);

        if (pu==pv) {                               // 부모가 같으면 사이클이 존재
            isCycle[pu] = true;
            return;
        }

        parent[pv] = pu;                            // 부모다르면 합치기
        isCycle[pu] = isCycle[pu] || isCycle[pv];   // 사이클 여부도 합치기
    }

    private static int cntTree(int N) {
        int cnt = 0;
        for (int i = 1; i < N + 1; i++) {
            if (parent[i]==i && !isCycle[i]) cnt++;     // 해당 그룹이 사이클 가지지 않으면 트리(루트 노드 기준)
        }
        return cnt;
    }
    private static void init(int N) {
        parent = new int[N + 1];
        isCycle = new boolean[N + 1];

        for (int i = 1; i < N + 1; i++) {
            parent[i] = i;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int idx = 1;
        while (true) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            if (N==0 && M==0) break;

            init(N);
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                union(u, v);
            }
            int cnt = cntTree(N);

            sb.append("Case ").append(idx).append(": ");
            if (cnt==0) sb.append(cnt0);
            else if (cnt==1) sb.append(cnt1);
            else sb.append("A forest of ").append(cnt).append(" trees.");
            sb.append('\n');
            idx++;
        }
        System.out.println(sb);
    }
}
