import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SB_7579_2 {
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] mem = new int[N];
        int[] cost = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            mem[i] = Integer.parseInt(st.nextToken());
        }

        int mx = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
            mx += cost[i];
        }

        int[] dp = new int[mx + 1];      // dp[i] = k, i비용으로 얻을 수 있는 최대 메모리 k
        for (int i = 0; i < N; i++) {       // i번째 앱에 대해
            for (int j = mx; j >= cost[i]; j--) {   // 최대비용부터 i번째 앱 비활성으로 얻을 수 있는 비용까지 봤을때
                dp[j] = Math.max(dp[j], dp[j - cost[i]] + mem[i]);      // 얻을 수 있는 비용 j는 기존 비용 or 비활성화로 얻을 수 있는 비용 중 최대값
            }
        }

        int ans = 0;
        for (int i = 0; i <= mx; i++) {
            if (dp[i] >= M) {
                ans = i;
                break;
            }
        }

        System.out.println(ans);
    }
}
