import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JW_시공의_돌풍 {

    static int n, m, t, p; // 세로, 가로, 시간, 시공의 돌풍의 위치
    // 윗 부분 회전에 맞추기 위해
    // 우 -> 상 -> 좌 -> 하
    // 순으로 변화량을 저장
    static int[] dy = { 0, -1, 0, 1 };
    static int[] dx = { 1, 0, -1, 0 };
    static int[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++)
                board[i][j] = Integer.parseInt(st.nextToken());
            // 마지막 시공의 돌풍의 y좌표
            if (board[i][0] == -1)
                p = i;
        }
        // 시간만큼 시뮬레이션
        while (t-- > 0) {
            spread();           // 확산
            rotate(p - 1, 1);   // 윗 부분 회전
            rotate(p    , -1);  // 아랫 부분 회전
        }
        System.out.println(calculate()); // 방 안의 먼지의 총합 계산
    }

    private static void spread() {
        int[][] tempBoard = new int[n][m]; // 확산되는 양을 저장할 새로운 배열
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (board[i][j] > 0) {
                    int amount = board[i][j] / 5; // 확산될 양
                    for (int k = 0; k < 4; k++) {
                        int y = i + dy[k];
                        int x = j + dx[k];
                        // 유효한 좌표라면 확산
                        if (isValid(y, x) && board[y][x] != -1) {
                            tempBoard[i][j] -= amount; // 확산시킨 곳의 양은 감소
                            tempBoard[y][x] += amount; // 확산된 곳의 양은 증가
                        }
                    }
                }
        // 원래 방에서 확산으로 인한 변화량을 더해줌
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                board[i][j] += tempBoard[i][j];
    }

    /*
     * @param s: 시공의 돌풍의 y좌표
     * @param mode: 윗 회전인지 아랫 회전인지 알려주는 변수
     * 
     * mode에 따라서 회전하는 방향이 결정됨
     */
    private static void rotate(int s, int mode) {
        int prev = 0; // 이전 값
        int dir = 0;
        int y = s, x = 1;
        // 시공의 돌풍으로 돌아올 때까지 반복
        while (!(y == s && x == 0)) {
            int temp = board[y][x]; // 원래 값 기억
            board[y][x] = prev;     // 다음 좌표를 이전 값으로 갱신
            prev = temp;            // 이전 값을 원래 값으로 갱신
            int ny = y + dy[dir];
            int nx = x + dx[dir];
            // 다음 좌표가 경계를 벗어난다면
            if (!isValid(ny, nx)) {
                // 방향 재설정
                dir = (dir + mode + 4) % 4;
                ny = y + dy[dir];
                nx = x + dx[dir];
            }
            // 다음 좌표 결정
            y = ny;
            x = nx;
        }
    }

    // 방 안의 먼지의 총합을 계산
    private static int calculate() {
        // 시공의 돌풍이 -1이므로 전처리
        int sum = 2;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                sum += board[i][j];
        return sum;
    }

    // 경계 체크
    private static boolean isValid(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < m;
    }
}