import java.io.BufferedReader;
import java.io.InputStreamReader;

public class JW_1992 {

    static char[][] board;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        board = new char[n][n];
        for (int i = 0; i < n; i++)
            board[i] = br.readLine().toCharArray();
        // 압축
        quardTree(n, 0, 0);
        System.out.println(sb);
    }

    // 분할 정복, 재귀를 통한 압축
    private static void quardTree(int n, int y, int x) {
        // 압축이 가능하다면 압축
        if (isPossible(n, y, x)) {
            sb.append(board[y][x]);
        // 압축이 불가능하다면 분할 정복으로 압축 시도
        } else {
            sb.append("(");
            quardTree(n / 2, y, x);
            quardTree(n / 2, y, x + n / 2);
            quardTree(n / 2, y + n / 2, x);
            quardTree(n / 2, y + n / 2, x + n / 2);
            sb.append(")");
        }
    }

    // 모두 같은 값을 가지는지 확인할 함수
    private static boolean isPossible(int n, int y, int x) {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (board[y][x] != board[y + i][x + j])
                    return false;
        return true;
    }
}