import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SB_2805_2 {
    static int N, M;
    static int[] tree;

    private static boolean canGet(int idx, int h) {
        long cnt = 0;
        for (int i = idx; i < N; i++) {
            cnt += tree[i] - h;
        }
        return cnt >= M;
    }

    private static int findIdx(int h) {
        int left = 0;
        int right = N;
        while (left < right) {
            int mid = (left + right) / 2;
            if (tree[mid] <= h) left = mid+1;
            else right = mid;
        }
        return left;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        tree = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            tree[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(tree);

        int left = 0;
        int right = tree[N - 1];

        while (left <= right) {
            int height = (left+right) / 2;
            int idx = findIdx(height);
            if (canGet(idx, height)) {
                left = height+1;
            } else right = height - 1;
        }

        System.out.println(right);
    }
}
