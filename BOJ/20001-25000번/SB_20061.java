import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SB_20061 {
    static boolean[][] bBoard = new boolean[4][6];
    static boolean[][] gBoard = new boolean[6][4];
    static int ans = 0;

    private static void putBlueBlock(Block b) {
        // 행 고정, 열 움직이기
        int targetCol = -1;
        for (int c = 0; c < 6; c++) {
            boolean canPlace = true;
            for (int i = 0; i < b.sr; i++) {        // 블럭의 세로 크기만큼 확인
                for (int j = 0; j < b.sc; j++) {    // 블럭의 가로 크기만큼 확인
                    if (c + j > 5 || bBoard[b.r + i][c + j]) {      // 블럭을 놓을 수 없을 경우(범위 벗어나거나 겹침)
                        canPlace = false;
                        break;
                    }
                }
                if (!canPlace) break;               // 현재 행 불가능이면 끝
            }

            if (canPlace) targetCol = c;            // 현재 위치가 블록을 놓을 수 있으면 targetCol 최신 현재 위치로 업데이트
            else break;                             // 블록을 더이상 못놓으면 탈출
        }

        // 블록을 targetCol에 놓기
        if (targetCol != -1) {
            for (int i = 0; i < b.sr; i++) {
                for (int j = 0; j < b.sc; j++) {
                    bBoard[b.r + i][targetCol + j] = true;
                }
            }
        }
    }

    private static void putGreenBlock(Block b) {
        // 열 고정, 행 움직이기
        int targetRow = -1;
        for (int r = 0; r < 6; r++) {
            boolean canPlace = true;
            for (int i = 0; i < b.sr; i++) {
                for (int j = 0; j < b.sc; j++) {
                    if (r + i > 5 || gBoard[r + i][b.c + j]) {
                        canPlace = false;
                        break;
                    }
                }
                if (!canPlace) break;
            }
            if (canPlace) targetRow = r;
            else break;
        }

        if (targetRow != -1) {
            for (int i = 0; i < b.sr; i++) {
                for (int j = 0; j < b.sc; j++) {
                    gBoard[targetRow + i][b.c + j] = true;
                }
            }
        }
    }

    private static int removeBlock() {     // 꽉찬 행, 열 터치기
        int score = 0;

        // 파란보드 (열 지우기)
        for (int c = 5; c >= 0; c--) {
            if (isColFull(c)) {
                removeCol(c);
                c++;            // 열 제거후 이전 열이 지워진 열로 이동하기에, 같은열 다시 확인
                score++;
            }
        }

        // 초록보드 (행 지우기)
        for (int r = 5; r >= 0; r--) {
            if (isRowFull(r)) {
                removeRow(r);
                r++;
                score++;
            }
        }

        return score;
    }

    private static boolean isColFull(int c) {
        for (int r = 0; r < 4; r++) {
            if (!bBoard[r][c]) return false;
        }
        return true;
    }

    private static void removeCol(int c) {
        // 터질 열을 기준으로 왼쪽에서 한열씩 이동
        for (int j = c; j >0; j--) {
            for (int i = 0; i < 4; i++) {
                bBoard[i][j] = bBoard[i][j-1];
            }
        }

        // 제일 끝 열(0열) 새 열로 초기화
        for (int r = 0; r < 4; r++) {
            bBoard[r][0] = false;
        }
    }


    private static boolean isRowFull(int r) {
        for (int c = 0; c < 4; c++) {
            if (!gBoard[r][c]) return false;
        }
        return true;
    }

    private static void removeRow(int r) {
        // 터질 행 위에서 아래로 한 행씩 이동
        for (int i = r; i > 0; i--) {
            for (int j = 0; j < 4; j++) {
                gBoard[i][j] = gBoard[i - 1][j];
            }
        }

        // 제일 처음 행(0행) 초기화
        for (int c = 0; c < 4; c++) {
            gBoard[0][c] = false;
        }
    }

    private static void pushLight() {
        // 연한 파랑 처리
        int cnt1 = 0;
        for (int c = 0; c < 2; c++) {
            for (int r = 0; r < 4; r++) {
                if (bBoard[r][c]) {
                    cnt1++;
                    break;
                }
            }
        }

        while (cnt1-- > 0) {
            removeCol(5);
        }


        // 연한 초록 처리
        int cnt2 = 0;
        for (int r = 0; r < 2; r++) {
            for (int c = 0; c < 4; c++) {
                if (gBoard[r][c]) {
                    cnt2++;
                    break;
                }
            }
        }

        while (cnt2-- > 0) {
            removeRow(5);
        }
    }

    private static int calBlock() {
        int cnt = 0;

        // 파란 보드 값 세기
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                if(bBoard[i][j]) cnt++;
            }
        }

        // 초록 보드 값 세기
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if(gBoard[i][j]) cnt++;
            }
        }
        return cnt;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            Block block = new Block(x, y, t);

            // 블럭 놓기
            putBlueBlock(block);
            putGreenBlock(block);

            // 타일 가득찬 블록 없애기
             ans += removeBlock();

            // 연한 칸 블록 처리
            pushLight();
        }

        // 점수 계산
        sb.append(ans).append('\n').append(calBlock());
        System.out.println(sb);
    }

    static private void print(boolean[][] board) {
        StringBuilder sb = new StringBuilder();
        for (boolean[] b : board) {
            sb.append(Arrays.toString(b)).append('\n');
        }
        System.out.println(sb);
    }

    static class Block{
        int r, c, sr, sc;       // 좌표 위치, 블럭 사이즈

        public Block(int r, int c, int type) {
            this.r = r;
            this.c = c;
            switch (type) {
                case 1:
                    this.sr = 1;
                    this.sc = 1;
                    break;
                case 2:
                    this.sr = 1;
                    this.sc = 2;
                    break;
                case 3:
                    this.sr = 2;
                    this.sc = 1;
                    break;
            }
        }

        @Override
        public String toString() {
            return "Block{" +
                    "r=" + r +
                    ", c=" + c +
                    ", sr=" + sr +
                    ", sc=" + sc +
                    '}';
        }
    }
}
