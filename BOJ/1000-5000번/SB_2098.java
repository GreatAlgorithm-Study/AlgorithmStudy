import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SB_2098 {
    static int N;
    static int[][] W;
    static int[][] dp;
    static int FULLBIT;
    static int INF = Integer.MAX_VALUE >> 2;

    private static int dfs(int fm, int msk) {
        if (msk == FULLBIT) {                        // 다 돌았으면, 마지막 도시에서 처음(0)으로 가는 비용 반환
            if (W[fm][0]==0) return INF;             // 만약 처음으로 갈 수 없으면 INF 반환
            return W[fm][0];
        }
        if (dp[fm][msk]!=-1) return dp[fm][msk];                      // 이미 계산해둔 값이 있으면 해당 값 반환

        dp[fm][msk] = INF;                                            // dp[fm][msk] 값을 처음 계산할 시, -1값을 INF로 초기화

        for (int to = 0; to < N; to++) {                              // 다음 도시 to로 이동
            if ((msk & (1 << to)) != 0) continue;                     // 다음 도시 to를 이미 방문했으면 패쓰
            if (W[fm][to]==0) continue;                               // 길이 없는 도시면 패쓰
            dp[fm][msk] = Math.min(dp[fm][msk], dfs(to, msk | (1 << to)) + W[fm][to]);      // 다음 도시를 갈때의 최소값
        }

        return dp[fm][msk];         // 현재도시 fm에서 처음도시까지 갔을때 최소값 반환
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        FULLBIT = (1 << N) - 1;             // 모든 도시 N을 방문했을때의 값
        W = new int[N][N];
        dp = new int[N][FULLBIT];           // dp[i][msk] = k, 현재 i번째 도시이고, msk만큼의 도시를 방문한 상태일때, 방문하지 않은 도시를 방문해 처음 도시로 갈때까지의 최소값
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // dp배열 초기화
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(dfs(0, 1));      // 0번째 도시에서 시작, 0번째 도시만 방문

    }
}
