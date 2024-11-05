import java.util.*;
import java.io.*;

public class JY_나무_타이쿤 {

    static int N, M;
    static int[][] g;       // 리브로수
    static boolean[][] s;   // 특수 영양제 
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {1, 1, 0, -1, -1, -1, 0, 1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        g = new int[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                g[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] rule = new int[M][2];
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            rule[i][0] = Integer.parseInt(st.nextToken()) - 1;
            rule[i][1] = Integer.parseInt(st.nextToken());
        }

        // 초기 특수영양제 초기화
        s = new boolean[N][N];
        for(int i=N-2; i<N; i++) {
            for(int j=0; j<2; j++) {
                s[i][j] = true;
            }
        }

        // 년도 반복
        for(int i=0; i<M; i++) {
            // 특수 영양제 이동
            moveVita(rule[i][0], rule[i][1]);

            // 투입 (높이+1)
            grow();

            // 인접 대각선 확인
            checkAdj();

            // 높이 2이상 리브로수 check & 영양제 생성 (이전 영양제 위치 제외 + 영양제 사라짐)
            generate();

        }

        // 결과값 구하기
        int total = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                total += g[i][j];
            }
        }
        System.out.println(total);

    }
    public static void print(int[][] arr) {
        for(int i=0; i<arr.length; i++) {
            System.out.println(Arrays.toString(arr[i]));
        }
    }
    public static void moveVita(int dir, int cnt) {
        boolean[][] tmp = new boolean[N][N];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(s[i][j]) {
                    int nx = (i + dx[dir]*cnt + N) % N;
                    int ny = (j + dy[dir]*cnt + N) % N;
                    tmp[nx][ny] = true;
                }
            }
        }

        // 깊은 복사
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                s[i][j] = tmp[i][j];
            }
        }
    }
    public static void grow() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(s[i][j]) {
                    g[i][j]++;
                }
            }
        }
    }
    public static boolean inRange(int x, int y) {
        return x>=0 && x<N && y>=0 && y<N;
    }
    public static void checkAdj() {
        // 대각선 방향 1, 3, 5, 7
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(s[i][j]){
                    for(int d=1; d<8; d+=2) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        if(inRange(nx, ny) && g[nx][ny] >= 1){
                            g[i][j]++;
                        }
                    }
                }
            }
        }
    }
    public static void generate() {
        boolean[][] tmp = new boolean[N][N];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(g[i][j] >= 2 && !s[i][j]) {
                    g[i][j] -= 2;
                    tmp[i][j] = true;
                }
            }
        }
        // 깊은 복사
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                s[i][j] = tmp[i][j];
            }
        }
    }
}