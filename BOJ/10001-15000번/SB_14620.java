import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SB_14620 {
    static int N;
    static int[][] board;
    static boolean[][] visited;
    static int ans = Integer.MAX_VALUE;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};


    private static boolean canPlant(int x, int y) {
        if (visited[x][y]) return false;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (visited[nx][ny]) return false;
        }
        return true;
    }

    private static boolean isEdge(int x, int y) {          // 가장자리 체크
        return x==0 || y==0 || x==N-1 || y==N-1;
    }
    private static int plantSeed(int x, int y, boolean flag) {
        int tmp = board[x][y];
        visited[x][y] = flag;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            visited[nx][ny] = flag;
            tmp+=board[nx][ny];
        }
        return tmp;
    }
    private static void dfs(int depth, int cur, int total) {
        if (depth == 3) {
            ans = Math.min(ans, total);
            return;
        }

        for (int idx = cur; idx < N * N; idx++) {
            int r = idx/N;
            int c = idx%N;
            if (isEdge(r, c) || !canPlant(r, c)) continue;                        // 꽃 못심으면 패쓰
            int cost = plantSeed(r, c, true);                              // 꽃심으면서 비용 계산
            dfs(depth+1, idx, total+ cost);                       // 꽃 심은 값으로 다음 dfs 진행
            plantSeed(r, c, false);                                       // 심었던 꽃, 비용 되돌리기
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, 0);
        System.out.println(ans);
    }
}
