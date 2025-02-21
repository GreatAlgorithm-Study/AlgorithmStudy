import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class SB_1450 {
    static int N, C;
    static int[] weigth;
    static List<Long> sbLst = new ArrayList<>();
    static int ans = 0;

    private static int find(long x) {    // upper-bound
        int left = 0;
        int right = sbLst.size();

        while (left < right) {
            int mid = (left + right) / 2;
            if (sbLst.get(mid) <= x) left = mid + 1;
            else right = mid;
        }
        return right;
    }


    private static void findSubSum(int depth, long sm) {
        if (depth > N-1) {
            ans += find(C-sm);      // C-현재값 보다 작거나 같은 수 개수 찾기
            return;
        }

        findSubSum(depth + 1, sm);
        if (sm + weigth[depth] <= C) {
            findSubSum(depth + 1, sm + weigth[depth]);
        }
    }

    private static void subSum(int depth, long sm) {
        if (depth >= (N / 2)) {         // 첫번째 그룹은 N/2의 값까지 포함되어야 함
            sbLst.add(sm);
            return;
        }
        // 현재 원소 포함
        subSum(depth + 1, sm);

        // 현재 원소 미포함
        if (sm + weigth[depth] <= C) {
            subSum(depth + 1, sm + weigth[depth]);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        weigth = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            weigth[i] = Integer.parseInt(st.nextToken());
        }

        // 첫번째 그룹에서 만들 수 있는 모든 부분집합 합 만들기
        subSum(0, 0);
        Collections.sort(sbLst);

        // 두번째 그룹에서도 만들 수 있는 모든 부분집합 합을 만들고, 위에서 만든 리스트에 C-x했을때 작거나 같은 값 수 찾기(이분탐색)
        findSubSum(N / 2, 0);

        System.out.println(ans);
    }

}
