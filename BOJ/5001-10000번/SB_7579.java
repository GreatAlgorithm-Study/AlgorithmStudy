import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SB_7579 {
    static int N, M;
    static int[] memo, cost;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        memo = new int[N];
        cost = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            memo[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        // 비활성화 비용 최소화로 M바이트 확보
        int[] dp = new int[10001];

        // dp[i] = j : i비용으로 얻을 수 있는 최대 메모리 j
        for (int i = 0; i <N; i++) {                // 앱 번호
            for (int j = 10000; j >=cost[i]; j--) {         // 비용
                dp[j] = Math.max(dp[j], dp[j - cost[i]] + memo[i]);
            }
        }

        int ans = 0;
        for (int i = 0; i <= 10000; i++) {
            if (dp[i] >=M) {
                ans = i;
                break;
            }
        }
        System.out.println(ans);
    }
}
