import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SB_1535 {
    static int health = 100;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] hp = new int[N+1];
        int[] happy = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            hp[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            happy[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[health + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = health; j >= hp[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - hp[i]] + happy[i]);
            }
        }

        System.out.println(dp[health-1]);
    }
}
