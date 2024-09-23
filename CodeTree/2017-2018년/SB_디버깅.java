import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* i번째에 i가 가도록. dfs로 완전탐색
* * 가지치기
*   - 사다리 옆에 있으면 못놓기(양옆 체크)
*   - 초기 올바르게 가는 행에는 놓지 말기
* * 구현
*   - 사다리타기 함수
*   - 사다리 놓기 함수
* */
public class SB_디버깅 {
    static int N, M, H;     // 열(사람), 선 갯수, 행(취약점, 가로)
    static Node[][] board;
    static int ans = -1;

    private static boolean play() {
        for (int c = 0; c < N; c++) {   // 사람 한명씩 사다리 탐 (열)
            int idx = c;
            int r = 0;                  // 행
            while (r<H){
                if (board[r][c].isRight) c++;
                else if (board[r][c].isLeft) c--;
                r++;
            }
            if (idx!=c) return false;
        }
        return true;
    }

    private static void addLine(int depth) {
        if (depth>3 || ans!=-1) return;

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N-1; j++) {
                // 사다릴 못놓으면 패쓰
                if (board[i][j].isLeft || board[i][j].isRight) continue;    // 사다리 존재하면 놓을 수 없음
                if (j>0 && board[i][j-1].isLeft) continue;  // 이웃 사다리 여부 확인(사다리 연속X)

                // 사다리 놓기
                board[i][j].onRight();
                board[i][j+1].onLeft();

//                System.out.println("depth:"+depth +" i:"+ i +" j: "+j);
                if (play()) {
                    ans = depth;
                    board[i][j].offRight();
                    board[i][j+1].offLeft();
                    return;
                }
                addLine(depth + 1);                 // 성공 못했으면 현재 상태에서 사다리 하나 더 추가해보기
                board[i][j].offRight();                    // 사다리 되돌리기
                board[i][j+1].offLeft();
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        board = new Node[H][N];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = new Node(i, j);
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            board[a-1][b-1].onRight();
            board[a-1][b].onLeft();
        }

        if (M==0 || play()) ans = 0;
        else addLine(1);

        System.out.println(ans);
    }

    static class Node{
        int i;
        int j;
        boolean isLeft = false;
        boolean isRight = false;

        Node(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public void onLeft(){
            this.isLeft = true;
        }
        public void onRight(){
            this.isRight = true;
        }
        public void offLeft(){
            this.isLeft = false;
        }
        public void offRight(){
            this.isRight = false;
        }
    }
}
