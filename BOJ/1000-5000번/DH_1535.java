import java.io.*;
import java.util.*;

/*
 * 안녕
 */

public class DH_1535 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] W = new int[N + 1];
        int[] V = new int[N + 1];
        int[] dp = new int[101];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i < W.length; i++) W[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i < V.length; i++) V[i] = Integer.parseInt(st.nextToken());


        for(int i = 1; i < N + 1; i++) {
            for(int k = 100; k > W[i]; k--) {
                dp[k] = Math.max(dp[k], dp[k - W[i]] + V[i]);
            }
        }

        System.out.println(dp[100]);
    }
}
