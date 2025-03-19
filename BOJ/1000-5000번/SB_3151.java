import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SB_3151 {
    static int N;
    static int[] arr;

    private static int lowerBound(int start, int target) {
        int s = start;
        int e = N;
        while (s < e) {
            int mid = (s+e) / 2;
            if (arr[mid] < target) s= mid+1;
            else e = mid;
        }
        return s;
    }

    private static int upperBound(int start, int target) {
        int s = start;
        int e = N;
        while (s < e) {
            int mid = (s + e) / 2;
            if (arr[mid] <= target ) s=mid+1;
            else e = mid;
        }
        return s;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        long cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int left = lowerBound(j + 1, (arr[i] + arr[j]) * -1);
                int right = upperBound(j + 1, (arr[i] + arr[j]) * -1);
                cnt += right-left;
            }
        }
        System.out.println(cnt);
    }
}
