import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SB_19939 {
    static int N, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 우선, 나눌 수 있으려면 (0~K-1)까지 더한값이 N-K값보다 커야함
        // 0부터 K-1까지 합 (K개의 그룹에 최소 한 개씩 공을 배분하는 데 필요한 최소 공 개수) : 등차수열의 합
        int need = K * (K - 1) / 2;

        if ((N-K) < need) {
            System.out.println(-1);
            return;
        }

        // 최소 1개씩 배분 후 남은 공 T
        int T = N - need;

        if (T%K==0) System.out.println(K - 1);      // 나눠떨어지면 0~K-1로 분배할 수 있고, 이때 최소 차는 (K-1)-0으로 K-1
        else System.out.println(K);                 // 나눠떨어지지 않으면, 분배에 1씩 추가하게되고, 최소와 최대차가 1늘어남
    }
}
