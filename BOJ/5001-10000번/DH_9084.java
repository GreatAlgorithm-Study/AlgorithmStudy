import java.io.*;
import java.util.*;

/*
 * 동전
 */

public class DH_9084 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine()); // 동전의 가지 수
            int[] coin = new int[N];

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < coin.length; i++) coin[i] = Integer.parseInt(st.nextToken());

            int M = Integer.parseInt(br.readLine());
            int[] dp = new int[M + 1]; // M원을 만들 수 있는 경우의 수 저장하는 배열

            dp[0] = 1; // 0원을 만드는 경우는 모든 동전을 사용하지 않는 경우이므로 한 가지임
            for(int c: coin) {
                for(int i = 0; i < dp.length; i++) {
                    if(i - c < 0) continue;
                    dp[i] += dp[i - c];
                }
            }

            sb.append(dp[M] + "\n");
        }

        System.out.println(sb);
    }
}
