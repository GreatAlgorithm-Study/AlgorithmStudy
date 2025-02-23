import java.util.*;

class JM_389479 {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int[] server = new int[players.length];
        
        for(int i = 0; i < players.length; i++) {
            if(i > 0) server[i] += server[i - 1];
            
            int cnt = players[i] / m - server[i];
            if(cnt > 0) {
                answer += cnt;
                server[i] += cnt;
                if(i + k < players.length) server[i + k] -= cnt;
            }
        }
        
        return answer;
    }
}