import java.util.*;

class Solution {
    static int[][] dp;
    static int[][] memo;

    private static int dfs(int depth, int res, int k) {
        if (depth > k) return 0;       // 모든 상담 유형 배정완하면 종료
        if (memo[depth][res] != -1) return memo[depth][res];    // 메모이제이션

        int wait = Integer.MAX_VALUE;
        for (int i = 1; i <= res; i++) {        // 현재 유형에 상담사 i명 배정 후 남은 상담사 배분
            wait = Math.min(wait, dfs(depth + 1, res - i + 1, k) + dp[depth][i]);     // 최소 대기시간 갱신, (이때 다음 유형에도 최소 1명의 상담사 보장)
        }
        return memo[depth][res] = wait;
    }
    public static int solution(int k, int n, int[][] reqs) {
        // 상담 유형별 리스트 생성
        List<List<Time>> category = new ArrayList<>();
        for (int i = 0; i <= k; i++) {
            category.add(new ArrayList<>());
        }

        // 상담 유형별 요청 분류
        for (int[] r : reqs) {
            category.get(r[2]).add(new Time(r[0], r[1]));
        }

        dp = new int[k + 1][n - k + 2];     // dp[i][j] (i: 상담유형, j:각 상담유형에서 가질 수 있는 최대 상담자 수)
        for (int i = 1; i <= k; i++) {      // 상담 유형 별로 반복
            for (int j = 1; j <= n - k + 1; j++) {      // 상담 유형별 상담자 수를 늘려가며 진행
                PriorityQueue<Integer> pq = new PriorityQueue<>();      // 상담 끝나는 시간 저장 최소 힙
                for (Time t : category.get(i)) {        // 해당 상담 유형의 요청들에 대해
                    if (pq.size() < j) {                // 상담인원 수가 상담자수보다 작으면 바로 상담
                        pq.offer(t.s + t.e);        // 상담 종료시간 큐 삽입
                        continue;
                    }

                    // 대기 후 상담 들어갈 경우
                    int done = pq.poll();        // 가장 빨리 끝나는 상담
                    int wait = Math.max(done - t.s, 0);  // 가장 빠른 상담(done)이 현재 요청시간보다 크면 대기 발생 (음수 값 0 처리)
                    pq.offer(t.s + t.e + wait);
                    dp[i][j] += wait;       // 총 대기시간 누적
                }
            }
        }

        // dp값을 이용해 최적의 상담사 배분하기
        memo = new int[k + 1][n - k + 2];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        return dfs(1, n - k + 1, k);
    }

    static class Time{
        int s, e;

        public Time(int s, int e) {
            this.s = s;
            this.e = e;
        }
    }
}