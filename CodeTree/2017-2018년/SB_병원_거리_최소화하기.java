import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SB_병원_거리_최소화하기 {
    static int N, M;
    static int[][] board;
    static List<Node> people = new ArrayList<>();
    static List<Node> hospital = new ArrayList<>();
    static List<List<Integer>> combi = new ArrayList<>();
    static int ans = Integer.MAX_VALUE;

    private static void dfs(int start, List<Integer> tmp) {
        if (tmp.size() == M) {
            combi.add(new ArrayList<>(tmp));
            return;
        }

        for (int i = start; i < hospital.size(); i++) {
            tmp.add(i);
            dfs(i + 1, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j]==1) people.add(new Node(i, j));
                else if (board[i][j]==2) hospital.add(new Node(i, j));
            }
        }

        // 병원 선택 조합 생성
        dfs(0, new ArrayList<Integer>());

        // 각 사람별 최소 병원 구간 구하기
        for (List<Integer> lst: combi) {
            int tmp = 0;
            for (Node p : people) {
                int mn = Integer.MAX_VALUE;
                for (Integer i : lst) {         // 각 사람이 병원을 돌며, 최소 병원길이 찾기
                    Node h = hospital.get(i);
                    int dist = Math.abs(h.x - p.x) + Math.abs(h.y - p.y);
                    mn = Math.min(mn, dist);
                }
                tmp += mn;                      // 최소 병원길이 더해주기
                if (tmp >= ans) break;
            }
            ans = Math.min(ans, tmp);
        }

        System.out.println(ans);
    }
    static class Node{
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
