import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SB_1052 {
    static int N, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int bottle = 0;
        while (Integer.bitCount(N) > K) {           // 물병의 2제곱근 묶음(비트변환시 1의 개수)이 K가 될때까지 물병 추가
            int lowPos = N & -N;                    // 가장 1의 위치에 물병 추가
            N+=lowPos;                              // 물병 갯수 업데이트
            bottle += lowPos;                       // 추가한 물병만큼 더해주기
        }
        System.out.println(bottle);
    }
}
