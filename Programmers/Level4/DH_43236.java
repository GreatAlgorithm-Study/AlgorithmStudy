
import java.util.*;

class DH_43236 {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        int s = 0, e = distance;

        Arrays.sort(rocks);

        // 각 지점 사이의 최소 거리를 조절하면서 정답 구하기
        while(s <= e) {
            int m = (s + e) / 2;

            // 최소 길이가 middle가 될 수 있을 때
            // 제거해야되는 최소 돌의 개수
            int tmp = removeCnt(rocks, m, distance);

            // 제거해야되는 돌의 개수가 주어진 값보다 같거나 작으면
            // 현재 값이 작아서 다른 큰 값들이 많다는 의미
            // 현재 값의 크기를 키워줘야 됨
            if(tmp <= n) {
                answer = m;
                s = m + 1;
            // 제거해야 되는 돌의 개수가 주어진 값보다 크다면
            // 해당 값보다 작은 값들이 많아서 작은 거리을 없애기 위해 돌을 많이 없애야 된다는 의미
            // 현재 값을 줄여줘야 됨
            } else {
                e = m - 1;
            }
        }
        return answer;
    }

    // 제거해야되는 바위 개수 세는 함수
    static int removeCnt(int[] rocks, int m, int distance) {

        // mid가 바위 간 최소 거리가 되어야 함
        int removeCnt = 0;
        int before = 0;

        for(int i = 0; i < rocks.length + 1; i++) {
            int current = i == rocks.length ? distance: rocks[i];

            // 두 지점 사이 간격이 m보다 작다면 해당 바위 제거해야 됨
            if(current - before < m) {
                removeCnt++;
                continue;
            }

            if(i == rocks.length) break;
            before = rocks[i];
        }

        return removeCnt;
    }
}