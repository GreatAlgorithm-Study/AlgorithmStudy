import java.io.*;
import java.util.*;

/*
줄세우기
 */

public class DH_7570 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];
        int max = 0;

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
//            dp[num] = dp[num - 1] != 0 ? dp[num - 1] + 1 : 1;
            dp[num] = dp[num - 1] + 1;
            max = Math.max(dp[num], max);
        }

        System.out.println(N - max);

    }
}
