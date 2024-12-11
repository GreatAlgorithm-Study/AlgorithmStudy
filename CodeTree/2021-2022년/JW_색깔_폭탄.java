import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class JW_색깔_폭탄 {

    static class Group implements Comparable<Group> {
        int sy, sx;
        ArrayList<int[]> list = new ArrayList<>();  // 그룹에 해당하는 모든 폭탄의 좌표들
        int redCnt = 0;

        // 처음 시작하는 요소 = 기준
        Group(int sy, int sx) {
            this.sy = sy;
            this.sx = sx;
        }

        @Override
        // 우선순위 결정
        public int compareTo(Group o) {
            if (this.list.size() != o.list.size())
                return o.list.size() - this.list.size();
            else if (this.redCnt != o.redCnt)
                return this.redCnt - o.redCnt;
            else if (this.sy != o.sy)
                return o.sy - this.sy;
            return this.sx - o.sx;
        }
    }

    static int n, m;
    static int[][] board;
    static int[] dy = { -1, 1, 0, 0 };
    static int[] dx = { 0, 0, -1, 1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++)
                board[i][j] = Integer.parseInt(st.nextToken());
        }
        int score = 0;  // 최종 점수
        while (true) {
            PriorityQueue<Group> groups = makeGroups(); // 우선순위 큐를 이용해서 우선순위 부여
            // 터트릴 수 없을 경우에 종료
            if (groups.isEmpty() || groups.peek().list.size() == 1)
                break;
            score += Math.pow(groups.peek().list.size(), 2); // 터트리기 전에 점수 계산
            explode(groups.poll()); // 폭발
            pullToDown();           // 중력 작용
            rotate();               // 반시계 회전
            pullToDown();           // 중력 작용
        }
        System.out.println(score);
    }

    private static PriorityQueue<Group> makeGroups() {
        PriorityQueue<Group> groups = new PriorityQueue<>();
        boolean[][] visited = new boolean[n][n];    // 방문 처리
        for (int y = n - 1; y >= 0; y--) {
            for (int x = 0; x < n; x++) {
                // 방문했거나 빨간 폭탄일 경우 건너뜀
                if (visited[y][x] || board[y][x] <= 0)
                    continue;
                boolean[][] redVisited = new boolean[n][n]; // 빨간 폭탄은 매 그룹마다 포함될 수 있으므로 따로 방문 처리
                Group group = new Group(y, x);
                Deque<int[]> dq = new ArrayDeque<>();   // BFS를 위한 덱
                group.list.add(new int[] { y, x });
                dq.offer(new int[] { y, x });
                visited[y][x] = true;
                
                // BFS
                while (!dq.isEmpty()) {
                    int[] cur = dq.poll();
                    for (int k = 0; k < 4; k++) {
                        int ny = cur[0] + dy[k], nx = cur[1] + dx[k];
                        if (isValid(ny, nx) && !visited[ny][nx]) {
                            // 빨간 폭탄일 경우
                            if (board[ny][nx] == 0) {
                                // 방문하지 않은 빨간 폭탄일 경우
                                if (!redVisited[ny][nx]) {
                                    group.list.add(new int[] { ny, nx });
                                    group.redCnt++;
                                    dq.offer(new int[] { ny, nx });
                                    redVisited[ny][nx] = true;
                                }
                            // 같은 색깔의 폭탄일 경우만 
                            } else if (board[ny][nx] == board[y][x]) {
                                group.list.add(new int[] { ny, nx });
                                dq.offer(new int[] { ny, nx });
                                visited[ny][nx] = true;
                            }
                        }
                    }
                }
                groups.offer(group);
            }
        }
        return groups;
    }

    // 폭발 함수 
    private static void explode(Group group) {
        ArrayList<int[]> list = group.list;
        for (int[] bomb : list) {
            board[bomb[0]][bomb[1]] = -2; // 빈 공간으로 변경
        }
    }

    // 빈 공간이 있을 경우 아래로 당김
    private static void pullToDown() {
        for (int y = n - 1; y > 0; y--)
            next: for (int x = 0; x < n; x++)
                if (board[y][x] == -2) {
                    for (int ny = y - 1; ny >= 0; ny--) {
                        // 빈 공간이 아니고 돌이 아닌 다른 수가 나올때까지
                        if (board[ny][x] != -2) {
                            if (board[ny][x] != -1)
                                swap(y, x, ny, x);  // 스왑
                            // 스왑을 했다면 다음 빈 공간 탐색
                            continue next;
                        }
                    }
                }
    }

    // 반 시계 방향 회전
    private static void rotate() {
        int[][] next = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                next[n - 1 - j][i] = board[i][j];
        board = next;
    }

    // 배열의 요소를 바꾸는 스왑
    private static void swap(int y, int x, int ny, int nx) {
        int temp = board[y][x];
        board[y][x] = board[ny][nx];
        board[ny][nx] = temp;
    }

    // 경계 체크
    private static boolean isValid(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < n;
    }
}