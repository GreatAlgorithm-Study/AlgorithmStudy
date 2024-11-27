import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SB_2352 {
    static int N;
    static int[] arr;
    static List<Integer> ans = new ArrayList<>();

    private static int findIdx(int x) {
        int st = 0;
        int ed = ans.size();
        while (st < ed) {
            int mid = (st + ed) / 2;
            if (ans.get(mid) < x) st = mid+1;
            else ed = mid;
        }
        return st;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        ans.add(arr[0]);                                             // 첫번째 원소 넣기
        for (int i = 1; i < N; i++) {
            if (arr[i] > ans.get(ans.size()-1)) ans.add(arr[i]);     // 리스트의 마지막 원소보다 큰 값이면 바로 붙이기
            int idx = findIdx(arr[i]);                               // 작을 경우 해당 원소가 들어갈 위치 찾아서 값 변경
            ans.set(idx, arr[i]);
        }
        System.out.println(ans.size());
    }
}
