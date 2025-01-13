public class SB_131703 {
    static int N, M;
    static int[][] tg;

    private static boolean isSame(int[][] board){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] != tg[i][j]) return false;
            }
        }
        return true;
    }

    private static void flipRow(int[][] board, int r) {         // 행 뒤집기
        for (int c = 0; c < M; c++) {
            board[r][c] ^= 1;
        }
    }
    private static void flipCol(int[][] board, int c) {         // 열 뒤집기
        for (int r = 0; r < N; r++) {
            board[r][c] ^= 1;
        }
    }

    private static int[][] copy(int[][] origin) {
        int[][] board = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                board[i][j] = origin[i][j];
            }
        }
        return board;
    }
    public static int solution(int[][] beginning, int[][] target) {
        N = target.length;
        M = target[0].length;
        tg = copy(target);

        int mn = Integer.MAX_VALUE;

        for (int rowMask = 0; rowMask < (1 << N); rowMask++) {          // 모든 행의 뒤집기 조합
            for (int colMask = 0; colMask < (1 << M); colMask++) {      // 모든 열의 뒤집기 조합
                int[][] tmp = copy(beginning);
                int flip = 0;

                // 뒤집기에 해당하는 행 찾기
                for (int r = 0; r < N; r++) {
                    if ((rowMask & (1<<r)) !=0){
                        flipRow(tmp, r);
                        flip++;
                    }
                }

                // 뒤집기에 해당하는 열 찾기
                for (int c = 0; c < M; c++) {
                    if ((colMask & (1 << c)) != 0) {
                        flipCol(tmp, c);
                        flip++;
                    }
                }

                // 목표한 상태랑 일치하는지 체크
                if (isSame(tmp)) mn = Math.min(mn, flip);
            }
        }
        return (mn==Integer.MAX_VALUE) ? -1 : mn;
    }
}
