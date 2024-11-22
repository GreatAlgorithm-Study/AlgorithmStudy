import java.io.*;
import java.util.*;

//2 <= N <= 10^5
//누적합
public class YJ_19939 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] dp = new int[K+1];
        for(int i=1; i<K+1; i++){
            dp[i] = dp[i-1] + i;
        }

        int minCount = dp[K];
        if(N < minCount){
            System.out.println(-1);
        }else{
            int result = (N-minCount) % K;
            if(result == 0){
                System.out.println(K-1);
            }else{
                System.out.println(K);   //K+result - (result*1)
            }
        }
    }
}