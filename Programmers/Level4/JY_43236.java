import java.util.*;
class JY_43236 {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        List<Integer> rList = new ArrayList<>();
        for(int i=0; i<rocks.length; i++) {
            rList.add(rocks[i]);
        }
        rList.add(distance);        // 도착지점 추가
        Collections.sort(rList);
        
        int s = 0;
        int e = distance;
        while(s <= e) {
            int mid = (s + e) / 2;
            
            int cnt = 0;    // 제거해야할 바위 수
            int pre = 0;    // 바로 직전의 바위
            for(int r: rList) {
                int dist = r - pre;
                // 두 바위 사이거리가 최소 거리보다 작음 => 안됨, 제거
                if(dist < mid) cnt++;
                else pre = r;
                
                if(cnt > n) break;
            }
            
            // 없애야할 바위가 n보다 작거나 같으면 정답 저장
            if(cnt <= n) {
                answer = mid;
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }
        
        return answer;
    }
}

