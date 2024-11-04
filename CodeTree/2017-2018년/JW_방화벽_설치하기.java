import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class JW_방화벽_설치하기 {

    static int n, m;
    static int[][] board; // 방 구조를 나타낼 2차원 배열
    static ArrayList<int[]> fire = new ArrayList<>(); // 불의 위치를 저장할 리스트
    static int[] dy = { 1, -1, 0, 0 };
    static int[] dx = { 0, 0, 1, -1 };
    static int originalArea = 0; // 초기 빈 영역의 수
    static int maxArea = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                // 빈 영역이라면 
                if (board[i][j] == 0)
                    originalArea++;
                // 불이 있다면
                if (board[i][j] == 2)
                    fire.add(new int[] { i, j });
            }
        }
        dfs(0, 0); // 방화벽 설치
        System.out.println(maxArea);
    }

    // 방화벽 설치하기 위한 재귀함수
    private static void dfs(int depth, int p) {
        // 방화벽이 3개가 설치되었다면
        if (depth == 3) {
            bfs(); // 불 퍼져나가는 시뮬레이션
            return;
        }
        for (int i = p; i < n * m; i++) {
            int y = i / m, x = i % m;
            if (board[y][x] == 0) {
                board[y][x] = 1;
                dfs(depth + 1, i);
                board[y][x] = 0;
            }
        }
    }

    private static void bfs() {
        int area = originalArea - 3; // 방화벽을 제외한 빈 영역의 수
        Deque<int[]> dq = new ArrayDeque<>(fire); // 불 위치 설정
        boolean[][] visited = new boolean[n][m]; // 방문 처리
        while (!dq.isEmpty()) {
            int[] cur = dq.poll();
            for (int i = 0; i < 4; i++) {
                int y = cur[0] + dy[i];
                int x = cur[1] + dx[i];
                // 불이 번질 수 있는 곳이라면
                if (isValid(y, x) && !visited[y][x]) {
                    dq.offer(new int[] { y, x });
                    visited[y][x] = true;
                    area--; // 기존 영역에서 1감소
                }
            }
        }
        maxArea = Math.max(maxArea, area);
    }

    // 경계 체크 및 이동할 수 있는지 확인하는 함수
    private static boolean isValid(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < m && board[y][x] == 0;
    }
}