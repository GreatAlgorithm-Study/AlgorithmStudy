import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        
        // 시작점 기준으로 정렬
        Arrays.sort(targets, (o1, o2)->o1[0]-o2[0]);
        
        int N = targets.length;
        int bound = 0;              // 경계값
        for(int i=0; i<N; i++) {
            int s = targets[i][0];
            int e = targets[i][1];
            
            // 새로운 요격 발사해야 함
            if(s >= bound) {
                answer++;
                bound = e;
            } else {
                bound = Math.min(bound, e);     // 현재 요격으로 가능한 모든 범위의 미사일을 없애야 하므로 범위가 가장 작은 미사일로 저장
            }
        }
        
        return answer;
    }
}