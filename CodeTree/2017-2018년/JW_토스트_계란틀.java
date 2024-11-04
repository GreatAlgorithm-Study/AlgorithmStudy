import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class JW_토스트_계란틀 {

    static int n, l, r;
    static int[][] toast;       // 계란의 양을 저장할 배열
    static boolean[][] visited; // 분리 가능한 지를 확인할 배열

    static int[] dy = { 1, 0, -1, 0 };
    static int[] dx = { 0, 1, 0, -1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        toast = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++)
                toast[i][j] = Integer.parseInt(st.nextToken());
        }
        int time = 0;           // 계란의 이동 시간
        while (separate()) {    // 분리가 가능한가?
            time++;             // 분리 했다면 계란의 이동 시간 증가
        }
        System.out.println(time);
    }

    // 순차적으로 계란틀이 분리가 가능한 지 확인하는 함수
    private static boolean separate() {
        visited = new boolean[n][n];
        boolean flg = false;            // 분리가 됐는 지를 알려줄 검출 Flag
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                if (visited[i][j])      // 이미 분리한(방문한) 계란 틀은 스킵
                    continue;           
                // 순차적으로 순회하기 때문에 진행 방향인 아래, 오른쪽만 확인
                for (int k = 0; k < 2; k++) {
                    int ni = i + dy[k];
                    int nj = j + dx[k];
                    // 다음 좌표와 합칠 수 있는 지
                    if (isPossible(i, j, ni, nj)) {
                        flg = true;         // 합칠 수 있었음
                        distribute(ni, nj); // BFS를 이용해서 합칠 수 있는 계란들 합치기
                    }
                }
            }
        return flg; // 검출 Flag 반환
    }

    // 계란틀이 분리가 가능하다면 계란 합치기
    // BFS 이용
    private static void distribute(int sy, int sx) {
        int cnt = 0, sum = 0;
        // BFS
        Deque<int[]> dq = new ArrayDeque<>();
        ArrayList<int[]> info = new ArrayList<>();  // BFS를 진행한 좌표들 저장
        dq.offer(new int[] { sy, sx });
        visited[sy][sx] = true;
        while (!dq.isEmpty()) {
            int[] cur = dq.poll();
            int y = cur[0], x = cur[1];
            // 정보 저장
            cnt++;
            sum += toast[y][x];
            info.add(new int[] { y, x });
            
            for (int i = 0; i < 4; i++) {
                int ny = cur[0] + dy[i];
                int nx = cur[1] + dx[i];
                // 다음 좌표도 합칠 수 있는 지
                if (isPossible(y, x, ny, nx)) {
                    visited[ny][nx] = true;         // 방문처리
                    dq.offer(new int[] { ny, nx });
                }
            }
        }
        int avg = sum / cnt;
        // 방문했던 좌표들 값 재할당
        for (int i = 0; i < info.size(); i++) {
            int y = info.get(i)[0], x = info.get(i)[1];
            toast[y][x] = avg;
        }
    }

    // 분리가 가능한 지 확인하는 함수
    private static boolean isPossible(int y, int x, int ny, int nx) {
        // 방문하지 않았던 좌표들 중
        if (isValid(ny, nx) && !visited[ny][nx]) {
            // 차이가 l이상 r이하
            int diff = Math.abs(toast[y][x] - toast[ny][nx]);
            return l <= diff && diff <= r;
        }
        return false;
    }

    // 경계 체크
    private static boolean isValid(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < n;
    }
}