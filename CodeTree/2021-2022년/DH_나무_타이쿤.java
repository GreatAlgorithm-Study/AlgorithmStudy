import java.io.*;
import java.util.*;

public class DH_나무_타이쿤 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M;
    static int[][] map; // 나무의 높이 저장
    static boolean[][] check; // 특수 영양제가 있는 곳을 저장하는 변수
    static int[] dr = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dc = {0, 1, 1, 0, -1, -1, -1, 0, 1};

    static void solution() throws Exception {

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int d = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            move(d, p); // 특수 영양제 이동
            grow(); // 대각선 확인 후 영양제 투입
            cutAndPut(); // 높이가 2 이상인 리브로수 베고, 특수영양제 올리기
        }

        System.out.println(getTotalHeight());
    }

    static int getTotalHeight() {
        int result = 0;
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < N; c++) {
                result += map[r][c];
            }
        }
        return result;
    }
    static void cutAndPut() {
        // 새롭게 특수 영양제를 놓을 곳을 저장하는 변수
        boolean[][] tmp = new boolean[N][N];

        for(int r = 0; r < N; r++) {
            for(int c = 0; c < N; c++) {
                // 이미 영양제가 있었던 곳이거나, 높이가 2미만이면 continue;
                if(check[r][c] || map[r][c] < 2) continue;
                map[r][c] -= 2;
                tmp[r][c] = true;
            }
        }

        // check 배열 update
        check = tmp;
    }

    static void grow() {
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < N; c++) {
                if(!check[r][c]) continue;
                int cnt = 0;
                for(int d = 2; d < dr.length; d += 2) {

                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if(!check(nr, nc) || map[nr][nc] == 0) continue;
                    cnt++;
                }

                map[r][c] += cnt;
            }
        }
    }

    static boolean check(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
    static void move(int d, int p) {
        boolean[][] tmp = new boolean[N][N];

        for(int r = 0; r < N; r++) {
            for(int c = 0; c < N; c++) {
                if(!check[r][c]) continue;
                check[r][c] = false;

                int nr = (r + dr[d] * p) % N;
                int nc = (c + dc[d] * p) % N;

                nr = nr < 0 ? nr + N: nr;
                nc = nc < 0 ? nc + N :nc;

                map[nr][nc] += 1; // 이동 후 땅에 영양제 투입
                tmp[nr][nc] = true;
            }
        }

        check = tmp;
    }
    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        check = new boolean[N][N];

        for(int r = N - 2; r < N; r++) {
            for(int c = 0; c < 2; c++) check[r][c] = true;
        }

        for(int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        solution();
    }
}