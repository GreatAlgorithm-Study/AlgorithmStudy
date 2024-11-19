import java.util.*;

class SB_42884 {
    public int solution(int[][] routes) {
        Arrays.sort(routes, ((o1, o2) -> o1[1]-o2[1]));
        
        int cnt = 1;
        int pos = routes[0][1];
        
        for(int[] r : routes){
            if(r[0] <= pos) continue;
            pos = r[1];
            cnt++;
        }
        return cnt;
    }
}