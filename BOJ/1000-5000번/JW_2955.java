import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class JW_2955 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] board = new int[9][9];
        for (int i = 0; i < 9; i++) {
            String line = br.readLine();
            for (int j = 0; j < 9; j++)
                if (Character.isDigit(line.charAt(j))) {
                    board[i][j] = line.charAt(j) - '0';
                }
        }
        // 갱신되는 숫자가 없을 때까지 반복
        boolean playFlag = true;
        while (playFlag) {
            playFlag = false;
            // 각 숫자가 들어갈 수 있는 자리를 체크
            for (int num = 1; num <= 9; num++) {
                // crossHatching을 이용하여 target이 들어갈 수 있는 자리들을 확인
                boolean[][] checkBoard = crossHatching(board, num);
                // 잘못된 경우라면 에러 출력
                if (!isValid(num, board, checkBoard)) {
                    System.out.println("ERROR");
                    return;
                }
                // 각 서브 그리드 안에 숫자를 넣을 수 있는지 확인
                for (int i = 0; i < 9; i++)
                    if (putNumber(num, board, i, checkBoard))
                        playFlag = true; // 넣을 수 있다면 반복
            }
        }
        System.out.println(printBoard(board));
    }

    private static boolean[][] crossHatching(int[][] board, int target) {
        boolean[][] checkBoard = new boolean[9][9];
        // 1. 이미 숫자가 존재함
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                if (board[i][j] != 0)
                    checkBoard[i][j] = true;
        // 2. 타겟과 같은 숫자의 위치
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                if (board[i][j] == target) {
                    check(checkBoard, i, j);
                }
        return checkBoard;
    }

    // 타겟(숫자)이 들어갈 수 있는지 확인하고 들어갈 자리가 한 곳이라면 삽입
    private static boolean putNumber(int target, int[][] board, int boardNum, boolean[][] checkBoard) {
        ArrayList<int[]> arr = new ArrayList<>();
        int sy = (boardNum / 3) * 3, sx = (boardNum % 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int ny = sy + i;
                int nx = sx + j;
                if (!checkBoard[ny][nx])
                    arr.add(new int[] { ny, nx });
            }
        }
        if (arr.size() == 1) {
            int y = arr.get(0)[0];
            int x = arr.get(0)[1];
            board[y][x] = target;
            check(checkBoard, y, x);
            return true;
        }
        return false;
    }
    
    // 해당 자리 기준으로 상하좌우, 서브그리드 체크
    private static void check(boolean[][] checkBoard, int y, int x) {
        int sy = (y / 3) * 3;
        int sx = (x / 3) * 3;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                checkBoard[sy + i][sx + j] = true;
            }
        for (int i = 0; i < 9; i++) {
            checkBoard[y][i] = true;
            checkBoard[i][x] = true;
        }
    }

    // 유효성 체크
    private static boolean isValid(int target, int[][] board, boolean[][] checkBoard) {
        for (int i = 0; i < 9; i++) {
            int putCnt = 0;
            int numCnt = 0;
            int sy = (i / 3) * 3;
            int sx = (i % 3) * 3;
            for (int j = 0; j < 3; j++)
                for (int k = 0; k < 3; k++) {
                    int ny = sy + j, nx = sx + k;
                    if (board[ny][nx] == target)
                        numCnt++;
                    if (!checkBoard[ny][nx])
                        putCnt++;
                }
            // 서브그리드에 타겟과 동일한 숫자가 2개, 들어갈 자리가 없는데 타겟이 없을 경우 예외
            if (numCnt > 1 || putCnt == 0 && numCnt == 0)
                return false;
        }
        return true;
    }

    // 스도쿠 출력
    private static String printBoard(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++)
                if (board[i][j] == 0)
                    sb.append(".");
                else
                    sb.append(board[i][j]);
            sb.append("\n");
        }
        return sb.toString();
    }
}