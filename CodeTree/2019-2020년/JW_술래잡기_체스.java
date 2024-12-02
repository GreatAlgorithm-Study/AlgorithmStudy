import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JW_술래잡기_체스 {

    static int[] dy = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };
    static int[] dx = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
    static int max = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int[][][] board = new int[4][4][2];
        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int p = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                board[i][j] = new int[] { p, d };
            }
        }
        policeMove(0, 0, 0, board);
        System.out.println(max);
    }

    // 경찰 이동(재귀)
    private static void policeMove(int sum, int y, int x, int[][][] board) {
        sum += board[y][x][0];      // 해당 좌표의 값을 합
        max = Math.max(max, sum);   // 최댓값 갱신
        board[y][x][0] = 0;         // 해당 좌표의 값을 초기화
        int d = board[y][x][1];     // 해당 좌표의 방향
        doDookMove(y, x, board);    // 도둑 이동
        // 경찰이 이동할 수 있는 방향을 모두 재귀적으로 탐색
        for (int i = 1; i <= 3; i++) {
            int ny = y + dy[d] * i, nx = x + dx[d] * i;
            // 이동할 수 있다면
            if (isValid(ny, nx) && board[ny][nx][0] != 0) {
                int[][][] nextBoard = copyBoard(board);
                policeMove(sum, ny, nx, nextBoard); // 재귀 호출
            }
        }
    }

    // 도둑 이동
    private static void doDookMove(int py, int px, int[][][] board) {
        // 순서에 따라서 이동
        next: for (int p = 1; p <= 16; p++) {
            for (int y = 0; y < 4; y++)
                for (int x = 0; x < 4; x++)
                    // 순서에 맞는 좌표를 찾았다면
                    if (board[y][x][0] == p) {
                        int d = board[y][x][1];
                        int ny = y + dy[d], nx = x + dx[d];
                        // 이동할 수 있는 좌표가 등장할 때까지 회전
                        while (!isValid(ny, nx) || (ny == py && nx == px)) {
                            d++;
                            d %= 9;
                            if (d == 0)
                                d = 1;
                            ny = y + dy[d];
                            nx = x + dx[d];
                        }
                        swap(y, x, ny, nx, board);  // 스왑
                        board[ny][nx][1] = d;       // 새로운 방향으로 변경
                        continue next; // 발견했다면 다음 순서 진행
                    }
        }
    }

    // 두 좌표에 있는 원소를 스왑해주는 함수
    private static void swap(int y, int x, int ny, int nx, int[][][] board) {
        int[] temp = board[y][x];
        board[y][x] = board[ny][nx];
        board[ny][nx] = temp;
    }

    // 기존의 배열을 복사하여 다음 배열을 만들어주는 함수
    private static int[][][] copyBoard(int[][][] board) {
        int[][][] nextBord = new int[4][4][2];
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                nextBord[i][j] = board[i][j].clone();
        return nextBord;
    }

    // 경계 체크 함수
    private static boolean isValid(int y, int x) {
        return 0 <= y && y < 4 && 0 <= x && x < 4;
    }
}