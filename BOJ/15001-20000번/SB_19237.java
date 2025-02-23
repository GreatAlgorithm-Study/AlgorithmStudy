import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SB_19237 {
    static int N, M, K;
    static int[] dx = {-1, 1, 0, 0};    // 상하좌우
    static int[] dy = {0, 0, -1, 1};
    static int[][] board;
    static Smell[][] sBoard;
    static int[][][] dir;
    static Shark[] sharks;

    private static boolean checkShark() {
        int cnt = 0;
        for (Shark s : sharks) {
            if (!s.isDead) cnt++;
        }
        return cnt == 1;
    }

    private static void spreadSmell() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (sBoard[i][j].idx==0) continue;
                sBoard[i][j].t--;
                if (sBoard[i][j].t==0) sBoard[i][j].idx = 0;
            }
        }
    }

    private static void moveShark() {
        int[][] tmpBoard = new int[N][N];

        for (Shark s : sharks) {
            if (s.idx==0 || s.isDead) continue;

            // 아무 냄새 없는 칸 있는지 체크
            int mvDir = -1;
            for (int d : dir[s.idx][s.d]) {     // 현재 상어가 보고있는 방향부터
                int nx = s.x + dx[d];
                int ny = s.y + dy[d];
                if (!isValid(nx, ny) || sBoard[nx][ny].idx!=0) continue;
                mvDir = d;
                break;
            }

            if (mvDir==-1) {     // 냄새 빈칸이 없을 경우
                for (int d : dir[s.idx][s.d]) {     // 현재 상어가 바라보는 위치에서 이동 우선순위
                    int nx = s.x + dx[d];
                    int ny = s.y + dy[d];

                    if (!isValid(nx, ny) || sBoard[nx][ny].idx!= s.idx) continue;
                    mvDir = d;
                    break;
                }
            }

            // 위치 최종 설정
            s.x+= dx[mvDir];
            s.y += dy[mvDir];
            s.d = mvDir;

            if (tmpBoard[s.x][s.y] != 0) {
                s.isDead = true;
            }else{
                board[s.x][s.y] = s.idx;
                tmpBoard[s.x][s.y] = K;
            }
        }


        // 모든 상어가 이동이 끝나면 냄새 뿌리기
        for (Shark s : sharks) {
            if (s.idx==0 || s.isDead) continue;
            sBoard[s.x][s.y] = new Smell(s.idx, K);
        }
    }

    private static boolean isValid(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new int[N][N];
        sBoard = new Smell[N][N];
        dir = new int[M+1][4][4];

        // 냄새보드 초기화
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sBoard[i][j] = new Smell(0, 0);
            }
        }

        sharks = new Shark[M + 1];
        for (int i = 0; i < N; i++) {                   // 보드 입력
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                board[i][j] = num;
                if (num != 0) {
                    sharks[num] = new Shark(num, i, j);
                    sBoard[i][j] = new Smell(num, K);       // 처음 냄새 남기기
                }
            }
        }


        st = new StringTokenizer(br.readLine());        // 상어 방향 입력
        for (int i = 1; i <= M; i++) {
            int d = Integer.parseInt(st.nextToken())-1;
            sharks[i].setDir(d);
        }
        sharks[0] = new Shark(0, 0, 0);
        sharks[0].isDead = true;

        for (int k = 1; k <= M; k++) {                  // 상어별 우선순위 방향 입력
            for (int i=0; i<4; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 4; j++) {
                    dir[k][i][j] = Integer.parseInt(st.nextToken())-1;
                }
            }
        }

        int time = 0;
        while (time++ <= 1000) {
            // 상어 이동
            moveShark();

            // 냄새 시간 --
            spreadSmell();

            // 상어 체크
            if (checkShark()) {
                System.out.println(time);
                return;
            }
        }
        System.out.println(-1);
    }

    static class Shark implements Comparable<Shark>{
        int x, y, idx, d;
        boolean isDead = false;

        public Shark(int idx, int x, int y) {
            this.idx = idx;
            this.x = x;
            this.y = y;
        }

        public void setDir(int d) {
            this.d = d;
        }

        private void move(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }

        @Override
        public int compareTo(Shark o) {
            return this.idx - o.idx;
        }

        @Override
        public String toString() {
            return "Shark{" +
                    "x=" + x +
                    ", y=" + y +
                    ", idx=" + idx +
                    ", d=" + d +
                    ", isDead=" + isDead +
                    '}';
        }
    }

    static class Smell{
        int idx, t;

        public Smell(int idx, int t) {
            this.idx = idx;
            this.t = t;
        }

        @Override
        public String toString() {
            return "Smell{" +
                    "idx=" + idx +
                    ", t=" + t +
                    '}';
        }
    }
}
