import java.io.*;
import java.util.*;

public class YJ_1052 {
    //반례 10 3?
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        //K-1 개 만큼 물 합치기
        for(int i=0; i<K-1; i++){
            int count = 0;
            while(Math.pow(2,count) < N){  //N개의 물병을 넘지 않는 최대 제곱
                count++;
            }
            N -= (int) Math.pow(2,count-1);   //해당 제곱수 만큼 반복하면 1개로 만들 수 있음
            if(N == 0) {
                break;
            }
        }

        if(N == 0){
            System.out.println(-1);
        }else{
            //마지막 1개는 남은 양을 하나로 만들기
            int count = 0;
            while(Math.pow(2,count) < N){
                count++;
            }
            System.out.println((int) Math.pow(2,count) - N);
        }
    }
}