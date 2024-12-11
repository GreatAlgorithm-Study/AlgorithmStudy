import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class HW_20181 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 먹이 개수
        int K = Integer.parseInt(st.nextToken()); // 최소 만족도
        int[] arr = new int[N];
        long[] dp = new long[N+1]; // dp[i] : i번쨰 먹이까지의 최대 탈피 에너지

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long sum = 0; // 만족도 합
        int left = 0;

        for(int right=1; right <=N; right++){
            sum += arr[right-1];

            while(K <= sum){
                dp[right] = Math.max(dp[right], dp[left] + sum - K); // 탈피 에너지 계산
                sum -= arr[left];
                left++; // 구간 축소
            }
            dp[right] = Math.max(dp[right], dp[right - 1]); // 최대 탈피 에너지값 출력
        }
        System.out.println(dp[N]);
    }
}