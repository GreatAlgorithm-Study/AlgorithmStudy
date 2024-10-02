import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SB_7570 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] child = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++){
            child[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N + 1];
        int mx = 0;
        for (int x : child) {       // 연속되는 부분증가 수열 찾기
            dp[x] = dp[x-1]+1;
            if (dp[x] > mx) mx = dp[x];
        }

        System.out.println(N-mx);
    }
}
