import java.io.*;
import java.util.*;

class BOJ_1938 {
    static int n;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static char[][] board;
    static boolean[][][] visited;

    static class Node {
        int x, y, dir, cnt;

        Node(int x, int y, int dir, int cnt) {
            this.x = x;
            this.y = y;
            this.dir = dir; // 0: 가로, 1: 세로
            this.cnt = cnt; // 이동 횟수
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new char[n][n];
        visited = new boolean[n][n][2];

        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < n; j++) {
                board[i][j] = input.charAt(j);
            }
        }
        int[] start = findPosition('B');
        int[] target = findPosition('E');
        int startX = start[0], startY = start[1], startDir = start[2];
        int targetX = target[0], targetY = target[1], targetDir = target[2];

        System.out.println(Bfs(startX, startY, startDir, targetX, targetY, targetDir));
    }

    private static int[] findPosition(char target) {
        int sumX = 0, sumY = 0, dir = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == target) {
                    sumX += i;
                    sumY += j;
                }
            }
        }
        int centerX = sumX / 3; // 중심 좌표 계산 /3
        int centerY = sumY / 3; //

        if (0 < centerY && board[centerX][centerY - 1] == target) {
            dir = 0; // 가로
        } else {
            dir = 1; // 세로
        }
        return new int[]{centerX, centerY, dir};
    }

    private static int Bfs(int startX, int startY, int startDir, int targetX, int targetY, int targetDir) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(startX, startY, startDir, 0));
        visited[startX][startY][startDir] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();
            int x = cur.x, y = cur.y, dir = cur.dir, cnt = cur.cnt;

            if (x == targetX && y == targetY && dir == targetDir) {
                return cnt;
            }
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                if (isValid(nx, ny) && check(nx, ny, dir) && !visited[nx][ny][dir]) {
                    visited[nx][ny][dir] = true;
                    q.add(new Node(nx, ny, dir, cnt + 1));
                }
            }
            int nextDir = 1 - dir;
            if (canRotate(x, y) && !visited[x][y][nextDir]) {
                visited[x][y][nextDir] = true;
                q.add(new Node(x, y, nextDir, cnt + 1));
            }
        }
        return 0;
    }

    private static boolean isValid(int nx, int ny) {
        return nx >= 0 && nx < n && ny >= 0 && ny < n && board[nx][ny] != '1';
    }

    private static boolean check(int nx, int ny, int dir) {
        if (!isValid(nx, ny)) return false;
        if (dir == 0) {
            return isValid(nx, ny - 1) && isValid(nx, ny) && isValid(nx, ny + 1);
        } else {
            return isValid(nx - 1, ny) && isValid(nx, ny) && isValid(nx + 1, ny);
        }
    }

    private static boolean canRotate(int x, int y) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int nx = x + i, ny = y + j;
                if (!isValid(nx, ny)) {
                    return false;
                }
            }
        }
        return true;
    }
}
