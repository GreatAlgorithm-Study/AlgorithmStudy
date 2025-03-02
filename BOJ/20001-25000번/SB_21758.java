import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_21758 {
    static int N;
    static int[] arr;
    static int[] sum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        sum = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if (i==0) sum[i] = arr[i];
            else sum[i] += arr[i]+sum[i-1];
        }

        int ans = 0;
        for (int i = 1; i < N - 1; i++) {
            ans = Math.max(ans, (sum[N - 1] - arr[0] - arr[i]) + (sum[N - 1] - sum[i]));    // 벌 벌 꿀
            ans = Math.max(ans, sum[i - 1] + (sum[N - 2] - arr[i]));                        // 꿀 벌 벌
            ans = Math.max(ans, (sum[i] - arr[0]) + (sum[N - 2] - sum[i - 1]));             // 벌 꿀 벌
        }
        System.out.println(ans);
    }
}
