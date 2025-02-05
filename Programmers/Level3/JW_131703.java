public class JW_131703 {

    public static void main(String[] args) {
        int[][] beginning = { { 0, 1, 0, 0, 0 }, { 1, 0, 1, 0, 1 }, { 0, 1, 1, 1, 0 }, { 1, 0, 1, 1, 0 },
                { 0, 1, 0, 1, 0 } };
        int[][] target = { { 0, 0, 0, 1, 1 }, { 0, 0, 0, 0, 1 }, { 0, 0, 1, 0, 1 }, { 0, 0, 0, 1, 0 },
                { 0, 0, 0, 0, 1 } };
        System.out.println(solution(beginning, target));
    }

    static int n, m;

    public static int solution(int[][] beginning, int[][] target) {
        n = beginning.length;
        m = beginning[0].length;
        int min = Integer.MAX_VALUE;
        // 만들 수 있는 모든 조합
        // row를 뒤집는 경우의 수
        for (int i = 0; i < (1 << n); i++) {
            // col을 뒤집는 경우의 수
            for (int j = 0; j < (1 << m); j++) {
                // 해당 뒤집는 경우의 수로 뒤집었을 때, 동일하다면
                if (isPossible(beginning, target, i, j)) {
                    // 뒤집은 횟수
                    int cnt = Integer.bitCount(i) + Integer.bitCount(j);
                    min = Math.min(min, cnt); // 최솟값 갱신
                }
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    private static boolean isPossible(int[][] beginning, int[][] target, int row, int col) {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                int v = beginning[i][j]; // 뒤집기 전의 값
                // 뒤집은 row에 해당한다면
                if (((1 << i) & row) != 0) {
                    v ^= 1; // 뒤집기
                }
                // 뒤집은 col에 해당한다면
                if (((1 << j) & col) != 0) {
                    v ^= 1; // 뒤집기
                }
                // 결과가 target의 해당 값과 동일하지 않다면 false 반환
                if (target[i][j] != v)
                    return false;
            }
        return true;
    }
}