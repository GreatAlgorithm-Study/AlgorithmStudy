import java.util.*;

class SB_214289 {
    static int INF = 100 * 1000 + 1;        // 가장 많은 에너지 소비
    public static int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        int N = onboard.length;
        int[][] dp = new int[N][51]; // dp[i][j] = k : i분에 j도로 만들 수 있는 최소 전력 k

        // 음수 처리를 위해 +10
        temperature += 10;
        t1 += 10;
        t2 += 10;

        // dp 배열 초기값 설정
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], INF);
        }

        // 시작 온도는 실외 온도로 초기화
        dp[0][temperature] = 0;

        for (int i = 0; i < N - 1; i++) { // i분
            int st = onboard[i] == 1 ? t1 : 0; // 현재 온도의 변화 범위 설정 (탑승자가 있을 땐 t1 ~ t2 값)
            int ed = onboard[i] == 1 ? t2 : 50; // 마지막 승객 탑승 시에는 적정 온도를 고려

            for (int j = 0; j <= 50; j++) {
                if (j < st || j > ed) continue; // 현재 온도가 적정 범위가 아닐 경우 건너뛰기

                // 온도를 1도 낮추기
                if (j > 0) {
                    int cost = (j - 1 < temperature) ? a : 0; // 외부온도보다 낮출 온도가 낮아서 온도조절이 필요하면 a비용 들어감
                    dp[i + 1][j - 1] = Math.min(dp[i + 1][j - 1], dp[i][j] + cost);     // 그 다음 시간에 온도를 내린 비용은, 기존 최소 비용과 현재 최소 전력비용에 cost더한 값 중 작은 값
                }

                // 온도를 유지
                int stayCost = (j == temperature) ? 0 : b; // 에어컨 끈 상태에서 비용
                dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + stayCost);             // 이전에 계산한 기존 비용 vs 온도 유지시 비용

                // 온도를 1도 높이기
                if (j < 50) {
                    int cost = (j + 1 > temperature) ? a : 0; // 외부온도보다 높일 온도가 높아서 온도조절이 필요하면 a비용 들어감
                    dp[i + 1][j + 1] = Math.min(dp[i + 1][j + 1], dp[i][j] + cost);     // 그 다음 시간에 온도를 올린 비용은, 기존에 계산해놓은 비용과 현재 온도를 높인 비용 중 최소 값
                }
            }
        }

        // 마지막 온도의 최소 비용 계산
        int mn = INF;
        for (int j = 0; j <= 50; j++) {
            if (onboard[N - 1] == 1 && (j < t1 || j > t2)) continue; // 마지막 승객이 탑승해 있는데 적정온도 범위 넘으면 패쓰
            mn = Math.min(mn, dp[N - 1][j]);
        }

        return mn;
    }
}
