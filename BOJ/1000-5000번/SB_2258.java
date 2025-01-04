import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SB_2258 {
    static int N, M;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dp = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            dp[i][0] = Integer.parseInt(st.nextToken());    // 무게
            dp[i][1] = Integer.parseInt(st.nextToken());    // 가격
        }

        // 가격 오름차순, 무게 내림차순
        Arrays.sort(dp, (o1, o2) -> {
            if (o1[1] != o2[1]) return o1[1] - o2[1];
            return o2[0] - o1[0];      // 가격이 같을땐 무게 기준 내림차순
        });


        int total = 0;
        int price = 0;
        int mn = Integer.MAX_VALUE;
        boolean flag = false;

        for (int i = 0; i < N; i++) {
            total += dp[i][0];                          // 무게 계속 누적 됨

            if (i > 0 && dp[i-1][1] == dp[i][1])        // 같은 가격의 고기는 돈 지불
                price += dp[i][1];
            else price = dp[i][1];                      // 다른 가격이면, 이 가격으로 지금까지 고기 다 살 수 있음(가격 오름차순 정렬)

            if (total >= M){
                flag = true;
                mn = Math.min(mn, price);               // 동일한 가격이 누적됐을때, 새로운 값이 더 쌀 수 있음
            }
        }

        System.out.println(flag ? mn : -1);

    }
}
