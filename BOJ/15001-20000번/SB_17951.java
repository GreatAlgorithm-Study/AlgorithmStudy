import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 최솟값의 최댓값 구하기
public class SB_17951 {
    static int N, K;
    static int[] arr;

    private static boolean canDiv(int target) {
        int cnt = 0;
        int sum = 0;
        for (int a : arr) {
            sum+=a;
            if (sum >= target) {
                cnt++;
                sum = 0;
            }
        }
        return cnt >= K;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N];
        int mx = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            mx += arr[i];
        }

        int l = 0;
        int h = mx;
        int ans = 0;
        while (l <= h) {
            int mid = (l + h) / 2;
            if (canDiv(mid)) {
                ans = mid;
                l = mid+1;
            }else {
                h = mid-1;
            }
        }
        System.out.println(ans);
    }

}
