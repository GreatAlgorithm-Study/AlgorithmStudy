import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static int n;               
    static int[][] board;       // 격자 칸
    static int[][] opers;       // 각 년도의 이동 규칙
    static boolean[][] supple;  // 영양제가 있는 위치

    // 대각선 움직임 구현
    static int[] dy = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
    static int[] dx = { 0, 1, 1, 0, -1, -1, -1, 0, 1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        board = new int[n][n];
        supple = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++)
                board[i][j] = Integer.parseInt(st.nextToken());
        }
        opers = new int[m][2]; // { 방향, 크기 }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            opers[i][0] = Integer.parseInt(st.nextToken());
            opers[i][1] = Integer.parseInt(st.nextToken());
        }
        // 첫 영양제 위치
        supple[n - 2][0] = true;
        supple[n - 2][1] = true;
        supple[n - 1][0] = true;
        supple[n - 1][1] = true;
        // 년도만큼 반복
        for (int year = 0; year < m; year++) {
            move(year); // 영양제의 움직임 구현
            grow();     // 영양제 위치의 나무 성장
            cut();      // 크기가 2 이상인 나무를 잘라내고 영양제를 심음
        }
        // 남아있는 나무 높이들의 총 합
        int sum = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                sum += board[i][j];
        System.out.println(sum);
    }

    // 영양제의 움직임 구현
    private static void move(int year) {
        // 큐를 이용해 기존 영양제의 위치를 저장
        Deque<int[]> dq = new ArrayDeque<>();
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (supple[i][j]) {
                    supple[i][j] = false;
                    dq.offer(new int[] { i, j });
                }
        // 년도에 따른 이동 방향 및 크기
        int[] oper = opers[year];
        while (!dq.isEmpty()) {
            int[] cur = dq.poll();
            // 영양제 위치 이동
            int y = (cur[0] + dy[oper[0]] * oper[1] + n) % n;
            int x = (cur[1] + dx[oper[0]] * oper[1] + n) % n;
            supple[y][x] = true;    // 이동한 영양제 위치 표시
            board[y][x]++;          // 영양제가 있는 위치의 나무 성장
        }
    }

    // 영양제 위치의 나무 성장
    private static void grow() {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                // 영양제가 있다면
                if (supple[i][j]) {
                    int cnt = 0;
                    for (int k = 1; k <= 4; k++) {
                        // 대각선 벡터값
                        int y = i + dy[k * 2];
                        int x = j + dx[k * 2];
                        if (isValid(y, x) && board[y][x] > 0)
                            cnt++;
                    }
                    // 대각선에 있는 나무의 수 만큼 성장
                    board[i][j] += cnt;
                }
    }

    // 크기가 2 이상인 나무를 잘라내고 영양제를 심음
    private static void cut() {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                // 영양제가 없었던 좌표에서 크기가 2 이상인 나무
                if (!supple[i][j]) {
                    if (board[i][j] >= 2) {
                        board[i][j] -= 2;
                        supple[i][j] = true;
                    }
                } else {
                    // 기존에 있던 영양제는 없애줌
                    supple[i][j] = false;
                }
    }

    private static boolean isValid(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < n;
    }
}