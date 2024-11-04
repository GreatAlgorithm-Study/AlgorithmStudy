import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간복잡도 : N=20,
// 제한된 체력으로 최적의 기쁨을 얻는 문제 -> 배낭 -> 인사O or 인사 X -> 0-1 배낭 문제
public class HW_1535 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int K = 99;
        int[] weight = new int[N+1]; // 체력
        int[] value = new int[N+1]; // 기쁨

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            weight[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            value[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N+1][K+1];
        for(int i=1; i<=N; i++){
            for(int j=1; j<=K; j++){
                if(weight[i]<= j) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i-1][j - (weight[i])] + value[i]);
                }
                else dp[i][j] = dp[i-1][j]; // 인사하지 않는 경우
            }
        }
        System.out.println(dp[N][K]);
    }
}