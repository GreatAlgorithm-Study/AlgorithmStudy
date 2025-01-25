public class JW_1405 {

    static int n;
    static double[] ratio;
    static double totalRatio = 0;
    static boolean[][] visited = new boolean[30][30];
    static int[] dy = { 0, 0, 1, -1 }, dx = { 1, -1, 0, 0 };

    public static void main(String[] args) throws Exception {
        n = read();
        ratio = new double[4];
        for (int i = 0; i < 4; i++)
            ratio[i] = read() / 100.0;
        visited[15][15] = true; // 초기 조건
        recursive(0, 15, 15, 1); // DFS
        System.out.println(totalRatio);
    }

    // DFS
    private static void recursive(int depth, int y, int x, double sumRatio) {
        // 종료 조건
        if (depth == n || sumRatio == 0) {
            totalRatio += sumRatio;
            return;
        }
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i], nx = x + dx[i]; // 다음 위치
            // 방문하지 않았다면
            if (!visited[ny][nx]) {
                visited[ny][nx] = true;
                recursive(depth + 1, ny, nx, sumRatio * ratio[i]); // 이동 및 확률 계산
                visited[ny][nx] = false; // 백 트래킹
            }
        }
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48)
            n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13)
            System.in.read();
        return n;
    }
}
