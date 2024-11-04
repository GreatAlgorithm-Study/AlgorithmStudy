public class JW_자율주행_자동차 {

    static int n, m;                        // 세로, 가로
    static boolean[][] board;               // 차가 움직일 보드
    static boolean[][] visited;             // 방문 처리
    static int[] dy = { -1, 0, 1, 0 };      // 북 동 남 서
    static int[] dx = { 0, 1, 0, -1 };
    static int area;                        // 차가 지나간 블록의 수

    public static void main(String[] args) throws Exception {
        n = read();
        m = read();
        board = new boolean[n][m];
        visited = new boolean[n][m];
        int y = read(), x = read(), d = read();
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                board[i][j] = read() == 0;  // 0인 부분만 true로 표시
        move(y, x, d);                      // 재귀 호출
    }

    // 자동차의 움직임을 구현할 재귀 함수
    // y : 현재 세로 값
    // x : 현재 가로 값
    // d : 방향 (0 : 북, 1 : 동, 2: 남, 3: 서)
    private static void move(int y, int x, int d) {
        if (!visited[y][x])                 // 방문하지 않았던 좌표라면
            area++;                         // 새로운 곳이므로 차가 움직인 영역 + 1
        visited[y][x] = true;               // 방문 체크
        for (int i = 1; i <= 4; i++) {
            int nd = (4 + (d - i)) % 4;     // 현재 방향에서 좌회전하면서 갈 수 있는 영역 확인
            int ny = y + dy[nd];
            int nx = x + dx[nd];
            if (isValid(ny, nx) && !visited[ny][nx]) // 다음 좌표가 유효하다면
                move(ny, nx, nd);           // 다음 좌표로 진행
        }
        
        // 이 부분에 들어왔다는 것은 윗 부분에서 재귀를 호출하지 못했다는 것
        // 재귀 호출을 하지 못했으면 후진해야함.
        
        // 후진 좌표 계산
        int by = y - dy[d];
        int bx = x - dx[d];
        if (isValid(by, bx))                // 후진할 수 있었다면
            move(by, bx, d);                // 방향을 유지한 채 후진 -> 그래야 다시 좌회전부터 체크할 수 있음
        // 후진할 수 없는 영역 즉, 인도나 경계인 경우
        else {
            System.out.println(area);       // 여태 움직인 거리를 출력
            System.exit(0);                 // 메인 함수 종료
        }
    }

    // 경계 및 차가 움직일 수 있는 도로인지 판단
    private static boolean isValid(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < m && board[y][x];
    }

    // 빠른 입력을 위한 함수
    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48)
            n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13)
            System.in.read();
        return n;
    }
}