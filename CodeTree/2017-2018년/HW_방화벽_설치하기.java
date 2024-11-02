import java.util.*;
import java.io.*;

// 방화벽 3개를 추가로 설치 했을 때 방화벽을 제외하고 불이 퍼지지 않는 영역 크기의 최댓값을 출력
public class HW_방화벽_설치하기 {
    static int n, m;
    static int[][] board;
    static int[] dx = {-1, 1, 0, 0}; // 상하좌우
    static int[] dy = {0, 0, -1, 1};
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0);
        System.out.println(max);
    }

    static void dfs(int wall) { // 방화벽 설치
        if (wall == 3) { // 방화벽 3개까지
            bfs();
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 0) {
                    board[i][j] = 1;
                    dfs(wall + 1);
                    board[i][j] = 0;
                }
            }
        }
    }

    static void bfs() { // 불 번지는 영역 확인
        Queue<Node> queue = new LinkedList<>();
        int[][] temp = new int[n][m];

        for (int i = 0; i < n; i++) { // 배열 복사
            for (int j = 0; j < m; j++) {
                temp[i][j] = board[i][j];
                if (board[i][j] == 2) { // 불이 있을 경우
                    queue.add(new Node(i, j)); // 불 번짐
                }
            }
        }
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int x = node.x;
            int y = node.y;

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                if (isRange(nx, ny) && temp[nx][ny] == 0) {
                    queue.add(new Node(nx, ny));
                    temp[nx][ny] = 2;
                }
            }
        }
        check(temp);
    }

    static void check(int[][] temp) { // 불이 번지지 않은 영역 확인
        int safe = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (temp[i][j] == 0) {
                    safe++;
                }
            }
        }
        max = Math.max(max, safe);
    }

    static boolean isRange(int nx, int ny) {
        return nx >= 0 && nx < n && ny >= 0 && ny < m;
    }

    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}