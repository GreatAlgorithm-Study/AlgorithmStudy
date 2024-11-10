import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SB_13164 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 옆사람과 키 차이 배열
        int[] dif = new int[N - 1];
        for (int i = 0; i < N - 1; i++) {
            dif[i] = arr[i + 1] - arr[i];
        }

        Arrays.sort(dif);
        int ans = 0;
        for (int i = 0; i < N - K; i++) {
            ans += dif[i];
        }
        System.out.println(ans);
    }
}
