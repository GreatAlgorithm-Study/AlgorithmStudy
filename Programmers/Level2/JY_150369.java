import java.util.*;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        int i = n-1;
        while(i >= 0) {
            System.out.println("i: "+i);
            System.out.println("d: "+Arrays.toString(deliveries));
            System.out.println("p: "+Arrays.toString(pickups));
            
            // 배달과 수거 모두 끝난 집은 pass
            if(deliveries[i] ==0 && pickups[i] ==0){
                i--;
                continue;
            }
            
            // 현재 가장 먼 집위치
            int start = i+1;
            
            // 배달하기
            int capD = cap;
            int d = i;
            while(d >= 0) {
                if(capD < deliveries[d]) {
                    deliveries[d] -= capD;
                    break;
                }
                capD -= deliveries[d];
                deliveries[d] = 0;
                d--;
            }
            
            // 수거하기
            int capP = cap;
            int p = i;
            while(p >= 0) {
                if(capP < pickups[p]) {
                    pickups[p] -= capP;
                    break;
                }
                capP -= pickups[p];
                pickups[p] = 0;
                p--;
            }
            
            answer += (start*2);
        }
        
        return answer;
    }
}