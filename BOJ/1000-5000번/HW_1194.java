import java.util.*;
import java.io.*;

// 미로를 탈출하는데 드는 이동 횟수의 최솟값을 출력

public class HW_1194 {
    static int N, M;
    static char[][] board;
    static boolean[][][] visited; // 방문
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static class Node{
        int x, y, key, step;
        public Node(int x, int y, int key, int step) {
            this.x = x;
            this.y = y;
            this.key = key;
            this.step = step;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        visited = new boolean[N][M][64];

        int startX = 0, startY = 0;
        for(int i=0; i<N; i++){
            String input = br.readLine();
            for(int j=0; j<M; j++){
                board[i][j] = input.charAt(j);
                if(board[i][j]=='0'){ // 시작 위치
                    startX = i;
                    startY = j;
                }
            }
        }
        int ans =  bfs(startX, startY);
        System.out.println(ans);
    }
    static int bfs(int x, int y){
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(x, y, 0, 0));
        visited[x][y][0] = true;
        board[x][y] = '.'; // 빈칸: 이동 가능

        while(!queue.isEmpty()){
            Node cur = queue.poll();
            if(board[cur.x][cur.y]=='1'){
                return cur.step;
            }
            for(int i=0; i<4; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                int nkey = cur.key;

                if(!isValid(nx,ny) || visited[nx][ny][nkey] || board[nx][ny]=='#') {
                    continue;
                }

                if ('a' <= board[nx][ny] && board[nx][ny] <= 'f') {
                    nkey |= (1 << (board[nx][ny] - 'a')); // 새로운 열쇠 획득
                }

                if ('A' <= board[nx][ny]&& board[nx][ny] <= 'F') {
                    if ((nkey & (1 << (board[nx][ny] - 'A'))) == 0) {
                        continue; // 열쇠 없으면 이동 불가
                    }
                }

                if (!visited[nx][ny][nkey]) { // 새 열쇠 상태로 방문
                    queue.add(new Node(nx, ny, nkey, cur.step + 1));
                    visited[nx][ny][nkey] = true;
                }
            }
        }
        return -1;
    }
    static boolean isValid(int nx, int ny){
        return 0<=nx && nx<N && 0<=ny && ny <M;
    }
}
