import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
* 줄세우기
* 문제: 최소한의 자리이동으로 정렬
* 설계: N - 최장부분증가수열
* */
public class SB_2631 {
    static int N;
    static int[] line;
    static List<Integer> lst = new ArrayList<>();

    private static int findIdx(int x) {
        int left = 0;
        int right = lst.size()-1;

        while (left < right) {
            int mid = (left + right) / 2;
            if (lst.get(mid) < x) left = mid+1;
            else right = mid;
        }
        return left;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        line = new int[N];

        for (int i = 0; i < N; i++) {
            line[i] = Integer.parseInt(br.readLine());
        }

        // 이분탐색으로 최장부분증가수열 구하기
        lst.add(line[0]);
        for (int i=1; i<N; i++) {
            if (line[i] > lst.get(lst.size() - 1)) lst.add(line[i]);
            else {
                int idx = findIdx(line[i]);
                lst.set(idx, line[i]);
            }
        }
        System.out.println(N-lst.size());
    }
}
