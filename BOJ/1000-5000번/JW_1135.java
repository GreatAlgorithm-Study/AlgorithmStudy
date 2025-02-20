import java.util.ArrayList;
import java.util.Collections;

public class JW_1135 {

    static int n;
    static int[] dp;
    static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        n = read();
        dp = new int[n];
        // 트리 구조 만들기
        for (int i = 0; i < n; i++) {
            tree.add(new ArrayList<>());
            int parent = read();
            // 루트 제외
            if (parent == -1)
                continue;
            tree.get(parent).add(i);
        }
        recursive(0);
        System.out.println(dp[0]);
    }

    // 재귀적으로 해당 노드가 걸리는 계산
    private static void recursive(int cur) {
        ArrayList<Integer> al = new ArrayList<>();
        for (int next : tree.get(cur)) {
            recursive(next);
            al.add(dp[next]); // 탐색한 노드가 가지는 값을 저장
        }
        // 그리디하게, 가장 오래 걸리는 노드를 먼저 탐색해줘야 함
        al.sort(Collections.reverseOrder());
        
        // 1초에 한 번씩 전파 가능
        // 최댓값이 결국 최솟값이 됨
        for (int i = 0; i < al.size(); i++)
            dp[cur] = Math.max(dp[cur], al.get(i) + i + 1);
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean m = n == 13;
        if (m)
            n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48)
            n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13)
            System.in.read();
        return m ? ~n + 1 : n;
    }
}
