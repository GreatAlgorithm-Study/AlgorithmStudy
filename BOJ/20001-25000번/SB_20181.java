import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SB_20181 {
    static int N, K;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long[] dp = new long[N + 1];

        long happy = 0;
        int left = 0;
        for (int right = 0; right < N; right++) {
            happy += arr[right];

            while (happy >= K) {
                dp[right + 1] = Math.max(dp[right + 1], dp[left] + happy - K);      // dp[left]는 이전 구간의 최대값(이전 최종 right의 위치)
                happy -= arr[left++];
            }

            dp[right + 1] = Math.max(dp[right], dp[right + 1]);             // 현재까지의 최대값과 갱신된 최대값 비교
        }

        System.out.println(Arrays.toString(dp));
    }
}
