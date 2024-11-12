import java.io.*;
import java.util.*;

public class DH_회전하는_빙하 {
    static class Point {
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int N, Q, totalIceCnt, iceSize;
    static int[][] map;
    static BufferedReader br;
    static StringTokenizer st;
    static int[] dr = {1, 0, -1, 0}, dc = {0, 1, 0, -1}; // 하, 우, 상, 좌
    static boolean[][] v;
    static Deque<Point> q;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/회전하는빙하.txt"));
        initInput();
        solution();
    }

    static void solution() throws Exception {
        st = new StringTokenizer(br.readLine());

        while(Q-- > 0) {
            int level = Integer.parseInt(st.nextToken());
            rotate(level);
            melt();
        }

        // map을 확인하며 영역의 최대 크기와 남아있는 얼음의 양 구하기
        checkMapStatus();

        StringBuilder sb = new StringBuilder();
        sb.append(totalIceCnt).append("\n").append(iceSize);
        System.out.println(sb);
    }

    static void checkMapStatus() {
        v = new boolean[map.length][map[0].length];
        q = new ArrayDeque<>();

        // bfs를 통해 얼음이 있는 영역의 최대 크기와 남아있는 얼음의 양 구하기
        for(int r = 0; r < map.length; r++) {
            for(int c = 0; c < map[0].length; c++) {
                if(v[r][c] || map[r][c] == 0) continue;
                bfs(r, c);
            }
        }
    }

    static void bfs(int r, int c) {
        q.add(new Point(r, c));
        v[r][c] = true;
        totalIceCnt += map[r][c];
        int size = 1;

        while(!q.isEmpty()) {
            Point current = q.poll();

            for(int d = 0; d < 4; d++) {
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];

                if(!check(nr, nc) || v[nr][nc] || map[nr][nc] == 0) continue;
                q.add(new Point(nr, nc));
                v[nr][nc] = true;
                totalIceCnt += map[nr][nc];
                size++;
            }
        }

        iceSize = Math.max(iceSize, size);
    }

    // meltFlag[r][c] == true인 지점 녹이기
    static void melt() {
        boolean[][] meltFlag = makeMeltFlagMap();
        for(int r = 0; r < meltFlag.length; r++) {
            for(int c = 0; c < meltFlag[0].length; c++) {
                if(!meltFlag[r][c] || map[r][c] == 0) continue;
                map[r][c]--;
            }
        }
    }

    // 해당 부분이 녹는지 안녹는지 확인하는 meltFlag 변수를 초기화하는 함수
    static boolean[][] makeMeltFlagMap() {
        boolean[][] meltFlag = new boolean[map.length][map[0].length];

        for(int r = 0; r < map.length; r++) {
            for(int c = 0; c < map[0].length; c++) {
                int iceCnt = 0;
                for(int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if(!check(nr, nc) || map[nr][nc] == 0) continue;
                    iceCnt++;
                }

                if(iceCnt < 3) meltFlag[r][c] = true;
            }
        }
        return meltFlag;
    }
    static boolean check(int r, int c) {
        return r >= 0 && r < map.length && c >= 0 && c < map[0].length;
    }
    static void rotate(int level) {
        int size = (int) Math.pow(2, level);
        // r, c: 하늘색 사각형 배열이 시작하는 부분
        for(int r = 0; r < map.length; r += size) {
            for(int c = 0; c < map[0].length; c += size) {

                // map에서 1사분면만 해당되는 부분을 저장
                int[][] tmp = copyPart1(r, c, size / 2);
                rotate(r, c, level, tmp);
            }
        }
    }

    static int[][] copyPart1(int r, int c, int level) {
        int[][] tmp = new int[level][level];
        for(int sr = r; sr < r + level; sr++) {
            for(int sc = c; sc < c + level; sc++) {
                tmp[sr - r][sc - c] = map[sr][sc];
            }
        }

        return tmp;
    }

    static void rotate(int r, int c, int level, int[][] tmp) {

        int size = (int) Math.pow(2, level) / 2;

        // 반시계 방향으로 돌면서 바꿀 값을 가지고 옴
        for(int i = 0; i < 3; i++) {

            int nr = r + dr[i] * size;
            int nc = c + dc[i] * size;

            // rr, cc: 첨부한 사진 level2에서 민트색(?) 부분 배열
            // level1에서는 크기가 1 * 1이기 때문에 사진에 표시 안함
            for(int rr = 0; rr < size; rr++) {
                for(int cc = 0; cc < size; cc++) {
                    map[r + rr][c + cc] = map[nr + rr][nc + cc];
                }
            }

            r = nr; c = nc;
        }

        // 처음 부분은 이미 값이 바뀐 상태이기 때문에
        // 미리 tmp 값에 저장한 값으로 가져옴
        for(int rr = 0; rr < size; rr++) {
            for(int cc = 0; cc < size; cc++) {
                map[r + rr][c + cc] = tmp[rr][cc];
            }
        }
    }

    static void printMap() {
        for(int r = 0; r < map.length; r++) {
            System.out.println(Arrays.toString(map[r]));
        }
    }

    static void initInput() throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        int length = (int) Math.pow(2, N);
        map = new int[length][length];

        for(int r = 0; r < map.length; r++) {
            st = new StringTokenizer(br.readLine());

            for(int c = 0; c < map[0].length; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }


    }
}