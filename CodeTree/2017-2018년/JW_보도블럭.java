import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JW_보도블럭 {

    static int n, len;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        len = Integer.parseInt(st.nextToken());
        int[][] board = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++)
                board[i][j] = Integer.parseInt(st.nextToken());
        }
        int cnt = 0;
        // 행 체크
        for (int i = 0; i < n; i++) {
            int[] line = new int[n];
            for (int j = 0; j < n; j++)
                line[j] = board[i][j];
            if (isPossible(line))
                cnt++;
        }
        // 열 체크
        for (int i = 0; i < n; i++) {
            int[] line = new int[n];
            for (int j = 0; j < n; j++)
                line[j] = board[j][i];
            if (isPossible(line))
                cnt++;
        }
        System.out.println(cnt);
    }

    // 해당 배열에 보도블럭을 설치할 수 있는지 확인하는 함수
    private static boolean isPossible(int[] line) {
        // 보도블럭을 사용하면 방문 처리하기 위한 배열
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n - 1; i++) {
            int diff = line[i] - line[i + 1];
            // 높이가 1을 초과하게 되면 설치할 수 없음
            if (Math.abs(diff) > 1)
                return false;
            if (diff == 1) {
                int s = i + 1;
                int lv = line[s];
                for (int j = 0; j < len; j++) {
                    // 유효한 범위 / 보도블럭 사용하지 않음 / 같은 높이인지를 체크
                    if (!isValid(s + j) || visited[s + j] || line[s + j] != lv)
                        return false;
                    visited[s + j] = true; // 방문 처리
                }
            } else if (diff == -1) {
                int s = i;
                int lv = line[s];
                for (int j = 0; j < len; j++) {
                    if (!isValid(s - j) || visited[s - j] || line[s - j] != lv)
                        return false;
                    visited[s - j] = true;
                }
            }
        }
        return true;
    }

    // 경계 체크
    private static boolean isValid(int x) {
        return 0 <= x && x < n;
    }
}