import java.io.*;
import java.util.*;

public class HW_15831 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 조약돌 개수
        int B = Integer.parseInt(st.nextToken()); // 흑돌 최대 개수
        int M = Integer.parseInt(st.nextToken()); // 백돌 최소 개수

        String input = br.readLine();
        char[] stones = input.toCharArray();

        int s = 0, e=0;
        int bCnt = 0, wCnt = 0;
        int length = 0;

        while(e<N){
            if(stones[e]=='B') bCnt++;
            if(stones[e]=='W') wCnt++;

            while(bCnt>B){ // 흑돌 초과한 경우 구간 축소
                if(stones[s]=='B') bCnt--;
                if(stones[s]=='W') wCnt--;
                s++;
            }
            if(wCnt >=M){ // 백돌 최소 조건 만족 시
                length = Math.max(length, e-s+1);
            }
            e++;
        }
        System.out.println(length);
    }
}