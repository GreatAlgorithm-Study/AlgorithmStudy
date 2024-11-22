import java.io.*;
import java.util.*;

// 시간복잡도 : Integer.bitCount O(log N) * K 물병 개수
// 상점에서 사야하는 물병의 최솟값
public class HW_1052 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int ans = 0;

        while(true) {
            int cnt = Integer.bitCount(N); // 2^n 리터 단위로 병 합칠 수 있음

            if(cnt<=K) { // K개를 넘으면 멈춤
                break;
            }
            N++; // 물병 사기
            ans++; //
        }
        System.out.println(ans);
    }
}
