class SB_60062 {
    static int N;
    static int[] Dist, expandWeak;
    static int M;
    static int ans = Integer.MAX_VALUE;;

    private static void check(int[] take) {
        for (int start = 0; start < expandWeak.length / 2; start++) {       // 취약 지점의 시작위치(인덱스)는 기존 배열의 범위 내 존재
            int cnt = 0;                                                    // 커버한 친구 수
            int pos = expandWeak[start];                                    // 현재 친구가 커버 시작할 위치
            for (int fd : take) {
                cnt++;
                pos += Dist[fd];                                            // 현재 친구가 마지막으로 커버한 위치 (현재 친구가 커버할 수 있는 최대 거리)
                // 현재 친구로 모든 지점 다 커버할 경우
                if (pos >= expandWeak[start + expandWeak.length / 2 - 1]) { // 시작 위치를 기점으로 취약구간의 길이만큼 조사(=모든 취약 지점 조사) 시
                    ans = Math.min(ans, cnt);                               // 최소값 갱신
                    return;
                }

                // 다음 친구 추가해야할 경우
                int nxtStart = start + 1;                                   // 다음 취약 지점의 인덱스 (expandWeak에서 커버되지 않은 첫번째 취약지점 가르키기)
                while (nxtStart < expandWeak.length / 2 && expandWeak[nxtStart] <= pos) {       // 취약 지점의 인덱스가 기존 배열의 인덱스에 위치하고, 이미 이전 친구가 커버했다면
                    nxtStart++;                                                                 // 포인터 이동하며 다음 취약 시작 지점 찾기
                }
                start = nxtStart - 1;                                       // 다음 친구가 커버 시작할 시작 지점 설정.
            }
        }
    }


    private static void perm(int depth, int cnt, boolean[] visited, int[] take) {       // 현재 시작점, 친구 수
        if (depth == cnt) {
            check(take);
            return;
        }
        for (int i = 0; i < M; i++) {
            if (visited[i]) continue;           // 이미 방문한 친구는 패쓰
            take[depth] = i;                    // 데려갈 수 있는 경우 데려가고
            visited[i] = true;
            perm(depth + 1, cnt, visited, take);     // 데려간 친구로 dfs 마져 수행
            visited[i] = false;                 // 다시 돌려놓기
        }
    }

    public static int solution(int n, int[] weak, int[] dist) {
        N = n;
        Dist = dist;
        M = dist.length;

        // 기존 배열 확장 (반시계도 일자로 진행하기 위함)
        expandWeak = new int[weak.length * 2];
        for (int i = 0; i < weak.length * 2; i++) {
            if (i < weak.length) expandWeak[i] = weak[i];
            else expandWeak[i] = weak[i%weak.length] + N;
        }

        for (int i = 1; i < M+1; i++) {                         // 데러갈 친구 수
            perm(0, i, new boolean[M], new int[i]);     // 1부터 M까지 경우에 대한 순열 구하기
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}