import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JW_1052 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int cnt = 0;
        // k개로 옮기지 못하는 경우
        // 2개씩 합쳐서 옮길 수 없는 경우 즉, 2^m의 조합의 수 > k
        while (Integer.bitCount(n) > k) {
            // 최소한의 수를 더하여 비트 줄이기
            cnt += Integer.lowestOneBit(n);
            n += Integer.lowestOneBit(n);
        }
        System.out.println(cnt);
    }
}