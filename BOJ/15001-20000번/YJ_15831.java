import java.io.*;
import java.util.*;

public class YJ_15831 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        String[] road = br.readLine().split("");

        int result = 0;
        int bCount = 0;
        int wCount = 0;
        int start = 0;
        int end = 0;
        while(end < N){
            if(bCount > B){       //b 갯수를 초과한 경우 start 움직이기
                if("W".equals(road[start])){
                    --wCount;
                }else{
                    --bCount;
                }
                start++;
            } else {      //b 갯수보다 작거나 같은 경우 end 움직이기
                if("W".equals(road[end])){
                    wCount++;
                }else{
                    bCount++;
                }
                end++;
            }

            if(wCount >= W && bCount <= B) { //조건이 부합하는 경우 산책길 길이 갱신
                result = Math.max(result, end - start);
            }
        }

        System.out.println(result);
    }
}