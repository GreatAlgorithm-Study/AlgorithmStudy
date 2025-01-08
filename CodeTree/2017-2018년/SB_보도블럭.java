import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* 오름차순: 세어온 개수가 L이상이고 다음 높이랑 1차이나면 가능
* 내림차순: 이전값 pre, 이후 세는 개수가 L이상이고 pre랑 높이 1차이나면 가능
* */
public class SB_보도블럭 {
    static int N, L;
    static int[][] board;
    static int ans = 0;

    private static boolean canPass(int[] line) {
        boolean[] used = new boolean[N];
        for (int i = 0; i < N - 1; i++) {
            if (line[i]==line[i+1]) continue;   // 같은 높이
            if (line[i+1]-line[i]==1) {         // 오름차순
                for (int j = 0; j < L; j++) {   // 자기자신부터 경사로 설치 됨
                    if (i-j < 0 || line[i] != line[i-j] || used[i-j]) return false;
                    used[i-j] = true;
                }
            } else if (line[i+1]-line[i]==-1) { // 내림차순
                for (int j = 1; j <= L; j++) {  // 자기 앞에부터 경사로 설치
                    if (i+j >=N || line[i+1] != line[i+j] || used[i+j]) return false;
                    used[i+j] = true;
                }
            } else return false;    // 높이차로 경사로 설치 불가
        }
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int[] row : board){        // 가로 확인
            if (canPass(row)) ans++;
        }

        for (int j = 0; j < N; j++) {   // 세로 확인
            int[] col = new int[N];
            for (int i = 0; i < N; i++) {
                col[i] = board[i][j];
            }
            if (canPass(col)) ans++;
        }

        System.out.println(ans);
    }
}
