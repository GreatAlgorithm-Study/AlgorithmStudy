import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SB_2660 {
    static int N;
    static List<List<Integer>> adj = new ArrayList<>();

    private static int bfs(int idx) {               // 친구 점수를 찾기 위한 bfs
        Queue<Integer> que = new ArrayDeque<>();
        int[] visited = new int[N + 1];             // idx에서 각 회원 별로 떨어진 거리 구함(=친구 점수)
        Arrays.fill(visited, -1);

        que.add(idx);
        visited[idx] = 0;

        int score = 0;                              // 가장 먼 거리의 친구가 최종 친구 점수
        while (!que.isEmpty()) {
            int cur = que.poll();
            for (int nxt : adj.get(cur)) {
                if (visited[nxt]!=-1) continue;
                que.add(nxt);
                visited[nxt] = visited[cur]+1;
                score = Math.max(score, visited[nxt]);
            }
        }
        return score;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i <= N; i++) {
            adj.add(new ArrayList<>());
        }

        while (true) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            if (u==-1 && v==-1) break;
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        int[] ans = new int[N + 1];         // 각 회원들의 친구 점수 저장 배열
        int mn = Integer.MAX_VALUE;         // 회장 점수
        for (int i = 1; i <= N; i++) {
            ans[i] = bfs(i);
            mn = Math.min(mn, ans[i]);
        }

        // 결과 출력
        int cnt = 0;                        // 회장 후보 수
        for (int i = 1; i <= N; i++) {
            if (ans[i]!=mn) continue;
            cnt++;
            sb2.append(i).append(" ");
        }
        sb.append(mn).append(" ").append(cnt).append('\n');
        sb.append(sb2);
        System.out.println(sb);
    }
}
