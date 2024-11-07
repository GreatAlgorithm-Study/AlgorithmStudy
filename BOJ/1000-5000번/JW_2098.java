import java.util.Arrays;

public class JW_2098 {
    static int n;
    static int[][] distance; // 각 거리를 정보
    static int[][] dp; // 메모이제이션을 위한 DP배열
    static int INF = Integer.MAX_VALUE >> 2; // 적절한 최댓값

    public static void main(String[] args) throws Exception {
        n = read();
        distance = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                int d = read();
                // 거리 정보가 없는 경우에는 최댓값으로 설정
                if (d == 0)
                    d = INF;
                distance[i][j] = d;
            }
        dp = new int[n][1 << n];
        // dp배열 초기화
        for (int i = 0; i < n; i++)
            Arrays.fill(dp[i], -1);
        // 0번 도시에서 출발하는 외판원 순회
        int result = tsp(0, 1);
        System.out.println(result);
    }

    // 외판원 순회
    // @param city 현재 도시
    // @param visited 비트마스킹 방문처리
    private static int tsp(int city, int visited) {
        // 모든 도시를 방문했다면
        if (visited == (1 << n) - 1)
            // 원래 도시로 돌아가는 거리를 반환
            return distance[city][0];
        // 미리 계산된 최솟값이 있다면 반환
        if (dp[city][visited] != -1)
            return dp[city][visited];
        int result = INF; // 해당 도시를 방문했을 때 최솟값을 구하기 위한 변수
        for (int nextCity = 0; nextCity < n; nextCity++) {
            // 방문한 도시라면 건너뜀
            if ((visited & (1 << nextCity)) != 0)
                continue;
            // 다음 도시를 방문
            int temp = distance[city][nextCity] + tsp(nextCity, visited | (1 << nextCity));
            // 해당 도시에서 가질 수 있는 최솟값 갱신
            result = Math.min(result, temp);
        }
        // 계산된 값으로 메모이제이션
        dp[city][visited] = result;
        return result;
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