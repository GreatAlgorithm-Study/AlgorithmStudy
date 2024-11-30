import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 보드 한칸에 곰팡이 한개씩 존재(중간 겹칠때 빼고)
// 곰팡이는 크기로 제거 (곰팡이의 크기는 고유함)
public class SB_생명과학부_랩_인턴 {
    static int N,M, K;
    static int[][] board;
    static Queue<Mold> live = new ArrayDeque<>();
    static List<Integer> dieList = new ArrayList<>();
    static int ans = 0;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};                    // 상하우좌

    private static void moveMold() {                    // 곰팡이 이동
        int size = live.size();
        int[][] tmpBoard = new int[N][M];               // 곰팡이 이동 할 새로운 보드 준비
        List<Integer> tmpDie = new ArrayList<>();       // 새롭게 죽은 곰팡이들 리스트

        // 현재 살아있는 곰팡이들 움직이기
        while (size-- > 0) {
            Mold cur = live.poll();
            if (dieList.contains(cur.b)) continue;       // 죽은 리스트에 해당하는 곰팡이는 패쓰

            // 이동 거리 최적화 (각 행열의 길이-1만큼 반복 빼기)
            cur.s %= (cur.d < 2 ? 2 * (N - 1) : 2 * (M - 1));
            for (int i = 0; i < cur.s; i++) {
                int nx = cur.x + dx[cur.d];
                int ny = cur.y + dy[cur.d];
                if (!isValid(nx, ny)) {                  // 벽에 닿으면 방향 전환 후 이동
                    cur.d = (cur.d % 2 == 0) ? cur.d + 1 : cur.d - 1;  // 방향 바꾸기
                    nx = cur.x + dx[cur.d];
                    ny = cur.y + dy[cur.d];
                }
                cur.x = nx;                             // 위치 이동
                cur.y = ny;
            }
            // 새로운 보드에 최종자리 위치하기
            if (tmpBoard[cur.x][cur.y]!=0) {                                        // 만약 최종 움직인 위치에 곰팡이 겹치면
                tmpDie.add(Math.min(tmpBoard[cur.x][cur.y], cur.b));                // 작은 곰팡이는 다이리스트 행
                tmpBoard[cur.x][cur.y] = Math.max(tmpBoard[cur.x][cur.y], cur.b);   // 큰 곰팡이는 자리 차지
            }else tmpBoard[cur.x][cur.y] = cur.b;                                   // 빈자리면 지금 곰팡이 차지

            live.offer(cur);                                                        // 일단 이동완료한 곰팡이는 다시 live큐에 넣기
        }
        // 보드 갱신
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                board[i][j] = tmpBoard[i][j];
            }
        }
        // 다이리스트 갱신
        dieList.clear();                // 기존 다이리스트 비우고
        dieList.addAll(tmpDie);         // 새로 업데이트한 다이리스트로 변경
    }

    private static boolean isValid(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 보드 초기화
        board = new int[N][M];

        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());           // 거리
            int d = Integer.parseInt(st.nextToken()) - 1;       // 방향
            int b = Integer.parseInt(st.nextToken());           // 크기
            Mold mold = new Mold(x, y, s, d, b);
            live.offer(mold);
            board[x][y] = b;
        }

        for (int j = 0; j < M; j++) {
            // 1. 해당 열의 곰팡이 채취
            for (int i = 0; i < N; i++) {
                if (board[i][j]!=0){
                    dieList.add(board[i][j]);     // 해당 위치의 곰팡이 크기 넣기(크기 고유)
                    ans += board[i][j];           // 곰팡이 채취
                    board[i][j] = 0;              // 곰팡이 제거
                    break;
                }
            }
            // 2. 곰팡이 이동
            moveMold();
        }

        System.out.println(ans);
    }

    static class Mold{
        int x, y, s, d, b;

        public Mold(int x, int y, int s, int d, int b) {
            this.x = x;
            this.y = y;
            this.s = s;
            this.d = d;
            this.b = b;
        }
    }
}
