import java.io.*;
import java.util.*;

public class HW_19939 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int sum = K*(K+1)/2;

        if(N<sum){
            System.out.println(-1);
            return;
        }

        int extra = N - sum;

        int min = 1 + extra/K; // 공이 가장 적은 바구니
        int max = K + extra/K; // 공이 가장 많은 바구니

        if(extra %K>0){
            max++;
        }
        System.out.println(max-min);
    }
}