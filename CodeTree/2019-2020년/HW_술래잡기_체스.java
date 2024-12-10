import java.util.*;
import java.io.*;

public class HW_술래잡기_체스 {
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    static int ans = 0;

    static class Node {
        int p, d;
        Node(int p, int d) {
            this.p = p;
            this.d = d;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        Node[][] board = new Node[4][4];
        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int p = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken()) - 1;
                board[i][j] = new Node(p, d);
            }
        }

        int initScore = board[0][0].p;
        int initDir = board[0][0].d;

        board[0][0] = new Node(0, initDir); // 술래말 초기화

        dfs(board, 0, 0, initScore);
        System.out.println(ans);
    }

    static void dfs(Node[][] board, int x, int y, int score) {
        ans = Math.max(ans, score); // 점수 갱신

        Node[][] copyBoard = copy(board);
        find(copyBoard);

        Node cur = copyBoard[x][y];
        int dir = cur.d;

        int nx = x + dx[dir];
        int ny = y + dy[dir];

        // 도둑말 이동
        while (isValid(nx, ny)) {
            if (copyBoard[nx][ny] != null) { // 도둑말이 있는 칸만 이동
                Node caught = copyBoard[nx][ny];
                copyBoard[x][y] = null; // 술래말이 원래 있던 칸 비우기
                copyBoard[nx][ny] = new Node(0, caught.d); // 술래말 이동

                dfs(copyBoard, nx, ny, score + caught.p); // 재귀

                copyBoard[nx][ny] = caught;
                copyBoard[x][y] = cur;
            }
            nx += dx[dir];
            ny += dy[dir];
        }
    }

    static void find(Node[][] board) {
        for (int k = 1; k <= 16; k++) { // 도둑말 번호 순서대로 탐색
            boolean visited = false; // 방문 처리
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (board[i][j] != null && board[i][j].p == k) {
                        move(board, i, j);
                        visited = true;
                        break;
                    }
                }
                if(visited) break;
            }
        }
    }

    static void move(Node[][] board, int x, int y) {
        Node cur = board[x][y];
        int d = cur.d;

        for (int i = 0; i < 8; i++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            // 술래말이 있는 칸이나 경계를 벗어나면 이동 불가
            if (isValid(nx, ny) && (board[nx][ny] == null || board[nx][ny].p != 0)) {
                Node temp = board[nx][ny];
                board[nx][ny] = new Node(cur.p, d); // 새 위치로 이동
                board[x][y] = temp; // 기존 위치 업데이트
                return;
            }
            d = (d + 1) % 8; // 반시계 방향 45도 회전
        }
    }

    static boolean isValid(int nx, int ny) {
        return 0 <= nx  && nx < 4 && 0 <= ny && ny < 4;
    }

    static Node[][] copy(Node[][] board) {
        Node[][] tempBoard = new Node[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] != null) {
                    tempBoard[i][j] = new Node(board[i][j].p, board[i][j].d);
                }
            }
        }
        return tempBoard;
    }
}
