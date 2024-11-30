import org.w3c.dom.Node;

import java.util.*;
import java.io.*;
public class HW_2352 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[] dp = new int[n];
        Arrays.fill(dp, 1); // 최소 1개 선 연결 가능

        for(int i=1; i<n; i++){ // LIS
            for(int j=0; j<i; j++){
                if(arr[i] > arr[j]){
                    dp[i] = Math.max(dp[i] , dp[j]+1);
                }
            }
        }

        int LIS = 0;
        for(int i=0; i<n; i++){
            LIS = Math.max(LIS, dp[i]);
        }
        System.out.println(LIS);
    }
}