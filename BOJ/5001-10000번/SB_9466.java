import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SB_9466 {
    static int[] choose;
    static boolean[] isFinish;
    static int[] visited;
    static int order = 0;
    static int cnt = 0;

    private static void dfs(int x) {
        visited[x] = ++order;           // 현재 지점 몇번째로 방문했는지 체크

        int nxt = choose[x];            // 현재 학생이 선택한 학생

        if (visited[nxt]==0) {          // 선택된 학생을 방문하지 않았으면 dfs진행
            dfs(nxt);
        } else if (!isFinish[nxt]) {    // 방문은 했는데 dfs가 끝나지 않았으면 사이클
            cnt += visited[x] - visited[nxt] + 1;
        }

        isFinish[x] = true;
    }
    private static void init(int n) {
        choose = new int[n + 1];
        isFinish = new boolean[n+1];
        visited = new int[n+1];
        order = 0;
        cnt = 0;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            init(N);
            st = new StringTokenizer(br.readLine());
            for (int v = 1; v < N+1; v++) {
                int u = Integer.parseInt(st.nextToken());
                choose[v] = u;
            }

            for (int i = 1; i < N + 1; i++) {
                if (!isFinish[i]) {         
                    dfs(i);
                }
            }
            sb.append(N - cnt).append('\n');
        }
        System.out.println(sb);
    }
}
