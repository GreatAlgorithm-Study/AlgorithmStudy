import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JW_테트리스_블럭_안의_합_최대화_하기 {

    static int n, m;
    // 도형을 회전, 반전으로 만들 수 있는 모든 경우를 저장
    static int[][][] shapes = { { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 0, 3 } }, { { 0, 0 }, { 1, 0 }, { 2, 0 }, { 3, 0 } },
            { { 0, 0 }, { 1, 0 }, { 0, 1 }, { 1, 1 } }, { { 0, 0 }, { 1, 0 }, { 2, 0 }, { 2, 1 } },
            { { 0, 0 }, { 0, 1 }, { 0, 2 }, { -1, 2 } }, { { 0, 0 }, { 0, 1 }, { 1, 1 }, { 2, 1 } },
            { { 0, 0 }, { 1, 0 }, { 0, 1 }, { 0, 2 } }, { { 0, 0 }, { 1, 0 }, { 2, 0 }, { 2, -1 } },
            { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 2 } }, { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 2, 0 } },
            { { 0, 0 }, { 1, 0 }, { 1, 1 }, { 1, 2 } }, { { 0, 0 }, { 1, 0 }, { 1, 1 }, { 2, 1 } },
            { { 0, 0 }, { 0, 1 }, { -1, 1 }, { -1, 2 } }, { { 0, 0 }, { 1, 0 }, { 1, -1 }, { 2, -1 } },
            { { 0, 0 }, { 0, 1 }, { 1, 1 }, { 1, 2 } }, { { 0, 0 }, { 1, 0 }, { 1, 1 }, { 2, 0 } },
            { { 0, 0 }, { 0, 1 }, { -1, 1 }, { 0, 2 } }, { { 0, 0 }, { 1, 0 }, { 1, -1 }, { 2, 0 } },
            { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 1 } } };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int[][] board = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++)
                board[i][j] = Integer.parseInt(st.nextToken());
        }
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 모든 지점에 대해서 모든 도형을 만들어서 합을 구해줌
                for (int[][] shape : shapes) {
                    int sum = 0;
                    for (int k = 0; k < 4; k++) {
                        int y = i + shape[k][0];
                        int x = j + shape[k][1];
                        if (isValid(y, x))
                            sum += board[y][x];
                    }
                    max = Math.max(max, sum);
                }
            }
        }
        System.out.println(max);
    }

    // 경계 체크
    private static boolean isValid(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < m;
    }
}