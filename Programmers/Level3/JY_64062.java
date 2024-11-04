class Solution {
    public int solution(int[] stones, int k) {
        int answer = 0;
        
        int s = 0;
        int e = Integer.MAX_VALUE;
        // stones[i] <= 200,000,000 이므로 max로 초기화
        
        while(s <= e) {
            int mid = (s+e) / 2;
            
            if(canGo(mid, stones, k)) {
                answer = mid;
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }
        
        return answer;
    }
    public static boolean canGo(int mid, int[] stones, int k) {
        int cnt = 0;    // 건널 수 없는 돌의 개수
        
        for(int i=0; i<stones.length; i++) {
            if(stones[i] - mid < 0) {
                cnt++;
            } else {
                cnt = 0;
            }
            
            // 건널 수 없는 돌의 개수가 연속으로 k개 이상되면 불가능
            if(cnt >= k) return false;
        }
        
        return true;
    }
}