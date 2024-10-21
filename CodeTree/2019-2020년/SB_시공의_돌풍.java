import java.io.*;
import java.util.*;

public class SB_시공의_돌풍 {
    static int T,N,M;
    static int[][] board;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Node[] cl = new Node[2];

    private static void diffusion() {
        // 먼지 확산
        int[][] dust = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j]==-1) continue;
                int div = board[i][j]/5;
                int cnt = 0;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if (!isValid(nx, ny) || board[nx][ny]==-1) continue;
                    dust[nx][ny]+=div;
                    cnt++;
                }
                dust[i][j] -= div*cnt;
            }
        }

        // 확산된 먼지 더하기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                board[i][j] += dust[i][j];
            }
        }
    }

    private static void cleanUp() {
        // 윗부분 먼지 흡입
        Queue<Integer> que1 = new ArrayDeque<>();
        int r = cl[0].r;
        que1.add(0);    // 청소기에서 나온 공기 0
        for (int j = 1; j < M; j++) {   // (R,0) -> (R,M)
            que1.add(board[r][j]);
        }
        for (int i = r-1; i >= 0; i--) {  // (R,M) -> (0,M)
            que1.add(board[i][M-1]);
        }
        for (int j = M-1-1; j >= 0; j--) {   // (0,M) -> (0,0)
            que1.add(board[0][j]);
        }
        for (int i = 1; i < r; i++) {  // (0,0) -> (R,0)
            que1.add(board[i][0]);
        }

        // 윗부분 먼지 밀기
        for (int j = 1; j < M; j++) {   // (R,0) -> (R,M)
            board[r][j] = que1.poll();
        }
        for (int i = r-1; i >= 0; i--) {  // (R,M) -> (0,M)
            board[i][M-1] = que1.poll();
        }
        for (int j = M-1-1; j >= 0; j--) {   // (0,M) -> (0,0)
            board[0][j] = que1.poll();
        }
        for (int i = 1; i < r; i++) {  // (0,0) -> (R,0)
            board[i][0] = que1.poll();
        }
        board[r][0] = -1;
    }

    private static void cleanDown() {
        // 아래 부분 먼지 흡입
        Queue<Integer> que = new ArrayDeque<>();
        int r = cl[1].r;
        que.add(0);    // 청소기에서 나온 공기 0
        for (int j = 1; j < M; j++) {   // (R,0) -> (R,M)
            que.add(board[r][j]);
        }
        for (int i = r+1; i < N; i++) {  // (R,M) -> (N,M)
            que.add(board[i][M-1]);
        }
        for (int j = M-1-1; j >= 0; j--) {   // (N,M) -> (N,0)
            que.add(board[N-1][j]);
        }
        for (int i = N-1-1; i > r; i--) {  // (N,0) -> (R,0)
            que.add(board[i][0]);
        }

        // 아래부분 먼지 밀기
        for (int j = 1; j < M; j++) {   // (R,0) -> (R,M)
            board[r][j] = que.poll();
        }
        for (int i = r+1; i < N; i++) {  // (R,M) -> (N,M)
            board[i][M-1] = que.poll();
        }
        for (int j = M-1-1; j >= 0; j--) {   // (N,M) -> (N,0)
            board[N-1][j] = que.poll();
        }
        for (int i = N-1-1; i > r; i--) {  // (N,0) -> (R,0)
            board[i][0] = que.poll();
        }
        board[r][0] = -1;
    }

    private static boolean isValid(int x, int y) {
        return 0<=x && x<N && 0<=y && y<M;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        boolean flag = false;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j]==-1) {
                    if(!flag) {
                        cl[0] = new Node(i, j);
                        flag = true;
                    } else cl[1] = new Node(i, j);
                }
            }
        }

        for (int t = 0; t < T; t++) {
            // 1. 먼지 확산
            diffusion();

            // 2. 돌풍 청소
            cleanUp();
            cleanDown();
        }

        // 총 먼지
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j]==-1) continue;
                cnt += board[i][j];
            }
        }
        System.out.println(cnt);
    }

    static class Node{
        int r, c;

        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
