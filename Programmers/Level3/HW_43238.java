// https://school.programmers.co.kr/learn/courses/30/lessons/43238
// 시간 복잡도 : n, m<= 10^9
// 모든 사람이 심사를 받는데 걸리는 시간의 최솟값
import java.util.*;
class HW_43238 {
    public long solution(int n, int[] times) {
        long answer = 0;
        Arrays.sort(times);
        long left = times[0];
        long right = times[times.length-1] * (long)n;// 최악의 시간
        while(left <= right){ // 끝나더라도 최소로 끝나는 시간이 있으니까 비교
            long mid = (left+right)/2;
            long target = 0;
            for(int i=0; i<times.length; i++){
                target += mid/times[i];
            }
            if(target < n){
                left = mid + 1;
            }
            else {
                right = mid -1;
                answer = mid;
            }
        }
        return answer;
    }
}