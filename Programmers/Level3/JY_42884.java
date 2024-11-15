import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        
        Arrays.sort(routes, (o1, o2)->o1[1]-o2[1]);
        
        int last = -30001;
        for(int [] route: routes) {
            if(route[0] > last) {
                answer++;
                last = route[1];
            }
        }
        
        return answer;
    }
}