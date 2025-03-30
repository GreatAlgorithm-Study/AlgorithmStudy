import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SB_나무박멸 {
    static int N, M, K, C;
    static int[][] board;
    static int[][] poison;
    static int[] dx = {-1, 0, 1, 0, -1, 1, 1, -1};
    static int[] dy = {0, -1, 0, 1, -1, -1, 1, 1};
    static int ans = 0;

    private static void grow() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] <=0) continue;
                int cnt = 0;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if (!isValid(nx, ny)) continue;
                    if (board[nx][ny] > 0) cnt++;
                }
                board[i][j] += cnt;
            }
        }
    }

    private static void breeding() {
        int[][] tmp = new int[N][N];

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (board[x][y] <= 0) continue;
                int cnt = 0;
                for (int i=0; i<4; i++) {       // 번식 공간 카운트
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (!isValid(nx, ny) || poison[nx][ny] > 0) continue;
                    if (board[nx][ny] == 0) cnt++;
                }

                // 번식
                if (cnt==0) continue;
                int nw = board[x][y] / cnt;
                for (int i=0; i<4; i++) {       // 주위로 나무 성장
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (!isValid(nx, ny) || poison[nx][ny] > 0) continue;   // 제초제 있는곳은 패쓰
                    if (board[nx][ny]==0) tmp[nx][ny] += nw;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] += tmp[i][j];
            }
        }
    }

    private static void weeding() {
        int mxVal = 0;
        int mxx = 0;
        int mxy = 0;

        for (int x = 0; x < N; x++) {
            for (int y=0; y<N; y++) {
                if (board[x][y] <=0) continue;
                int cnt = board[x][y];
                for (int i=4; i<8; i++) {
                    for (int k = 1; k <= K; k++) {
                        int nx = x + dx[i]*k;
                        int ny = y + dy[i]*k;
                        if (!isValid(nx, ny) || board[nx][ny] <= 0) continue;
                        cnt += board[nx][ny];
                    }
                }
                if (mxVal < cnt) {   // 최대 제초 업데이트
                    mxVal = cnt;
                    mxx = x;
                    mxy = y;
                }
            }
        }

        ans += mxVal;

        if (board[mxx][mxy] > 0) {
            board[mxx][mxy] = 0;
            poison[mxx][mxy] = C;
            for (int i=4; i<8; i++) {
                for (int k = 1; k <= K; k++) {
                    int nx = mxx + dx[i]*k;
                    int ny = mxy + dy[i]*k;
                    if (!isValid(nx, ny) || board[nx][ny] < 0) continue;
                    if (board[nx][ny]==0) {
                        poison[nx][ny] = C;
                        break;
                    }
                    board[nx][ny] = 0;
                    poison[nx][ny] = 0;
                }
            }
        }
    }

    private static void disappear() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (poison[i][j] > 0) poison[i][j]--;
            }
        }
    }

    private static boolean isValid(int x, int y) {
        return 0<=x && x<N && 0<=y && y<N;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new int[N][N];
        poison = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (M-- > 0) {
            grow();
            // System.out.println("1. ");
            // System.out.println(Arrays.deepToString(board));

            breeding();
            // System.out.println("2. ");
            // System.out.println(Arrays.deepToString(board));

            weeding();
            // System.out.println("3. ");
            // System.out.println(Arrays.deepToString(board));
            // System.out.println(Arrays.deepToString(poison));


            disappear();
            // System.out.println("4. ");
            // System.out.println(Arrays.deepToString(board));

        }
        System.out.println(ans);
    }
}
