import java.util.Arrays;

class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times); // 이분 탐색을 위한 정렬
        long l = 1, r = (long) times[times.length - 1] * n; // 가능한 최소, 최대 시간
        // 매개 변수 탐색
        while (l <= r) {
            long m = (l + r) / 2;  // 매개 변수
            if (isPossible(times, n, m)) { // 결정 함수
                // 최솟값을 찾기 위해 왼쪽 탐색
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return l; // Lower Bound 반환
    }

    // 결정 함수 : target 시간에 각각의 심사관이 몇 명을 심사할 수 있는지
    private boolean isPossible(int[] times, int n, long target) {
        long cnt = 0;
        for (int time : times) {
            cnt += target / (long) time;
        }
        return cnt >= n; // n명 이상 심사했다면 true
    }
}