import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class boj_2531_2 {
    static int N, d, k, c;
    static int[] table;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        table = new int[N];
        for (int i = 0; i < N; i++) {
            table[i] = Integer.parseInt(br.readLine());
        }

        // 첫 접시부터 k만큼 먹기
        HashMap<Integer, Integer> sushi = new HashMap<>();
        for (int i = 0; i < k; i++) {
            sushi.put(table[i], sushi.getOrDefault(table[i], 0) + 1);
        }
        sushi.put(c, sushi.getOrDefault(c, 0) + 1);     // 보너스 초밥 추가

        int start = 0;
        int end = k;    // k-1에서 한칸 움직인 값으로 초기화
        int mx = sushi.size();
        while (start < N) {                     // 원형테이블로 st가 N까지 올때까지 탐색
            int left = table[start];
            int right = table[end % N];         // 원형 테이블

            sushi.put(left, sushi.getOrDefault(left, 0) - 1);
            if (sushi.get(left)==0) sushi.remove(left);
            sushi.put(right, sushi.getOrDefault(right, 0) + 1);

            mx = Math.max(mx, sushi.size());
            start++;
            end++;
        }

        System.out.println(mx);
    }
}
