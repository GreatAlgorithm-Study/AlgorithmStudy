import java.util.*;

class SB_214288 {
    static int[][] memo;
    static int[][] dp;
    static int INF = Integer.MAX_VALUE;

    public static int solution(int k, int n, int[][] reqs) {
        List<List<Node>> counsel = new ArrayList<>();
        for (int i = 0; i <= k; i++) {
            counsel.add(new ArrayList<>());
        }

        for (int[] r : reqs) {
            counsel.get(r[2]).add(new Node(r[0], r[1]));
        }

        dp = new int[k+1][n-k+2];       // dp[i][j]: (i:상당유형, j:상담자 수), 최대 상담자 수= n-k+2(현재 유형(1)+0부터시작(1))
        for (int i = 1; i <= k; i++) {          // 상담유형 별로, 1~최대 상담자 수일때 걸리는 총 시간 구하기
            for (int j = 1; j <= n - k + 1; j++) {
                PriorityQueue<Integer> pq = new PriorityQueue<>();
                for (Node nd : counsel.get(i)){
                    if (pq.size() < j){          // 큐 크기는 상담 수만큼 유지 되어아햠 (안기다리고 바로 상담 들어감)
                        pq.offer(nd.s + nd.e);
                        continue;
                    }
                    int cur = pq.poll();                        // 기다리고 들어갈때
                    int wait = Math.max(cur - nd.s, 0);         // wait은 참가자 신청 시작보다 앞타임이 늦게 끝날때만 발생
                    pq.offer(nd.s + nd.e + wait);
                    dp[i][j] += wait;     // 기다린 시간 더하기
                }
            }
        }

        // 조합으로 최소 상담 배분시간 찾기
        memo = new int[k+1][n-k+2];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        return dfs(1, n-k+1, k);
    }

    private static int dfs(int depth, int cns, int k){      // 깊이(상담유형), 남은 상담자 수, 상담유형 개수
        if (depth > k) return 0;

        if (memo[depth][cns]!=-1) return memo[depth][cns];

        int cnt = INF;
        for (int i = 1; i <= cns; i++) {        // 다음 유형에 남은 상담자 분배, 현재 유형에 상담자 i명 할당했을때 대기시간 더해
            cnt = Math.min(cnt, dfs(depth + 1, cns - i+1, k) + dp[depth][i]);
        }
        return memo[depth][cns] = cnt;
    }

    private static class Node{
        int s, e;

        public Node(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "s=" + s +
                    ", e=" + e +
                    '}';
        }
    }
}