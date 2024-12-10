import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SB_술래잡기_체스 {
    static int mx = -1;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};

    private static int[][] copyBoard(int[][] board) {
        int[][] tmp = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tmp[i][j] = board[i][j];
            }
        }
        return tmp;
    }

    private static List<Piece> copyPiece(List<Piece> pieces) {
        List<Piece> tmpList = new ArrayList<>();
        for (Piece p : pieces) {
            tmpList.add(new Piece(p.idx, p.x, p.y, p.dir, p.isDie));
        }
        return tmpList;
    }
    private static void dfs(Tagger it, int[][] board, List<Piece> pieces) {
        // 도둑말들 움직이기
        moveThief(board, pieces);

        // 현재 방향으로 잡을 수 있는 말 잡기(최대 3번 이동 가능)
        for (int i = 1; i < 4; i++) {
            int nx = it.x + dx[it.dir] * i;
            int ny = it.y + dy[it.dir] * i;

            if (isValid(nx, ny) && board[nx][ny] != 0) {
                int[][] tmpBoard = copyBoard(board);
                List<Piece> tmpPiece = copyPiece(pieces);

                tmpBoard[it.x][it.y] = 0;
                Piece thief = tmpPiece.get(board[nx][ny]);
                Tagger nit = new Tagger(thief.x, thief.y, thief.dir, it.cnt + thief.idx);
                thief.isDie = true;
                tmpBoard[thief.x][thief.y] = -1;

                dfs(nit, tmpBoard, tmpPiece);
            }
        }
        mx = Math.max(mx, it.cnt);
    }


    private static void moveThief(int[][] board, List<Piece> pieces) {
        for (Piece cur : pieces) {
            if (cur.idx == 0 || cur.isDie) continue;            // 죽었거나 술래면 스킵
            for (int d = 0; d < 8; d++) {
                int nd = (cur.dir + d) % 8;
                int nx = cur.x + dx[nd];
                int ny = cur.y + dy[nd];
                if (!isValid(nx, ny) || board[nx][ny] == -1) continue;
                board[cur.x][cur.y] = 0;
                if (board[nx][ny] == 0) cur.setPos(nx, ny);     // 빈칸이면 바로 이동
                else {                                          // 다른말있으면 교환
                    Piece target = pieces.get(board[nx][ny]);
                    target.setPos(cur.x, cur.y);
                    board[cur.x][cur.y] = target.idx;
                    cur.setPos(nx, ny);
                }
                board[nx][ny] = cur.idx;                        // 움직일 보드에 현재 말 놓기
                cur.dir = nd;                                   // 현재 말의 방향 갱신
                break;
            }
        }
    }

    private static boolean isValid(int x, int y) {
        return 0 <= x && x < 4 && 0 <= y && y < 4;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] board = new int[4][4];                       // 말들의 번호로 저장
        List<Piece> pieces = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken()) - 1;
                Piece piece = new Piece(num, i, j, dir, false);
                pieces.add(piece);
                board[i][j] = num;
            }
        }

        // 도둑말 인덱스 순으로 정렬
        pieces.add(new Piece(0, -1, -1, 0, false));      // 인덱스 0맞추기 위해 더미 객체 넣기
        pieces.sort(((o1, o2) -> o1.idx - o2.idx));

        // 초기 0,0 도둑말 잡기
        Piece first = pieces.get(board[0][0]);
        Tagger tagger = new Tagger(0, 0, first.dir, first.idx);
        board[0][0] = -1;
        first.isDie = true;

        // 술래말의 재귀 시작
        dfs(tagger, board, pieces);

        System.out.println(mx);
    }

    static class Tagger{
        int x, y, dir, cnt;

        public Tagger(int x, int y, int dir, int cnt) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cnt = cnt;
        }
    }

    static class Piece{
        int idx, x, y, dir;
        boolean isDie;

        public Piece(int idx, int x, int y, int dir, boolean isDie) {
            this.idx = idx;
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.isDie = isDie;
        }

        private void setPos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
