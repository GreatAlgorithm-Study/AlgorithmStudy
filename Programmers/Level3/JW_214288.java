import java.util.ArrayList;
import java.util.PriorityQueue;
class Solution {
    
    int minWaitingTime = Integer.MAX_VALUE; // 최소 대기 시간
    ArrayList<ArrayList<int[]>> refineReqs; // 기존의 상담 요청을 가공해서 저장할 리스트
    int[][] waitingTimes;                   // 각 유형별 인원에 따른 상담 시간을 저장할 배열
    int[] consultCombi;                     // 멘토의 조합을 만들 배열

    public int solution(int k, int n, int[][] reqs) {
        refineReqs = new ArrayList<>();
        consultCombi = new int[k + 1];
        waitingTimes = new int[k + 1][n - k + 2];
        for (int i = 0; i < k + 1; i++)
            refineReqs.add(new ArrayList<>());
        // 해당 유형의 요청별로 시작, 종료 시간만 남김
        for (int[] req : reqs) {
            refineReqs.get(req[2]).add(new int[] { req[0], req[1] });
        }
        calWaitingTime(k, n);       // 모든 대기 시간 계산
        makeConsultCombi(k, n, 1);  // 만들 수 있는 모든 멘토의 조합을 구함
        return minWaitingTime;
    }

    // 모든 대기 시간 계산
    private void calWaitingTime(int k, int n) {
        
        // i번 째 유형에 대해서
        for (int i = 1; i < k + 1; i++) {
            // j명의 인원이 있을 때의 총 대기 시간 계산
            for (int j = 1; j < n - k + 2; j++) {
                // 현재 상담하고 있는 인원이 끝나는 시간을 오름차순으로 저장할 큐
                PriorityQueue<Integer> pq = new PriorityQueue<>();
                int totalWaitingTime = 0;
                for (int[] req : refineReqs.get(i)) {
                    // 한가한 멘토가 있다면 상담 시작
                    if (pq.size() < j) {
                        pq.add(req[0] + req[1]);
                        continue;
                    }
                    // 아니라면 다음 인원이 기다리는 시간 및 상담이 끝나는 시간 계산
                    int now = pq.poll();
                    int waitingTime = now - req[0];
                    if (waitingTime > 0) {
                        totalWaitingTime += waitingTime;
                        pq.add(now + req[1]);
                    } else
                        pq.add(req[0] + req[1]);
                }
                // i번 째 유형의 멘토가 j명 있을 때 걸리는 총 대기 시간
                waitingTimes[i][j] = totalWaitingTime;
            }
        }
    }

    // 만들 수 있는 모든 멘토의 조합을 구함
    // @param remainig : depth번 째 유형에 사용할 수 있는 멘토의 수
    private void makeConsultCombi(int k, int remaining, int depth) {
        // 마지막 유형의 멘토까지 왔다면 남은 인원들 전부 추가 후 총 대기시간 계산
        if (depth == k) {
            consultCombi[depth] += remaining;
            int totalWaitingTime = 0;
            // 미리 계산한 대기 시간표로 총 대기 시간을 계산
            for (int i = 1; i < k + 1; i++) {
                totalWaitingTime += waitingTimes[i][consultCombi[i]];
            }
            minWaitingTime = Math.min(minWaitingTime, totalWaitingTime);
            consultCombi[depth] -= remaining;   // 백 트래킹
            return;
        }
        for (int i = 1; i < remaining; i++) {
            consultCombi[depth] += i;   // depth번 째 유형에 i만큼의 멘토를 추가
            makeConsultCombi(k, remaining - i, depth + 1);  // 남은 멘토들로 다음 유형 탐색
            consultCombi[depth] -= i;   // 백 트래킹
        }
    }
}