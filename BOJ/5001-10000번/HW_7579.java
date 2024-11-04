import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 시간 복잡도 : O(N), 메모리를 활성화 or 비활성화 2^100 -> 완전탐색 불가
// 비활성 메모리 추가 비용을 최소화 하는 문제
// 메모리 나눌 수 없음 -> 완전 배낭 문제 -> dp

public class HW_7579 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int sum = 0;
        int[] memory = new int[N];
        int[] cost = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N;i++){
            memory[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N;i++){
            cost[i] = Integer.parseInt(st.nextToken());
            sum += cost[i];
        }

        int[] dp = new int[sum+1]; // 비용으로 확보할 수 있는 최대 메모리, sum 최대 = (100 * 100) = 10000
        for(int i=0; i<N; i++){
            for(int j=sum; j>=cost[i]; j--){
                dp[j] = Math.max(dp[j], dp[j-cost[i]] + memory[i]);
            }
        }

        for(int i=0; i<=sum; i++){
            if(dp[i]>=M){
                System.out.println(i);
                break;
            }
        }
    }
}