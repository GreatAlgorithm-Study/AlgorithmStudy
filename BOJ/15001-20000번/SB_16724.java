import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 어느 구역에 있더라도 SAFE ZONE 들거가게 하는 SAFE ZONE의 최소 개수 구하기
 * => 순환 그래프의 개수 구하기
 */

public class SB_16724 {
    static int N, M;
    static int[][] board;
    static int[] dx = {-1, 1, 0, 0};        // 행 이동
    static int[] dy = {0, 0, -1, 1};        // 열 이동
    static boolean[][] visited;
    static boolean[][] finished;
    static int cycle = 0;

    private static void dfs(int x, int y) {
        visited[x][y] = true;

        // 다음 좌표
        int d = board[x][y];
        int nx = x+dx[d];
        int ny = y+dy[d];

        if (visited[nx][ny] && !finished[nx][ny]) {     // 사이클 찾은 경우
            cycle++;
        }

        if (!visited[nx][ny]) dfs(nx, ny);         // 방문안했을 경우 다음 좌표로 이동

        finished[x][y] = true;                      // 현재 노드의 dfs를 다 돌았으면 끝났음 표시
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        visited = new boolean[N][M];
        finished = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                char d = line.charAt(j);
                switch (d){
                    case 'U':
                        board[i][j] = 0;
                        break;
                    case 'D':
                        board[i][j] = 1;
                        break;
                    case 'L':
                        board[i][j] = 2;
                        break;
                    case 'R':
                        board[i][j] = 3;
                        break;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!finished[i][j]) dfs(i, j);
            }
        }

        System.out.println(cycle);
    }
}
