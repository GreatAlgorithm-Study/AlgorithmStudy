class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        int T = times.length;
        
        // 가장 시간이 오래걸리는 심사관
        long maxTime = 0;
        for(int i=0; i<T; i++){
            maxTime = Math.max(maxTime, times[i]);
        }
        
        long s = 1;
        long e = maxTime*n;
        
        while(s <= e) {
            long mid = (s + e) / 2;
            // 현재 시간(mid)으로 심사할 수 있는 인원수 구하기
            long pCnt = calPeople(n, mid, times);
            
            // n명보다 많다면 가능하고, 시간 더 줄일 수 있음
            if(n <= pCnt) {
                answer = mid;
                e = mid - 1;
            } else{
                s = mid + 1;
            }
            
        }
        
        return answer;
    }
    public static long calPeople(int n, long t, int[] times) {
        long total = 0;
        for(int i=0; i<times.length; i++) {
            // 한명당 심사할 수 있는 인원 수: 실행시간 / 심사시간
            total += (t / times[i]);
            if(total > n) break;
        }
        return total;
    }
}