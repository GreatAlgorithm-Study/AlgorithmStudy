import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SB_2955 {
    static int[][] board = new int[9][9];
    static boolean[][] row = new boolean[9][10];
    static boolean[][] col = new boolean[9][10];
    static boolean[][] box = new boolean[9][10];


    private static boolean crossHatching(int num) {
        boolean flag = false;
        for (int i = 0; i < 9; i++) {               // 가로줄 놓을 수 있는지 확인
            int cr = -1, cc = -1, cnt = 0;
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0 && !row[i][num] && !col[j][num] && !box[boxIdx(i, j)][num]) {
                    cr = i;
                    cc = j;
                    cnt++;
                }
            }
            if (cnt == 1) {
                placeNumber(cr, cc, num);
                flag = true;
            }
        }

        for (int j = 0; j < 9; j++) {               // 세로줄 놓을 수 있는지 확인
            int cr = -1, cc = -1, cnt = 0;
            for (int i = 0; i < 9; i++) {
                if (board[i][j] == 0 && !row[i][num] && !col[j][num] && !box[boxIdx(i, j)][num]) {
                    cr = i;
                    cc = j;
                    cnt++;
                }
            }
            if (cnt == 1) {
                placeNumber(cr, cc, num);
                flag = true;
            }
        }

        for (int idx = 0; idx < 9; idx++) {             // 박스 놓을 수 있는지 확인
            int cr = -1, cc = -1, cnt = 0;
            int sr = (idx / 3) * 3, sc = (idx % 3) * 3;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    int r = sr + i;
                    int c = sc + j;
                    if (board[r][c] == 0 && !row[r][num] && !col[c][num] && !box[idx][num]) {
                        cr = r;
                        cc = c;
                        cnt++;
                    }
                }
            }
            if (cnt == 1) {
                placeNumber(cr, cc, num);
                flag = true;
            }
        }
        return flag;
    }

    private static void placeNumber(int r, int c, int num) {
        board[r][c] = num;
        row[r][num] = true;
        col[c][num] = true;
        box[boxIdx(r, c)][num] = true;
    }

    private static int boxIdx(int i, int j) {
        return (i / 3) * 3 + j / 3;
    }

    private static void printBoard() {
        StringBuilder sb = new StringBuilder();
        for (int[] row : board) {
            for (int c : row) {
                sb.append(c == 0 ? "." : c);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 9; i++) {
            String line = br.readLine();
            for (int j = 0; j < 9; j++) {
                int num = line.charAt(j) == '.' ? 0 : line.charAt(j) - '0';
                board[i][j] = num;
                if (num != 0) {
                    if (row[i][num] || col[j][num] || box[boxIdx(i, j)][num]) {       // 입력값이 잘못됐을 경우 에러
                        System.out.println("ERROR");
                        return;
                    }
                    row[i][num] = true;
                    col[j][num] = true;
                    box[boxIdx(i, j)][num] = true;
                }
            }
        }

        boolean doTry = false;          // 첫 시도 체크 변수
        boolean updated = true;         // 숫자 적은거 있는지 체크
        while (updated) {
            updated = false;
            for (int num = 1; num <= 9; num++) {
                boolean res = crossHatching(num);
                updated |= res;
                doTry |= res;
            }
        }

        if (!doTry){        // 아예 시도조차 못할 경우 에러
            System.out.println("ERROR");
            return;
        }

        printBoard();
    }
}