import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HW_시공의_돌풍 {
    static int n, m, t;
    static int[][] map;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        int top = -1;
        int bottom = -1; // 시공의 돌풍 위치

        // 입력 처리
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) {
                    if (top == -1) top = i; // 윗부분 시공의 돌풍
                    else bottom = i; // 아랫부분 시공의 돌풍
                }
            }
        }

        // t초 동안 먼지 확산과 돌풍 작동
        for (int time = 0; time < t; time++) {
            spread(); // 먼지 확산
            clean(top, bottom); // 시공의 돌풍 작동
        }

        // 남은 먼지의 양 계산
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] > 0)
                    ans += map[i][j];
            }
        }
        System.out.println(ans);
    }

    // 먼지 확산
    public static void spread() {
        int[][] temp = new int[n][m];

        for (int i = 0; i < n; i++) { // 배열 복사
            for (int j = 0; j < m; j++) {
                temp[i][j] = map[i][j];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] > 0) {
                    int total = map[i][j] / 5;
                    int cnt = 0;
                    for (int d = 0; d < 4; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];

                        if (isRange(nx, ny)) {
                            temp[nx][ny] += total;
                            cnt++;
                        }
                    }
                    temp[i][j] -= (total * cnt); // 확산 후 남은 먼지 양
                }
            }
        }
        // 배열 복사
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = temp[i][j];
            }
        }


    }

    // 시공의 돌풍 작동
    static void clean(int top, int bottom) {
        // 윗부분 (반시계 방향)
        for (int i = top - 1; i > 0; i--) { // 아래
            map[i][0] = map[i - 1][0];
        }
        for (int i = 0; i < m - 1; i++) { // 왼쪾
            map[0][i] = map[0][i + 1];
        }
        for (int i = 0; i < top; i++) { // 위
            map[i][m - 1] = map[i + 1][m - 1];
        }
        for (int i = m - 1; i > 1; i--) { // 오른쪽
            map[top][i] = map[top][i - 1];
        }
        map[top][1] = 0; // 깨끗한 공기

        // 아랫부분 (시계 방향)
        for (int i = bottom + 1; i < n - 1; i++) { // 위
            map[i][0] = map[i + 1][0];
        }

        for (int i = 0; i < m - 1; i++) { // 왼쪽
            map[n - 1][i] = map[n - 1][i + 1];
        }

        for (int i = n - 1; i > bottom; i--) { // 아래
            map[i][m - 1] = map[i - 1][m - 1];
        }

        for (int i = m - 1; i > 1; i--) { // 오른쪽
            map[bottom][i] = map[bottom][i - 1];
        }
        map[bottom][1] = 0; // 깨끗한 공기
    }
    static boolean isRange(int nx, int ny) {
        return nx >= 0 && nx < n && ny >= 0 && ny < m && map[nx][ny] != -1;
    }
}