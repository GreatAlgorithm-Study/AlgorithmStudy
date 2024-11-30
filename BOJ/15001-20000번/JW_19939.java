import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JW_19939 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        // 최소 간격으로 나눠줄 때 필요한 수
        int need = k * (k + 1) / 2;
        if (n < need) {
            System.out.println(-1);
            return;
        }
        // 각 바구니의 최솟값 최댓값 계산
        int min = n / need, max = min + k - 1;
        // 고르게 나눠주고 남아있는 양이 있는지 계산
        int remain = (n - need) % k;
        // 남은 양이 있다면 최댓값 + 1
        if (remain != 0) {
            max++;
        }
        System.out.println(max - min);
    }
}