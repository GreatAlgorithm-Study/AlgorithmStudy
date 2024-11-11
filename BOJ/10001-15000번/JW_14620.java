public class JW_14620 {
    static int n;
    static int[][] board;
    static boolean[][] visited;
    // 현재 위치 + 상하좌우를 확인하기 위한 변화량
    static int[] dy = { 0, 1, -1, 0, 0 };
    static int[] dx = { 0, 0, 0, 1, -1 };
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        n = read();
        board = new int[n][n];
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                board[i][j] = read();
        // 완전 탐색
        recursive(0, 0, 0);
        System.out.println(min);
    }

    private static void recursive(int depth, int total, int p) {
        // 3개를 다 심었다면 최솟값 갱신
        if (depth == 3) {
            min = Math.min(min, total);
            return;
        }
        // 심을 수 있는 위치 찾기
        for (int i = p; i < n * n; i++) {
            int y = i / n, x = i % n;
            boolean isPossible = true;
            // 모든 유효성을 통과하는지 확인
            for (int j = 0; j < 5; j++) {
                int ny = y + dy[j];
                int nx = x + dx[j];
                if (!isValid(ny, nx)) {
                    isPossible = false;
                }
            }
            // 심을 수 있는 위치일 경우
            if (isPossible) {
                int sum = 0;
                // 방문 처리
                for (int j = 0; j < 5; j++) {
                    int ny = y + dy[j];
                    int nx = x + dx[j];
                    visited[ny][nx] = true;
                    sum += board[ny][nx];
                }
                // 다음 깊이의 재귀 진행
                recursive(depth + 1, total + sum, i + 1);
                
                // 백 트래킹
                for (int j = 0; j < 5; j++) {
                    int ny = y + dy[j];
                    int nx = x + dx[j];
                    visited[ny][nx] = false;
                }
            }
        }
    }

    // 경계 체크 및 방문하지 않았는지 유효성 검증
    private static boolean isValid(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < n && !visited[y][x];
    }

    // 빠른 입력 함수
    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48)
            n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13)
            System.in.read();
        return n;
    }
}