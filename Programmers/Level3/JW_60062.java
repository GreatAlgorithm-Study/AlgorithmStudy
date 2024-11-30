import java.util.Arrays;

class JW_60062 {
    int w, d;
    int answer = Integer.MAX_VALUE;

    public int solution(int n, int[] weak, int[] dist) {
        w = weak.length;
        d = dist.length;
        Arrays.sort(dist); // 그리디한 풀이를 위해 정렬
        judge(0, 0, 0, n, weak, dist);
        return answer != Integer.MAX_VALUE ? answer : -1;
    }

    /*
     * @param depth: 깊이(몇 명을 사용했는가)
     * @param p: 마지막 탐색이 끝난 인덱스
     * @param visited: 방문처리
     */
    private void judge(int depth, int p, int visited, int n, int[] weak, int[] dist) {
        // 모든 외벽을 수리 했다면 갱신
        if (visited == (1 << w) - 1) {
            answer = Math.min(answer, depth);
            return;
        }
        // 종료 조건
        // 깊이가 최대거나 최선의 선택이 아닐 경우
        if (depth == d || depth >= answer - 1) {
            return;
        }
        // 많이 움직이는 친구부터 선택
        int move = dist[d - 1 - depth];
        for (int i = p; i < w; i++) {
            int next = visited;             // 다음 방문 배열
            int l = weak[i], r = l + move;  // 해당 친구가 탐색하는 범위
            int idx = i;
            // 수리가 가능한 범위까지 탐색
            while (isPossible(l, r, weak[idx], weak[idx] + n)) {
                // 이미 수리를 한 곳이라면 종료
                if ((next & (1 << idx)) == 1)
                    break;
                next |= (1 << idx);
                idx++;
                // 원형 배열이므로 인덱스 초기화
                if (idx >= w)
                    idx %= w;
            }
            // 다음 친구를 사용하여 탐색 진행
            judge(depth + 1, idx, next, n, weak, dist);
        }
    }

    // 현재 위치를 수리할 수 있는지 확인하는 함수
    private boolean isPossible(int l, int r, int point, int other) {
        return (l <= point && point <= r) || (l <= other) && (other <= r);
    }
}