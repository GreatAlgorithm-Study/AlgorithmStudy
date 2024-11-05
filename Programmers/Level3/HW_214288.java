import java.util.*;

class HW_214288 {
    public int solution(int k, int n, int[][] reqs) {
        int answer = Integer.MAX_VALUE; // 최소 대기 시간을 저장할 변수

        // 가능한 멘토 배정 조합 생성
        List<int[]> mentors = make(k, n);

        // 각 배정 조합에 대해 최소 대기 시간을 계산
        for (int i = 0; i < mentors.size(); i++) {
            int[] dist = mentors.get(i);
            int wait = simulation(dist, reqs);
            answer = Math.min(answer, wait);
        }
        return answer;
    }

    // 멘토 배정 조합 생성
    private List<int[]> make(int k, int n) {
        List<int[]> result = new ArrayList<>();
        combination(new int[k], 0, n - k, result); // n명의 멘토를 k개의 유형에 최소 한 명씩 배정
        return result;
    }

    // 재귀적으로 멘토 배정 조합을 생성
    private void combination(int[] dist, int idx, int remaining, List<int[]> result) {
        if (idx == dist.length) {
            if (remaining == 0) {
                int[] newDist = new int[dist.length];
                for (int i = 0; i < dist.length; i++) {
                    newDist[i] = dist[i];
                }
                result.add(newDist);
            }
            return;
        }

        for (int i = 1; i <= remaining + 1; i++) {
            dist[idx] = i;
            combination(dist, idx + 1, remaining - (i - 1), result);
        }
    }

    // 주어진 멘토 배정 조합에 대해 각 상담 유형별 대기 시간 합산
    private int simulation(int[] dist, int[][] reqs) {
        Map<Integer, PriorityQueue<Integer>> mentorMap = new HashMap<>();
        for (int i = 0; i < dist.length; i++) {
            mentorMap.put(i + 1, new PriorityQueue<>(dist[i]));
            for (int j = 0; j < dist[i]; j++) {
                mentorMap.get(i + 1).offer(0); // 초기 상담 가능 시간을 0으로 설정
            }
        }

        int total = 0; // 총 대기 시간

        // 상담 요청 순서대로 처리
        for (int i = 0; i < reqs.length; i++) {
            int start = reqs[i][0];  // 상담 요청 시간
            int ing = reqs[i][1];    // 상담 시간
            int type = reqs[i][2];   // 상담 유형
            PriorityQueue<Integer> mentors = mentorMap.get(type);

            int next = mentors.poll(); // 해당 유형의 멘토들 중 가장 빨리 상담 가능한 멘토 선택
            int wait = Math.max(0, next - start);  // 대기 시간 계산
            total += wait;

            mentors.offer(Math.max(start, next) + ing); // 멘토의 다음 상담 가능 시간 업데이트
        }

        return total;
    }
}