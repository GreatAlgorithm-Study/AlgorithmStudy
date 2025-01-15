import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SB_3020 {
    static int N, H, n;

    private static int binarySearch(int x, int[] arr) {
        int st = 0;
        int ed = n;

        while (st < ed) {
            int mid = (st+ed)/2;
            if (arr[mid] < x) st = mid+1;
            else ed = mid;
        }
        return st;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        n = N/2;

        int[] up = new int[n];
        int[] down = new int[n];
        for (int i = 0; i < N; i++) {
            if (i%2==0) down[i/2] = Integer.parseInt(br.readLine());
            else up[(i-1)/2] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(up);
        Arrays.sort(down);

        int mn = Integer.MAX_VALUE;
        int cnt = 0;
        for (int i = 1; i < H+1; i++) {
            int tmp = 0;
            tmp += n-binarySearch(i, down);
            tmp += n-binarySearch(H - i+1, up);
            if (tmp < mn) {
                mn = tmp;
                cnt = 1;
            }else if(mn==tmp) cnt++;
        }
        sb.append(mn).append(" ").append(cnt);
        System.out.println(sb);
    }
}
