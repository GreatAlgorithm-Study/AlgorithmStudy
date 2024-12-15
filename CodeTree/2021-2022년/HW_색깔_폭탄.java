import java.io.*;
import java.util.*;

public class HW_색깔_폭탄 {
    static int n, m;
    static int[][] board;
    static int score = 0;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static class Bomb {
        int size;
        int redCount;
        int stdX;
        int stdY;
        List<int[]> blocks; // 폭탄 묶음 내 좌표

        Bomb(int size, int redCount, int stdX, int stdY, List<int[]> blocks) {
            this.size = size;
            this.redCount = redCount;
            this.stdX = stdX;
            this.stdY = stdY;
            this.blocks = blocks;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][n];

        for (int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<n; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            Bomb group = find();
            if (group == null || group.size < 2) {
                break;
            }
            score += group.size * group.size;
            remove(group);
            gravity();
            board = rotate(board);
            gravity();
        }

        System.out.println(score);
    }

    static Bomb find() {
        List<Bomb> candidates = new ArrayList<>();

        for (int i=0; i<n; i++){
            for (int j=0; j<n; j++){
                int color = board[i][j];
                if (0 < color && color <= m) {
                    Bomb bg = bfs(i, j, color);
                    if (bg != null && 2 <=bg.size) {
                        candidates.add(bg);
                    }
                }
            }
        }
        if (candidates.isEmpty()){
            return null;
        }
        // 우선순위 정렬
        candidates.sort((a,b)-> {
            if (a.size != b.size) // 1. size 내림차순
                return b.size - a.size;
            if (a.redCount != b.redCount) // 2. redCount 오름차순
                return a.redCount - b.redCount;
            if (a.stdX != b.stdX) // 3. 기준 행 내림차순
                return b.stdX - a.stdX;
            return a.stdY - b.stdY; // 4. 기준 열 오름차순
        });

        return candidates.get(0);
    }

    static Bomb bfs(int sx, int sy, int color) {
        boolean[][] visited = new boolean[n][n];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{sx, sy});
        visited[sx][sy] = true;

        List<int[]> group = new ArrayList<>();
        group.add(new int[]{sx, sy});
        int redCount = (board[sx][sy] == 0) ? 1 : 0;

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int x=cur[0], y=cur[1];

            for (int k=0; k<4; k++){
                int nx = x + dx[k];
                int ny = y + dy[k];
                if (!isValid(nx, ny)) {
                    continue;
                }
                if (board[nx][ny] == -1) { // 검은 돌 제외
                    continue;
                }
                if (!visited[nx][ny]) {
                    if (board[nx][ny] == color || board[nx][ny] == 0) {
                        visited[nx][ny] = true;
                        q.offer(new int[]{nx,ny});
                        group.add(new int[]{nx,ny});
                        if (board[nx][ny] == 0) {
                            redCount++;
                        }
                    }
                }
            }
        }

        if (group.size()<2) return null;


        int targetX=-1;
        int targetY=-1;
        for (int i=0; i<group.size(); i++) {
            int gx = group.get(i)[0];
            int gy = group.get(i)[1];
            if (board[gx][gy]!=0) { // 기준점 찾기: 빨간 아닌 폭탄 중 x 최대, x 같다면 y 최소
                if (gx > targetX || (gx==targetX && (targetY==-1 || gy < targetY))) {
                    targetX = gx;
                    targetY = gy;
                }
            }
        }
        return new Bomb(group.size(), redCount, targetX, targetY, group);
    }

    static void remove(Bomb b) {
        for (int i=0; i<b.blocks.size(); i++) {
            int x=b.blocks.get(i)[0];
            int y=b.blocks.get(i)[1];
            board[x][y] = -2; // 빈칸 처리
        }
    }

    static void gravity() {
        for (int y=0; y<n; y++){
            for (int x=n-1; x>=0; x--){
                if (board[x][y] > -1) {
                    int nx = x;
                    while(true) {
                        int down = nx+1;
                        if (down>=n) break;
                        if (board[down][y]!=-2) break;
                        board[down][y]=board[nx][y];
                        board[nx][y]=-2;
                        nx=down;
                    }
                }
            }
        }
    }
    static int[][] rotate(int[][] arr) {
        int[][] newBoard = new int[n][n];
        for (int x=0; x<n; x++){
            for (int y=0; y<n; y++){
                newBoard[n-1-y][x]=arr[x][y];
            }
        }
        return newBoard;
    }
    static boolean isValid(int nx, int ny){
        return 0 <= nx && nx < n && 0 <= ny && ny < n;
    }
}