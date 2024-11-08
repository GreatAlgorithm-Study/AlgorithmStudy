import java.io.*;
import java.util.*;

public class YJ_13164 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] data = br.readLine().split("\\s");
        int N = Integer.parseInt(data[0]);
        int K = Integer.parseInt(data[1]);
        int[] children = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++){
            children[i] = Integer.parseInt(st.nextToken());
        }

        int[] costs = new int[N-1];
        for(int i=1; i < children.length; i++){
            costs[i-1] = children[i] - children[i-1];
        }
        Arrays.sort(costs);

        //가장 대소차이가 심한 그룹의 경우 제외하기
        int total = 0;
        for(int i=0; i<N-K; i++){
            total +=  costs[i];
        }
        System.out.println(total);
    }
}