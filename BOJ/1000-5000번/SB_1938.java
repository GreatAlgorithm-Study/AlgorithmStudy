import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SB_1938 {
    static int N;
    static int[][] board;
    static int[][][] visited;
    static Node target;
    static int[] dx = {-1, 1, 0, 0, 0};
    static int[] dy = {0, 0, -1, 1, 0};

    private static int bfs(Node start) {
        Queue<Node> que = new ArrayDeque<>();
        que.offer(start);
        visited[start.x][start.y][start.ty] = 0;

        while (!que.isEmpty()) {
            Node cur = que.poll();
            if (cur.x==target.x && cur.y== target.y && cur.ty== target.ty) return visited[cur.x][cur.y][cur.ty];

            for (int i = 0; i < 5; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (!isValid(nx, ny) || board[nx][ny]==1) continue;     // 중심좌표가 범위 벗어나면 패쓰

                if (i==4) {     // 회전할때 경우
                    int nt = cur.ty ^ 1;
                    if(canTurn(nx, ny, nt) && visited[nx][ny][nt]==-1) {
                        que.offer(new Node(nx, ny, nt));
                        visited[nx][ny][nt] = visited[cur.x][cur.y][cur.ty] + 1;
                    }
                    continue;
                }

                // 상하좌우로 움직인 nx,ny 값에 대해 가로,세로일때 각 양날개가 가능한지 체크
                if (cur.ty == 0 && canWingRow(nx, ny) && visited[nx][ny][cur.ty]==-1) {
                    que.offer(new Node(nx, ny, cur.ty));
                    visited[nx][ny][cur.ty] = visited[cur.x][cur.y][cur.ty] + 1;
                } else if (cur.ty == 1 && canWingCol(nx, ny) && visited[nx][ny][cur.ty]==-1) {
                    que.offer(new Node(nx, ny, cur.ty));
                    visited[nx][ny][cur.ty] = visited[cur.x][cur.y][cur.ty] + 1;
                }
            }
        }
        return 0;
    }

    private static boolean canWingRow(int r, int c) {       // 가로일때 날개 유효한지
        return isValid(r, c-1) && isValid(r, c+1) && board[r][c - 1] == 0 && board[r][c + 1] == 0;
    }

    private static boolean canWingCol(int r, int c) {       // 세로일때 날개 유효한지
        return isValid(r-1, c) && isValid(r+1, c) && board[r-1][c] == 0 && board[r+1][c] == 0;
    }

    private static boolean canTurn(int r, int c, int t) {
        for (int i = r - 1; i <= r + 1; i++) {
            for (int j = c - 1; j <= c + 1; j++) {
                if (!isValid(i, j) || board[i][j]==1) return false;
            }
        }
        return true;
    }

    private static boolean isValid(int x, int y) {         // 좌표 유효성 검사
        return 0<=x && x<N && 0<=y && y<N;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        visited = new int[N][N][2];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < 2; k++) {
                    visited[i][j][k] = -1;
                }
            }
        }

        List<int[]> b = new ArrayList<>();
        List<int[]> e = new ArrayList<>();
        for (int i=0; i<N; i++){
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                char c = line.charAt(j);
                if (Character.isDigit(c)) {
                    board[i][j] = c - '0';
                    continue;
                }
                if (c=='B') b.add(new int[]{i, j});
                else if (c=='E') e.add(new int[]{i, j});
                board[i][j] = 0;
            }
        }

        Collections.sort(b, (o1, o2) -> {
            if (o1[0] != o2[0]) return Integer.compare(o1[0], o2[0]);
            return Integer.compare(o1[1], o2[1]);
        });

        Collections.sort(e, (o1, o2) -> {
            if (o1[0] != o2[0]) return Integer.compare(o1[0], o2[0]);
            return Integer.compare(o1[1], o2[1]);
        });

        // 처음 중심 좌표 및 형태
        int tb = (b.get(0)[0] == b.get(1)[0]) ? 0 : 1; // 가로(0), 세로(1)
        int te = (e.get(0)[0] == e.get(1)[0]) ? 0 : 1;
        Node start = new Node(b.get(1)[0], b.get(1)[1], tb);   // 중간값이 중심값
        target = new Node(e.get(1)[0], e.get(1)[1], te);

        System.out.println(bfs(start));
    }

    static class Node{
        int x, y, ty;

        public Node(int x, int y, int ty) {
            this.x = x;
            this.y = y;
            this.ty = ty;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    ", ty=" + ty +
                    '}';
        }
    }

}
