import java.io.*;
import java.util.*;

public class SB_놀이기구_탑승 {
    static int N;
    static int[][] board;
    static int[][] emptyCnt;
    static List<List<Integer>> friends = new ArrayList<>();
    static PriorityQueue<Node> pq = new PriorityQueue<Node>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    private static int checkNxt(int x, int y, int idx) {
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (!isValid(nx, ny)) continue;
            if (friends.get(idx).contains(board[nx][ny])) cnt++;
        }
        return cnt;
    }
    private static void findSit(int idx) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j]==0) {
                    int like = checkNxt(i, j, idx);
                    pq.offer(new Node(i, j, like, emptyCnt[i][j]));
                }
            }
        }
        fixSit(idx);
    }

    private static void fixSit(int idx) {
        if (!pq.isEmpty()) {
            Node cur = pq.poll();
            board[cur.r][cur.c] = idx;

            // 빈자리 수 업데이트
            emptyCnt[cur.r][cur.c]--;
            for (int i = 0; i < 4; i++) {
                int nx = cur.r + dx[i];
                int ny = cur.c + dy[i];
                if (isValid(nx, ny)) {
                    emptyCnt[nx][ny]--;
                }
            }
        }
        pq.clear();
    }

    private static boolean isValid(int x, int y) {
        return 0<=x && x<N && 0<=y && y<N;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        board = new int[N][N];
        emptyCnt = new int[N][N];

        // emptyCnt 초기화
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                emptyCnt[i][j] = 4;
                if (i==0 || i==N-1) emptyCnt[i][j]--;
                if (j==0 || j==N-1) emptyCnt[i][j]--;
                if (emptyCnt[i][j]==4) pq.offer(new Node(i, j, 0, 4));
            }
        }
        // friends 리스트 초기화
        for (int i = 0; i <= N * N; i++) {
            friends.add(new ArrayList<>());
        }

        for (int i = 0; i < N * N; i++) {
            st = new StringTokenizer(br.readLine());
            int me = Integer.parseInt(st.nextToken());
            for (int j = 0; j < 4; j++) {
                int f = Integer.parseInt(st.nextToken());
                friends.get(me).add(f);
            }
            if (i==0) fixSit(me);       // 처음사람은 바로 자리 고정
            else findSit(me);
        }

        // 점수 카운트
        int[] score = {0, 1, 10, 100, 1000};
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int like = checkNxt(i, j, board[i][j]);
                ans += score[like];
            }
        }
        System.out.println(ans);
    }
    private static class Node implements Comparable<Node>{
        int r, c, like, empty;

        Node(int r, int c, int like, int empty) {
            this.r = r;
            this.c = c;
            this.like = like;
            this.empty = empty;
        }

        @Override
        public int compareTo(Node o) {
            int cmp = Integer.compare(o.like, this.like);
            if (cmp == 0) {
                int cmp2 = Integer.compare(o.empty, this.empty);
                if (cmp2 == 0) {
                    int cmp3 = Integer.compare(this.r, o.r);
                    if (cmp3==0){
                        return Integer.compare(this.c, o.c);
                    } return cmp3;
                }return cmp2;
            }
            return cmp;
        }
    }
}