import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간복잡도 : D <=100_000, P<=350 O(D * P) -> O(N^2)
// D길이가 커서 배열 1차원으로 해야할듯
// 가능한 최대 수도관 용량을 구하는 프로그램
public class HW_2073 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int D = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        int[] dp = new int[D+1];
        dp[0] = Integer.MAX_VALUE;

        for(int i=1; i<=P; i++){
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            for(int j = D; j>= L; j--){
                dp[j] = Math.max(dp[j], Math.min(C, dp[j-L]));
            }
        }
        System.out.println(dp[D]);
    }
}