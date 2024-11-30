import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SB_2533 {
    static int N;
    static List<List<Integer>> adj = new ArrayList<>();
    static boolean[] visited;
    static int[][] dp;

    private static void setAdaptor(int x) {
        visited[x] = true;                      // 현재 값 방문 처리
        dp[x][1] = 1;                           // 자신의 얼리어답터 수 1 설정 (자신의 dp에서 얼리어답터일때 값 초기화)

        for (int nxt : adj.get(x)) {            // 일단 리프노드까지 자식들 탐색
            if (visited[nxt]) continue;
            setAdaptor(nxt);                    // 자식들을 탐색하는 과정에서 자식들의 얼리어답터 값 1로 세팅 됨

            // x(자신)의 dp비용은 자식들에 의해서 정해짐 (딸린 자식들의 경우들에 따라 정해짐)
            dp[x][0] += dp[nxt][1];             // 내가 얼리어답터가 아니려면 자식이 어답터여 함
            dp[x][1] += Math.min(dp[nxt][1], dp[nxt][0]);       // 내가 얼리어답터면, 자식이 뭐든 상관없기에 최소값 사용
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1][2];
        visited = new boolean[N + 1];

        for (int i = 0; i < N + 1; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj.get(u).add(v);
            adj.get(v).add(u);          // 주어지는 입력이 부모, 자식 관계가 확실히 명시되어있지 않기에 양방향으로 추가
        }

        // 1 초기값 세팅
        dp[1][0] = 0;
        dp[1][1] = 1;
        setAdaptor(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

}
