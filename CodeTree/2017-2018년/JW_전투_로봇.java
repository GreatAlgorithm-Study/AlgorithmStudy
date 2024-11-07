import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class JW_전투_로봇 {

    static int n;
    static int[][] board;
    static int[] dy = { -1, 1, 0, 0 };
    static int[] dx = { 0, 0, -1, 1 };
    static int level = 2, count = 0, totalMove = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        int sy = 0, sx = 0;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int p = Integer.parseInt(st.nextToken());
                // 초기 로봇 위치 저장
                if (p == 9) {
                    sy = i;
                    sx = j;
                } else if (p != 0) {
                    board[i][j] = p;
                }
            }
        }
        Deque<int[]> dq = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][n];
        dq.offer(new int[] { 0, sy, sx });
        // BFS
        while (!dq.isEmpty()) {
            // 처리할 수 있는 몬스터들의 위치에 따라 우선 순위 부여
            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);
            // 깊이 별로 BFS 진행
            int t = dq.size();
            while (t-- > 0) {
                int[] cur = dq.poll();
                int y = cur[1], x = cur[2];
                if (visited[y][x])
                    continue;
                visited[y][x] = true;
                // 해당 레벨에서 잡을 수 있는 몬스터가 존재한다면 우선 순위 큐에 저장
                if (isCatch(y, x)) {
                    pq.offer(cur);
                    continue;
                }
                for (int i = 0; i < 4; i++) {
                    int ny = y + dy[i], nx = x + dx[i];
                    if (isValid(ny, nx) && !visited[ny][nx]) {
                        dq.offer(new int[] { cur[0] + 1, ny, nx });
                    }
                }
            }
            // 해당 깊이에서 처리할 수 있는 몬스터를 1개 이상 발견했다면
            if (!pq.isEmpty()) {
                // 우선 순위가 가장 높은 몬스터를 처리
                int[] cur = pq.poll();
                totalMove += cur[0];
                int y = cur[1], x = cur[2];
                board[y][x] = 0;
                // 없앤 몬스터의 수 갱신과 레벨 업
                count++;
                if (count == level) {
                    level++;
                    count = 0;
                }
                // 큐를 비우고 해당 위치에서 다시 시작
                dq.clear();
                dq.offer(new int[] { 0, y, x });
                visited = new boolean[n][n];
            }
        }
        System.out.println(totalMove);
    }

    // 몬스터를 없앨 수 있는지 확인하는 함수
    private static boolean isCatch(int y, int x) {
        return 0 < board[y][x] && board[y][x] < level;
    }

    // 경계 체크 함수
    private static boolean isValid(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < n && board[y][x] <= level;
    }
}