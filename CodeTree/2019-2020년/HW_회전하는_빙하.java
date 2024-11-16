import java.io.*;
import java.util.*;

public class HW_회전하는_빙하 {
    static class Node {
        int x, y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n, q, L;
    static int[][] board, nextboard;
    static boolean[][] visited;
    static int[] dx = {0, 1, -1, 0}; // 오른쪽, 아래, 위, 왼쪽
    static int[] dy = {1, 0, 0, -1};
    static int max = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 격자의 크기 (2^n * 2^n)
        q = Integer.parseInt(st.nextToken()); // 회전 횟수
        L = (int) Math.pow(2, n);
        board = new int[L][L];
        nextboard = new int[L][L];

        // 보드 입력
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < L; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 회전 레벨 입력
        st = new StringTokenizer(br.readLine());
        while (q-- > 0) {
            int lv = Integer.parseInt(st.nextToken());
            if (lv > 0) rotate(lv);
            melt();
        }

        // 1. 남아있는 빙하의 총량 계산
        int sum = 0;
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < L; j++) {
                sum += board[i][j];
            }
        }
        System.out.println(sum);

        // 2. 가장 큰 얼음 군집 크기 계산
        visited = new boolean[L][L];
        max = 0;
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < L; j++) {
                if (!visited[i][j] && board[i][j] > 0) {
                    max = Math.max(max, bfs(i, j));
                }
            }
        }
        System.out.println(max);
    }

    // 초기화
    public static void initialize() {
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < L; j++) {
                nextboard[i][j] = 0;
            }
        }
    }


    public static void rotate(int level) {
        initialize();  // `nextboard` 초기화
        int boxSize = (int) Math.pow(2, level);
        int halfSize = (int) Math.pow(2, level - 1);

        for (int i = 0; i < L; i += boxSize) { // 시계 방향 90도 회전
            for (int j = 0; j < L; j += boxSize) {
                move(i, j, halfSize, 0);
                move(i, j + halfSize, halfSize, 1);
                move(i + halfSize, j, halfSize, 2);
                move(i + halfSize, j + halfSize, halfSize, 3);
            }
        }

        for (int i = 0; i < L; i++) {
            for (int j = 0; j < L; j++) {
                board[i][j] = nextboard[i][j]; // 배열 복사
            }
        }
    }

    // size`만큼의 하위 격자를 시계 방향으로 회전
    public static void move(int startX, int startY, int size, int dir) {
        for (int i = startX; i < startX + size; i++) {
            for (int j = startY; j < startY + size; j++) {
                int nx = i + dx[dir] * size;
                int ny = j + dy[dir] * size;
                if (!isValid(nx, ny)) continue;
                nextboard[nx][ny] = board[i][j];
            }
        }
    }

    public static void melt() {
        initialize();

        for (int i = 0; i < L; i++) {
            for (int j = 0; j < L; j++) {
                if (board[i][j] != 0 && iceCnt(i, j) < 3) {
                    nextboard[i][j] = board[i][j] - 1; // -1
                } else {
                    nextboard[i][j] = board[i][j];
                }
            }
        }

        for (int i = 0; i < L; i++) {
            for (int j = 0; j < L; j++) {
                board[i][j] = nextboard[i][j];
            }
        }
    }

    public static int iceCnt(int x, int y) {
        int cnt = 0;
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (isValid(nx, ny) && board[nx][ny] != 0) {
                cnt++;
            }
        }
        return cnt;
    }

    // BFS : 얼음 군집 크기를 계산
    public static int bfs(int x, int y) {
        int size = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nx = pos[0] + dx[d];
                int ny = pos[1] + dy[d];

                if (isValid(nx, ny) && !visited[nx][ny] && board[nx][ny] > 0) {
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny});
                    size++;
                }
            }
        }
        return size;
    }

    public static boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < L && y < L;
    }
}