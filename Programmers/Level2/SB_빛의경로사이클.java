import java.util.ArrayList;
import java.util.Collections;


public class SB_빛의경로사이클 {
    static int N, M;
    static boolean[][][] visited;
    static char[][] board;
    static ArrayList<Integer> ans = new ArrayList<>();
    static int[] dx = {-1, 0, 1, 0};        // 상우하좌
    static int[] dy = {0, 1, 0, -1};

    private static int shootLight(int i, int j, int d) {
        int cnt = 0;

        while (!visited[i][j][d]) {     // 사이클을 만들기전까지 반복
            visited[i][j][d] = true;
            cnt++;

            // 현재 위치에 따른 방향 변환
            if (board[i][j]=='L') d = (d + 3) % 4;
            else if (board[i][j]=='R') d = (d + 1) % 4;

            // 다음 위치 이동
            int nx = (i + dx[d] + N) % N;
            int ny = (j + dy[d] + M) % M;
            i = nx;
            j = ny;
        }
        return cnt;
    }
    public static int[] solution(String[] grid) {
        N = grid.length;
        M = grid[0].length();
        visited = new boolean[N][M][4];
        board = new char[N][M];

        // char배열로 그리드 변환
        for (int i = 0; i < N; i++) {
            String row = grid[i];
            for (int j = 0; j < M; j++) {
                board[i][j] = row.charAt(j);
            }
        }

        // 방문하지 않은 곳과 쏘아보지 않은 빛 방향일때 빛 쏘기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int d = 0; d < 4; d++) {
                    if (!visited[i][j][d]) ans.add(shootLight(i, j, d));
                }
            }
        }

        Collections.sort(ans);
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}
