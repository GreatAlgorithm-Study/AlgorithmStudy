import java.util.Arrays;
class JW_181188 {
    public int solution(int[][] targets) {
        int answer = 0;
        // 미사일의 마지막 위치로 오름차순 정렬
        Arrays.sort(targets, (o1, o2) -> o1[1] - o2[1]);
        int missile = 0; // 요격 미사일의 좌표
        for (int i = 0; i < targets.length; i++) {
            // 개구간 이기 때문에 등호
            // 해당 요격미사일로 요격할 수 있는지
            if (missile <= targets[i][0]) {
                answer++;
                // 가장 늦게 격추하는 것이 효율적이므로 마지막 값으로 갱신
                missile = targets[i][1];
            }
        }
        return answer;
    }
}