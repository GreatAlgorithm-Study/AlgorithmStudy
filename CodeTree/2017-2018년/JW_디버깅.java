public class JW_디버깅 {

    static int n, m, h;
    static boolean[][] ladders;

    public static void main(String[] args) throws Exception {
        n = read();
        m = read();
        h = read();
        ladders = new boolean[h][n - 1]; // 사다리가 설치되어 있는지 확인할 배열
        // 미리 설치되어 있는 사다리 입력
        for (int i = 0; i < m; i++) {
            int y = read(), x = read();
            ladders[y - 1][x - 1] = true;
        }
        // 사다리를 3개 이하로 설치해서 올바른지 확인
        for (int i = 0; i <= 3; i++) {
            if (placeLadder(0, i)) {
                System.out.println(i);
                return;
            }
        }
        // 3개 초과한다면 실패, -1 출력
        System.out.println(-1);
    }

    // 재귀적 호출로 사다리 설치
    // depth : 설치한 사다리의 수
    // cnt : 설치해야 할 사다리 수
    private static boolean placeLadder(int depth, int cnt) {
        // 사다리가 다 설치되었다면 움직임
        if (depth == cnt)
            return simulate();
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n - 1; j++) {
                // 사다리를 설치할 수 있는 위치라면
                if (isPossible(i, j)) {
                    ladders[i][j] = true; // 사다리 설치 후 다음 깊이의 재귀 호출
                    if (placeLadder(depth + 1, cnt))
                        return true;
                    // 사다리 설치가 잘못되었다면
                    ladders[i][j] = false; // 사다리 해체 (백트래킹)
                }
            }
        }
        // 전부 실패
        return false;
    }

    // 사다리를 설치할 수 있는지 없는지 확인
    private static boolean isPossible(int y, int x) {
        // 현재 위치가 설치되어 있지 않고
        if (ladders[y][x])
            return false;
        // 좌, 우에 사다리가 설치되지 않은 경우
        if ((x - 1 >= 0 && ladders[y][x - 1]) || (x + 1 < n - 1 && ladders[y][x + 1]))
            return false;
        return true;
    }

    // 움직임 구현하는 시뮬레이션
    private static boolean simulate() {
        for (int i = 0; i < n - 1; i++) {
            int num = i; // 초기 위치
            // 내려가면서 좌, 우에 사다리가 있다면 위치 변환
            for (int j = 0; j < h; j++) {
                int left = num - 1, right = num;
                // 좌측에 사다리가 있다면 왼쪽으로 이동
                if (left >= 0 && ladders[j][left])
                    num--;
                // 우측에 사다리가 있다면 오른쪽으로 이동
                if (right < n - 1 && ladders[j][right])
                    num++;
            }
            // 처음 위치와 마지막 위치가 같지 않다면
            if (num != i)
                return false;
        }
        return true;
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