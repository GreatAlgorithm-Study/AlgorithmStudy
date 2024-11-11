import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SB_회전하는_빙하 {
    static int N, Q, M;
    static int[][] board;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int area = 0;
    static int mx = 0;
    private static void bfs(int x, int y) {
        Queue<Node> que = new ArrayDeque<>();
        que.offer(new Node(x, y));
        visited[x][y] = true;

        int size = 0;
        while (!que.isEmpty()) {
            Node cur = que.poll();
            area += board[cur.x][cur.y];
            size++;

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (!isValid(nx, ny) || visited[nx][ny] || board[nx][ny]==0) continue;
                que.offer(new Node(nx, ny));
                visited[nx][ny] = true;
            }
        }
        mx = Math.max(mx, size);
    }

    private static void calIceGroup() {
        visited = new boolean[M][M];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && board[i][j] > 0) {
                    bfs(i, j);
                }
            }
        }
    }
    private static void meltIce() {
        boolean[][] check = new boolean[M][M];

        // 녹을 빙하 체크
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j]==0) continue;       // 얼음이 아닌 곳은 패쓰
                int cnt = 0;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if (isValid(nx, ny) && board[nx][ny]>0) cnt++;
                }
                if (cnt < 3) check[i][j] = true;
            }
        }

        // 체크된 빙하 녹이기
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                if (check[i][j]) board[i][j]--;
            }
        }
    }

    private static boolean isValid(int x, int y) {
        return 0<=x && x<M && 0<=y && y<M;
    }
    private static void rotateIce(int sx, int sy, int l, int[][] tmp) {         // 레벨 사각형안의 분할된 사각형의 회전
        int s = 1<<l;
        int ss = 1 << (l - 1);

        for (int i = sx; i < sx + s; i+=ss) {                   // 현재 레벨 사각형의 시작 꼭짓점부터 진행
            for (int j = sy; j < sy + s; j+=ss) {
                int ox = i - sx;                                // 0점 맞추기
                int oy = j - sy;
                int rx = oy;                                    // 회전 좌표
                int ry = s-ox-ss;
               for (int x = 0; x < ss; x++) {                   // 분할된 사각형 조각 이동
                    for (int y = 0; y < ss; y++) {
                        int cx = i + x;                         // 원본 배열에서 현재 위치
                        int cy = j + y;
                        int nx = rx + x;                        // 회전된 좌표에서 새로운 위치
                        int ny = ry + y;
                        tmp[nx+sx][ny+sy] = board[cx][cy];      // 회전된 위치엔 보정으로 뺐던 시작값 더해주기
                    }
                }
            }
        }
    }
    private static void rotate(int l) {
        int s = 1<<l;
        // tmp 배열 복사
        int[][] tmp = new int[M][M];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                tmp[i][j] = board[i][j];
            }
        }

        // 큰 사각형 회전 (레벨 사각형)
        for (int i = 0; i < M; i+=s) {
            for (int j = 0; j < M; j+=s) {
                rotateIce(i, j, l, tmp);
            }
        }

        // 회전한 내용 반영
        board = tmp;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        M = 1<<N;
        board = new int[M][M];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 회전 수행
        st = new StringTokenizer(br.readLine());
        while (Q-- > 0) {
            int L = Integer.parseInt(st.nextToken());
            if (L > 0) rotate(L);
            meltIce();      // 얼음 녹기
        }

        // 얼음군집 구하기
        calIceGroup();

        System.out.println(area);
        System.out.println(mx);
    }

    static class Node{
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}