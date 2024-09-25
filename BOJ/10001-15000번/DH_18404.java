import java.io.*;
import java.util.*;

/*
현명한 나이트
 */

public class DH_18404 {
    static class Point {
        int r, c, d;

        public Point(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }
    static Deque<Point> q = new ArrayDeque<>();
    static int N, M;
    static int[][] map; // 방문: -1, 말이 있는 곳: 1 ~ N
    static int[] dr = {-1, -2, -2, -1, 1, 2, 2, 1};
    static int[] dc = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] result;

    static void solution() {
        int cnt = 0; // 말의 개수 세는 변수

        L: while(!q.isEmpty()) {
            Point current = q.poll();

            for(int d = 0; d < dr.length; d++) {
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];

                // 나이트가 갈 수 없는 곳이면서
                // 이미 간 곳이라면 continue
                if(!check(nr, nc) || map[nr][nc] == -1) continue;
                if(map[nr][nc] > 0) { // 말이 있는 곳
                    cnt++;
                    result[map[nr][nc] - 1] = current.d + 1; // 몇 번째에 도달했는지 저장
                    if(cnt == M) break L; // 말이 있는 모든 곳을 방문했다면 while문 빠져나오기
                }
                q.add(new Point(nr, nc, current.d + 1));
                map[nr][nc] = -1; // 방문처리
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i: result) sb.append(i).append(" ");
        System.out.println(sb);
    }

    static boolean check(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        result = new int[M];

        st = new StringTokenizer(br.readLine());

        // 나이트의 위치 입력
        int r = Integer.parseInt(st.nextToken()) - 1;
        int c = Integer.parseInt(st.nextToken()) - 1;
        q.add(new Point(r, c, 0));
        map[r][c] = -1;

        // 말의 위치 입력
        // 말의 위치에 몇 번째 말이 저장되어 있는지 저장
        int idx = 1;
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken()) - 1;
            c = Integer.parseInt(st.nextToken()) - 1;

            map[r][c] = idx++;
        }

        solution();
    }
}
