import java.io.*;
import java.util.*;

public class HW_22944 {
    static int N, H, D;
    static char[][] board;
    static int[][] check;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class Node {
        int x, y, h, d, m;

        public Node(int x, int y, int h, int d, int m) {
            this.x = x;
            this.y = y;
            this.h = h; // 현재 체력
            this.d = d; // 우산 내구도
            this.m = m; // 이동 횟수
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        board = new char[N][N];
        check = new int[N][N];

        int startX = 0, startY = 0;
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = line.charAt(j);
                if (board[i][j] == 'S') {
                    startX = i;
                    startY = j;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            Arrays.fill(check[i], -1);
        }

        int result = bfs(startX, startY);
        System.out.println(result);
    }

    public static int bfs(int sx, int sy) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(sx, sy, H, 0, 0)); // 시작점 추가
        check[sx][sy] = H; // 시작점 방문 처리

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int newH = cur.h, newD = cur.d, newM = cur.m;
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];


                if (!isValid(nx, ny))
                    continue;

                if (board[nx][ny] == 'E') { // 안전지대에 도달하면 이동 횟수 반환
                    return newM + 1;
                }

                if (board[nx][ny] == 'U')
                    newD = D;

                if (newD > 0) {
                    newD--;
                } else {
                    newH--;
                }

                if (newH == 0)
                    continue;

                if (check[nx][ny] < newH + newD) {
                    check[nx][ny] = newH + newD;
                    queue.add(new Node(nx, ny, newH, newD, newM + 1));
                }
            }
        }
        return -1; // 안전지대에 도달하지 못한 경우
    }

    public static boolean isValid(int nx, int ny) {
        return 0<= nx && nx < N  && 0 <=ny && ny < N;
    }
}
