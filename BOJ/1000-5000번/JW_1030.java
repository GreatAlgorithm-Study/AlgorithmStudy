import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JW_1030 {

    static int s, n, k, r1, r2, c1, c2;
    static boolean[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        r1 = Integer.parseInt(st.nextToken());
        r2 = Integer.parseInt(st.nextToken());
        c1 = Integer.parseInt(st.nextToken());
        c2 = Integer.parseInt(st.nextToken());
        board = new boolean[r2 - r1 + 1][c2 - c1 + 1];
        makeBoard(s, 0, 0, false);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++)
                if (board[i][j])
                    sb.append("1");
                else
                    sb.append("0");
            sb.append("\n");
        }
        System.out.println(sb);
    }

    // 분할 정복으로 검은색 타일 채우기
    private static void makeBoard(int depth, int y, int x, boolean tile) {
        int len = (int) Math.pow(n, depth); // 그리드의 길이
        // 확인하지 않아도 되는 그리드라면 스킵
        if (isNotOverlap(y, x, len)) {
            return;
        }
        // 재귀 종료 조건
        if (depth == 0) {
            board[y - r1][x - c1] = tile; // true = 검은색 타일
            return;
        }
        int sLen = len / n; // 서브그리드의 길이
        int bsy = y + sLen * (n - k) / 2, bsx = x + sLen * (n - k) / 2; // 검은색 타일이 시작하는 좌표
        int bLen = sLen * k; // 검은색 타일의 길이
        // 서브 그리드별로 확인
        for (int i = y; i < y + len; i += sLen) {
            for (int j = x; j < x + len; j += sLen) {
                makeBoard(depth - 1, i, j, tile | isBlack(bsy, bsx, bLen, i, j)); // 다음 재귀 진행, 검은색 타일 체크
            }
        }
    }

    // 유효한 그리드인지 체크
    private static boolean isNotOverlap(int y, int x, int len) {
        return y + len <= r1 || y > r2 || x + len <= c1 || x > c2;
    }

    // 검은색 타일 체크
    private static boolean isBlack(int bsy, int bsx, int bLen, int y, int x) {
        return bsy <= y && y < bsy + bLen && bsx <= x && x < bsx + bLen;
    }
}