import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
* bfs로 폭탄 묶음 찾기
*   - 이때 가장 큰 폭탄 묶음 있으면 갱신 (빨강 갯수따로, 리스트 저장 따로)
*   - 빨강 개수, 기준점 폭탄 정보 필요 (빨강제외 정렬)
* 선택된 폭탄 터트리기 (폭탄 점수++)
* 위에있던 폭탄 아래로 내려오기 (돌 빼고)
* 반시계 90도 회전 진행
* 또 내려오기
* */
public class SB_색깔폭탄 {
    static int N, M;
    static int[][] board;

    // 큰 폭탄 그룹 체크를 위한 변수
    static int bBRed = Integer.MAX_VALUE;
    static Boom bBoom = null;
    static List<Boom> bBooms = new ArrayList<>();
    static int score = 0;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    private static void rotate90R() {               // 시계방향 90도 회전
        int[][] tmpBoard = new int[N][N];
        for (int i = 0; i < N; i++) {
            System.arraycopy(board[i], 0, tmpBoard[i], 0, N);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = tmpBoard[N - 1 - j][i];
            }
        }
    }

    private static void rotate90L() {                 // 반시계 90도 회전
        int[][] tmpBoard = new int[N][N];
        for (int i = 0; i < N; i++) {
            System.arraycopy(board[i], 0, tmpBoard[i], 0, N);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = tmpBoard[j][N - 1 - i];
            }
        }
    }

    private static void tilt() {                        // 오른쪽으로 밀기
        for (int i = 0; i < N; i++) {
            int[] tmp = new int[N];
            Arrays.fill(tmp, -2);
            int idx = N-1;
            for (int j = N - 1; j >= 0; j--) {
                if (board[i][j]==-2) continue;          // 빈 곳은 패쓰
                if (board[i][j]==-1) {                  // 돌만나면
                    idx = j;
                    tmp[idx] = board[i][j];
                    idx--;
                    continue;
                }
                tmp[idx] = board[i][j];                 // 폭탄 떨어지고
                board[i][j] = -2;                       // 기존 폭탄 위치 빈칸 처리
                idx--;                                  // 폭탄이 떨어질 위치 갱신
            }
            System.arraycopy(tmp, 0, board[i], 0, N);
        }
    }
    private static void fallDownBoom() {
        // 중력상태 2번 겪음
        // 먼저 반시계 90도 돌리고 오른쪽으로 밀기 (== 첫번째 중력작용후 회전 상태)
        rotate90L();
        tilt();

        // 또 90도 반시계후 오른쪽밀고 시계 90도로 원상복구하면 최종 중력상태 완성
        rotate90L();
        tilt();
        rotate90R();
    }

    private static void removeBoom() {
        int size = bBooms.size();       // 점수 업데이트
        score += size * size;

//        System.out.println(bBooms);
//        System.out.println(bBoom);

        for (Boom b : bBooms) {         // 폭탄 터트리기(-2)
            board[b.x][b.y] = -2;
        }

        bBRed = Integer.MAX_VALUE;      // 큰 폭탄 정보 초기화
        bBoom = null;
        bBooms.clear();
    }

    private static void checkBigBoom(List<Boom> tmp, int RedSize) {
        Collections.sort(tmp);              // 기준폭탄을 위해 폭탄정렬
        Boom tB = null;
        for (Boom b : tmp) {
            if (b.c==0) continue;           // 빨강폭탄이면 패쓰
            tB = b;                         // 기준 폭탄 설정
            break;
        }

        if (tmp.size() > bBooms.size()) {     // 새로운 폭탄 수가 더 크면 갱신
            bBRed = RedSize;
            bBoom = tB;
            bBooms = new ArrayList<>(tmp);
            return;
        }
        if (RedSize < bBRed) {               // 빨강 폭탄 수가 더 적으면 갱신
            bBRed = RedSize;
            bBoom = tB;
            bBooms = new ArrayList<>(tmp);
            return;
        }

        if (tB.x > bBoom.x) {
            bBRed = RedSize;
            bBoom = tB;
            bBooms = new ArrayList<>(tmp);
            return;
        }

        if (tB.y < bBoom.y) {
            bBRed = RedSize;
            bBoom = tB;
            bBooms = new ArrayList<>(tmp);
        }
    }

    private static boolean bfs(Boom start, boolean[][] visited) {
        Queue<Boom> que = new ArrayDeque<>();
        List<Boom> tmp = new ArrayList<>();
        Set<Integer> red = new HashSet<>();
        que.offer(start);
        visited[start.x][start.y] = true;

        if (start.c==0) red.add(start.x * N + start.y);             // 시작점도 리스트에 넣기
        tmp.add(start);

        while (!que.isEmpty()) {
            Boom cur = que.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (!isValid(nx, ny) || board[nx][ny] < 0) continue;
                if (!visited[nx][ny] && board[nx][ny] == start.c) {     // 같은 색 넣기
                    que.offer(new Boom(nx, ny, board[nx][ny]));
                    visited[nx][ny] = true;
                    tmp.add(new Boom(nx, ny, board[nx][ny]));
                }
                if (board[nx][ny] == 0 && !red.contains(nx*N+ny)) {     // 빨간 색 넣기
                    que.offer(new Boom(nx, ny, board[nx][ny]));
                    visited[nx][ny] = true;
                    tmp.add(new Boom(nx, ny, board[nx][ny]));
                    red.add(nx * N + ny);
                }
            }
        }

        if (tmp.size() <= 1 || red.size()==tmp.size()) {        // 크기가 2가 안되거나 빨강으로만 이루어져있으면 만들 수 없음
            return false;
        }

        // 가장 큰 폭탄 그룹 갱신
        if (tmp.size() >= bBooms.size()) checkBigBoom(tmp, red.size());
        return true;
    }

    private static boolean findBigBoom() {
        boolean[][] visited = new boolean[N][N];

        boolean isBoom = false;
        for (int i=0; i<N; i++){
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && board[i][j] > 0) {
                    isBoom |= bfs(new Boom(i, j, board[i][j]), visited);
                }
            }
        }
        return isBoom;
    }

    private static boolean isValid(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (findBigBoom()) {     // 1. 큰 폭탄 묶음 있을때까지 반복
            System.out.println("폭탄 제거 전 보드: " + Arrays.deepToString(board));
            // 2. 폭탄 제거하기
            removeBoom();
            System.out.println("폭탄 제거 후 보드: " + Arrays.deepToString(board));

            // 3. 폭탄 떨어지기
            fallDownBoom();
            System.out.println("폭탄 중력 후 보드: " + Arrays.deepToString(board));
        }
        System.out.println(score);
    }

    static class Boom implements Comparable<Boom>{
        int x, y, c;

        public Boom(int x, int y, int c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }

        @Override
        public int compareTo(Boom o) {
            if (o.x!=this.x) return o.x - this.x;
            return this.y - o.y;
        }

        @Override
        public String toString() {
            return "Boom{" +
                    "x=" + x +
                    ", y=" + y +
                    ", c=" + c +
                    '}';
        }
    }
}
