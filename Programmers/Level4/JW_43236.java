import java.util.Arrays;
class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        Arrays.sort(rocks);         // 이분 탐색을 위한 정렬
        int l = 1, r = distance;    // 이분 탐색의 최소, 최대 경계 설정
        // 이분 탐색을 통해 최적의 최소 거리를 찾음
        while (l <= r) {
            int m = (l + r) / 2;    // 각 바위 사이의 최소 거리
            // 해당 거리로 지울 수 있는 바위 수 계산
            if (isPossible(distance, rocks, m, n)) {
                answer = m;         // 가능하면 그 거리를 정답으로 저장
                l = m + 1;          // 다음 탐색(최소 거리 중 최댓값)을 위해 왼쪽 경계 재설정
            } else
                r = m - 1;          // 불가능하면 더 작은 최소 거리를 탐색
        }
        return answer;
    }

    // 해당 target의 길이로 없앨 수 있는 바위의 개수를 반환
    private boolean isPossible(int distance, int[] rocks, int target, int n) {
        int now = 0;        // 시작 바위
        int removed = 0;    // 제거된 바위 수
        // 두 바위 사이의 거리가 target보다 작다면 없앰
        for (int i = 0; i < rocks.length; i++) {
            if (rocks[i] < now + target)
                removed++;
            else
                now = rocks[i]; // 거리가 충분하면 현재 바위를 기준으로 갱신
        }
        // 마지막 바위(distance)까지 거리도 확인
        if (distance < now + target)
            removed++;
        return removed <= n;    // 현재 거리로 가능했는지 반환
        // 부등호가 들어가는 이유는 아무거나 지워서 n개를 맞출 수 있어서 상관없음.
    }
}