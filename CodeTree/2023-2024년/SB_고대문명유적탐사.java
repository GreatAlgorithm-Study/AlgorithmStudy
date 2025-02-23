import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SB_고대문명유적탐사 {
    static int K, M;
    static int[][] board = new int[5][5];
    static int[] relics;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static PriorityQueue<ValInfo> mxValQue = new PriorityQueue<>();     // 1차 탐사시 최고 가치를 만드는 정보 (가치, 회전횟수, 좌표)
    static int[][] valBoard = new int[5][5];
    static int mxVal = 0;
    static int idx = 0;

    private static int bfs(int x, int y, boolean[][] visited, int[][] before) {
        Deque<Node> que = new ArrayDeque<>();
        int idx = before[x][y];
        que.offer(new Node(x, y, idx));
        visited[x][y] = true;
        int cnt = 1;

        while (!que.isEmpty()) {
            Node cur = que.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (!isValid(nx, ny) || visited[nx][ny] || before[nx][ny]!=idx) continue;
                que.offer(new Node(nx, ny, idx));
                visited[nx][ny] = true;
                cnt++;
            }
        }

        return cnt < 3 ? 0 : cnt;
    }

    private static int getValue(int[][] before) {
        boolean[][] visited = new boolean[5][5];
        int total = 0;
        for (int i = 0; i < 5; i++) {
            for (int j=0; j<5; j++) {
                if (!visited[i][j]) total += bfs(i, j, visited, before);
            }
        }
        return total;
    }

    private static void rotate(int mx, int my, int[][] before) {
        int sx = mx-1;  // 돌릴 작은 사각형의 시작은 중심점의 좌상단에 위치(-1, -1)
        int sy = my-1;
        for (int k=0; k<3; k++) {
            int[][] tmp = copy(before);
            // 90도 회전 수행
            for (int i = sx; i < sx+3; i++) {
                for (int j = sy; j < sy+3; j++) {
                    int ox = i-sx;
                    int oy = j-sy;
                    int rx = oy;
                    int ry = 3-ox-1;
                    tmp[sx+rx][sy+ry] = before[i][j];
                }
            }

            // 회전 내용 반영
            before = copy(tmp);

            // 1차 유물 획득 가치 얻기
            int tmpVal = getValue(before);
            ValInfo pre = mxValQue.peek();
            mxValQue.offer(new ValInfo(tmpVal, k, mx, my));     // 중심좌표 값 넣어주기

            if (pre==null || !pre.equals(mxValQue.peek())) {   // 첫번째 값이거나, 가치가 업데이트 되면 보드판도 업데이트
                valBoard = copy(before);
            }

        }
    }

    private static int[][] copy(int[][] origin) {
        int[][] tmp = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                tmp[i][j] = origin[i][j];
            }
        }
        return tmp;
    }

    private static boolean isValid(int x, int y) {
        return 0<=x && x<5 && 0<=y && y<5;
    }

    private static int explore() {
        // 모든 지점 최대가치 검사
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                int[][] before = copy(board);
                rotate(i, j, before);
            }
        }

        // 유물 1차 획득 보드 선정
        board = copy(valBoard);
//        mxVal += mxValQue.poll().val;       // 1차 최대 가치 획득
        if (!mxValQue.isEmpty()) return mxValQue.poll().val;
        else return 0;
//        return mxValQue.poll().val;
    }

    private static int getRelic() {        // 유물찾고 빵꾸 뚫기
        boolean[][] visited = new boolean[5][5];

        int cnt = 0;        // 한 탐사에서 얻는 가치
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (!visited[i][j] && board[i][j] != -1) {
                    cnt += checkRelic(i, j, visited);
                }
            }
        }

        mxVal += cnt;
        return cnt;
    }

    private static int checkRelic(int x, int y, boolean[][] visited) {
        Deque<Node> que = new ArrayDeque<>();
        int idx = board[x][y];
        List<Node> removeLst = new ArrayList<>();

        que.offer(new Node(x, y, idx));
        visited[x][y] = true;
        removeLst.add(new Node(x, y, idx));

        while (!que.isEmpty()) {
            Node cur = que.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (!isValid(nx, ny) || visited[nx][ny] || board[nx][ny] != idx) continue;
                que.offer(new Node(nx, ny, idx));
                visited[nx][ny] = true;
                removeLst.add(new Node(nx, ny, idx));
            }
        }

        if (removeLst.size() >= 3) {
            for (Node n : removeLst) {
                board[n.x][n.y] = -1;
            }
        }
        return removeLst.size() >=3 ? removeLst.size() : 0;
    }

    private static void putPiece() {
        for (int j = 0; j < 5; j++) {
            for (int i = 4; i >= 0; i--) {
                if (board[i][j]!=-1)continue;
                board[i][j] = relics[idx];
                idx++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        relics = new int[M];

        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            relics[i] = Integer.parseInt(st.nextToken());
        }


        while (K-- > 0) {
            // 가치 최대화할 격자 선택하기
            mxValQue.clear();
            mxVal = 0;

            // 해당 좌표에서 회전을 진행했을때 가치 기록 후 조건에 따라 격자 및 회전 선택
            if (explore()==0) break;

            while (getRelic()!=0) {
                putPiece();
            }
            sb.append(mxVal).append(" ");
        }
        System.out.println(sb);
    }

    static class Node{
        int x, y, idx;

        public Node(int x, int y, int idx) {
            this.x = x;
            this.y = y;
            this.idx = idx;
        }
    }

    static class ValInfo implements Comparable<ValInfo>{
        int val, rt, r, c;

        public ValInfo(int val, int rt, int r, int c) {
            this.val = val;
            this.rt = rt;
            this.r = r;
            this.c = c;
        }

        @Override
        public int compareTo(ValInfo o) {
            if (o.val-this.val==0){
                if (this.rt - o.rt==0){
                    if (this.c-o.c==0) {
                        return this.r - o.r;
                    } return this.c-o.c;
                } return this.rt - o.rt;
            }return o.val - this.val;
        }

        @Override
        public String toString() {
            return "ValInfo{" +
                    "val=" + val +
                    ", rt=" + rt +
                    ", r=" + r +
                    ", c=" + c +
                    '}';
        }
    }
}
