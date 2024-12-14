import java.io.*;
import java.util.*;

//투포인터+dp : 1<=N<=100,000 > O(N)
public class YJ_20181 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] data = br.readLine().split("\\s");
        int n = Integer.parseInt(data[0]);
        int k = Integer.parseInt(data[1]);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] branch = new int[n+1];
        for(int i=0; i<n; i++){
            branch[i] = Integer.parseInt(st.nextToken());
        }

        int start = 1;
        int end = 0;
        int satisfaction =branch[0];
        long[] dp = new long[n+1];  // dp[현재지점] = 최대 누적 탈피 에너지

        //k 보다 작으면 start를 움직이고, k 를 넘으면 end 를 움직이기 때문에 start 와 end 범위가 슬라이딩 윈도우 형태로 이동됨
        while(start < n+1){
            if(satisfaction < k){
                dp[start] = Math.max(dp[start],dp[start-1]);    //현재까지의 최대 에너지 갱신
                if(start < n+1){
                    satisfaction += branch[start++];    //k 보다 작으면 start 오른쪽으로 이동
                }
            }else{
                while(satisfaction >= k){
                    //기존 누적 최대에너지 vs 이전 상태로 돌아가서 현재 구간에 대한 에너지
                    //start 가 1부터 시작했기 때문에 end 가 투포인터 범위에 포함되지 않는 이전 상태가 됨
                    dp[start] = Math.max(dp[start], dp[end] + satisfaction - k);
                    satisfaction -= branch[end++];  //end 오른쪽으로 이동 시켜서 범위 좁히기
                }
            }
        }
        System.out.println(dp[n]);
    }
}