import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SB_2110_2 {
    static int N, C;
    static int[] arr;

    private static boolean canDo(int k) {
        int cnt = 1;
        int pre = arr[0];
        for (int i = 1; i < N; i++) {
            if (arr[i]-pre >= k) {     // 현재와 이전 집 사이 거리가 K보다 크거나 같을때 공유기 설치
                pre = arr[i];
                cnt++;
            }
            if (cnt >= C) return true;
        }
        return false;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int left = 1;
        int right = arr[N - 1] - arr[0];

        int param = 0;
        while (left <= right) {
            int mid = (left + right) / 2;       // 공유기간 간격
            if (canDo(mid)) {                   // 해당 공유기 간격으로 C개 이상 설치할 수 있으면 거리 늘리기 
                left = mid+1;
                param = mid;
            }
            else right = mid-1;
        }

        System.out.println(param);
    }
}
