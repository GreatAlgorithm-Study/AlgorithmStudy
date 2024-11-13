import java.io.*;

public class HW_2955 {
    static int[][] board;
    public static boolean[] row, col;
    public static boolean[][] square;
    static boolean error = false;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        board = new int[9][9];
        for (int i = 0; i < 9; i++) {
            String line = br.readLine();
            for (int j = 0; j < 9; j++) {
                if (line.charAt(j) == '.') {
                    board[i][j] = 0;
                } else board[i][j] = line.charAt(j) - '0';
            }
        }

        while (true) {
            if (!solve()) {
                break;
            }
        }

        if (error) {
            System.out.println("ERROR");
        } else {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if(board[i][j] == 0){
                        System.out.print(".");
                    } else System.out.print(board[i][j]);
                }
                System.out.println();
            }
        }
    }

    public static boolean solve() {
        for (int i = 1; i <= 9; i++) {
            if (crossHatching(i)) {
                return true;
            }
        }
        return false;
    }

    public static boolean crossHatching(int x) {
        row = new boolean[9];
        col = new boolean[9];
        square = new boolean[3][3];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == x) {
                    if (row[i] || col[j] || square[i / 3][j / 3]) {
                        error = true;
                        return false;
                    }
                    row[i] = true;
                    col[j] = true;
                    square[i / 3][j / 3] = true;
                }
            }
        }
        boolean flag = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!square[i][j]) {
                    if (check(i * 3, j * 3, x)) {
                        flag = true;
                    }
                }
            }
        }
        return flag;
    }

    public static boolean check(int curX, int curY, int x) { // x : 배치하려는 숫자
        int nx = -1;
        int ny = -1;
        int cnt = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[curX + i][curY + j] == 0 && !row[curX + i] && !col[curY + j]) {
                    if (cnt == 0) {
                        nx = curX + i;
                        ny = curY + j;
                        cnt++;
                    } else {
                        return false;
                    }
                }
            }
        }

        if (cnt == 0) {
            error = true;
            return false;
        }

        board[nx][ny] = x;
        row[nx] = true;
        col[ny] = true;

        return true;
    }
}