import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 시간 복잡도 : T * N * M = O(N) 가능
// 주어진 금액을 만드는 모든 방법의 수
// dp로 푼 이유 : 각 동전이 주어졌을 때 금액을 만드는 다양한 방법을 모두 계산
public class Main {
    static int T, N, M;
    static int[] coins;
    static int[] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; t++){
            N = Integer.parseInt(br.readLine());
            coins = new int[N];

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++){
                coins[i] = Integer.parseInt(st.nextToken());
            }
            M = Integer.parseInt(br.readLine());
            dp = new int[M+1]; // M원을 만들 수 있는 경우의 수
            dp[0] = 1; // 0원 만드는 경우

            for(int i=0; i<N; i++){
                for(int j=coins[i]; j<M+1; j++){
                    dp[j] += dp[j - coins[i]];

            }
            System.out.println(dp[M]);
        }

    }
}