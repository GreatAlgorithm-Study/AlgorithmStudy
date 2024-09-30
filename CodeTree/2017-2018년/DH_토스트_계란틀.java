import java.io.*;
import java.util.*;

/*
토스트 계란틀
 */

public class DH_토스트_계란틀 {
    static class Point {
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int N, L, R;
    static int[][] map;
    static boolean[][] v;
    static int[] dr = {1, 0, -1, 0}, dc = {0, 1, 0, -1};
    // tmp: 방문한 위치들을 저장할 임시 큐
    static ArrayDeque<Point> q, tmp;

    static void solution() {
        int turn = 0;
        while(canMove()) turn++;
        System.out.println(turn);
    }

    static boolean canMove() {
        v = new boolean[N][N];
        boolean flag = false;

        for(int r = 0; r < N; r++) {

            int sum, cnt;
            for(int c = 0; c < N; c++) {
                if(v[r][c]) continue;
                sum = map[r][c];
                cnt = 1;

                q.add(new Point(r, c));
                tmp.add(new Point(r, c));
                v[r][c] = true;
                while(!q.isEmpty()) {
                    Point current = q.poll();
                    tmp.add(current);

                    for(int d = 0; d < 4; d++) {
                        int nr = current.r + dr[d];
                        int nc = current.c + dc[d];

                        if(!check(nr, nc) || v[nr][nc] || !isInRange(map[current.r][current.c], map[nr][nc])) continue;
                        v[nr][nc] = true;
                        q.add(new Point(nr, nc));
                        sum += map[nr][nc];
                        cnt++;
                        flag = true;
                    }
                }

                while(!tmp.isEmpty()) {
                    Point current = tmp.poll();
                    map[current.r][current.c] = sum / cnt;
                }
            }

        }
        return flag;
    }

    static boolean isInRange(int a, int b) {
        return Math.abs(a - b) >= L && Math.abs(a - b) <= R;
    }
    static boolean check(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        q = new ArrayDeque<>();
        tmp = new ArrayDeque<>();

        for(int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        solution();
    }
}
