import java.io.*;
import java.util.*;

public class SB_전투로봇 {
    static int N;
    static int[][] board;
    static Bot rb;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    private static int bfs() {
        int time = 0;
        int level = 2;
        int catchMon = 0;

        while (true){
            PriorityQueue<Bot> monsters = new PriorityQueue<>();
            boolean[][] visited = new boolean[N][N];
            monsters.offer(new Bot(rb.i, rb.j, 0));
            visited[rb.i][rb.j] = true;
            boolean isCatch = false;

            while (!monsters.isEmpty()) {
                Bot cur = monsters.poll();

                // 현재 위치에 잡을 수 있는 몬스터 있는 경우 몬스터 잡기
                if (board[cur.i][cur.j] != 0 && board[cur.i][cur.j] < level){
                    catchMon++;
                    time += cur.dist;
                    board[cur.i][cur.j] = 0;

                    rb.updateBot(cur.i, cur.j);         // 로봇 위치 업데이트
                    isCatch = true;
                    break;
                }

                // 몬스터 못잡으면 이동
                for (int k = 0; k < 4; k++) {
                    int nx = cur.i + dx[k];
                    int ny = cur.j + dy[k];

                    if (!isValid(nx, ny) || visited[nx][ny] || board[nx][ny] > level) continue;
                    monsters.offer(new Bot(nx, ny, cur.dist + 1));
                    visited[nx][ny] = true;
                }
            }
            if (!isCatch) break;            // 못잡으면 끝남
            if (level == catchMon) {
                level++;
                catchMon = 0;
            }
        }

        return time;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 9) {
                    rb = new Bot(i, j, 0);
                    board[i][j] = 0;
                }
            }
        }

        System.out.println(bfs());
    }

    private static boolean isValid(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    static class Bot implements Comparable<Bot> {
        int i, j, dist;

        public Bot(int i, int j, int dist) {
            this.i = i;
            this.j = j;
            this.dist = dist;
        }

        private void updateBot(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public int compareTo(Bot o) {
            if (this.dist == o.dist) {
                if (this.i == o.i) {
                    return this.j - o.j;
                }
                return this.i - o.i;
            }
            return this.dist - o.dist;
        }

        @Override
        public String toString() {
            return "Bot{" +
                    "i=" + i +
                    ", j=" + j +
                    ", dist=" + dist +
                    '}';
        }
    }
}