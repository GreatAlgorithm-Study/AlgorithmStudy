import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SB_나무_타이쿤 {
    static int N, M;
    static int[][] board;
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {1, 1, 0, -1, -1, -1, 0, 1};
    static Queue<Node> pills = new ArrayDeque<>();
    static boolean[][] check;

    private static void movePill(int d, int p){
        p %= N;
        int num = pills.size();
        while (!pills.isEmpty() && num-- > 0) {
            Node cur = pills.poll();
            int nx = (cur.x + (dx[d] * p)) % N;
            int ny = (cur.y + (dy[d] * p)) % N;
            if (nx < 0) nx= (nx+N)%N;
            if (ny < 0) ny= (ny+N)%N;

            board[nx][ny]++;        // 특수 영양제 땅의 리브로 1증가
            pills.offer(new Node(nx, ny));
        }

    }

    private static void growTree(){
        check = new boolean[N][N];

        while(!pills.isEmpty()){
            Node cur = pills.poll();

            for(int i=1; i<8; i+=2){     // 각 영양제의 대각선 체크 (홀수)
                int nx = cur.x+dx[i];
                int ny = cur.y+dy[i];
                if((0<=nx && nx<N && 0<=ny && ny<N) && board[nx][ny]>0) board[cur.x][cur.y]++;
            }
            check[cur.x][cur.y] = true;
        }
    }

    private static void injectPill(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] >= 2 && !check[i][j]) {
                    board[i][j] -= 2;
                    pills.offer(new Node(i, j));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][N];
        check = new boolean[N][N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 초기 영양제 주입
        pills.offer(new Node(N-1, 0));
        pills.offer(new Node(N-1, 1));
        pills.offer(new Node(N-2, 0));
        pills.offer(new Node(N-2, 1));

        while(M-- >0){
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken())-1;
            int p = Integer.parseInt(st.nextToken());

            // 1. 영양제 움직이기
            movePill(d, p);

            // 2. 리브로수 성장
            growTree();

            // 3. 새로운 특수 영양제 투입
            injectPill();
        }

        // 4. 리브로수 높이 총 합
        int ans = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                ans+=board[i][j];
            }
        }

        System.out.println(ans);

    }

    static class Node{
        int x;
        int y;
        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
