import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class HW_20002 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[][] dp = new int[N+1][N+1];
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + arr[i-1][j-1];
            }
        }
        int max = Integer.MIN_VALUE;

        for(int K=1; K<=N; K++){
            for(int x=1; x+K-1<=N; x++){
                for(int y=1; y+K-1<=N; y++){
                    int x2 = x+K-1;
                    int y2 = y+K-1;

                    int temp = dp[x2][y2] - dp[x-1][y2] - dp[x2][y-1] + dp[x-1][y-1];
                    if(temp > max){
                        max = temp;
                    }
                }
            }
        }
        System.out.println(max);
    }
}