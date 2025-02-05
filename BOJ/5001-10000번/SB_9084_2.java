import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SB_9084_2 {

    private static int cal(int[] arr, int M) {
        int[] dp = new int[M + 1];
        dp[0] = 1;

        for (int a : arr) {
            for (int i = a; i < M + 1; i++) {
                dp[i] += dp[i - a];
            }
        }
        return dp[M];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            int M = Integer.parseInt(br.readLine());
            sb.append(cal(arr, M)).append('\n');
        }
        System.out.println(sb);
    }
}
