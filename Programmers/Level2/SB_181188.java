import java.util.*;

class SB_181188 {
    public int solution(int[][] targets) {
        Arrays.sort(targets, ((o1, o2) -> o1[1]-o2[1]));
    
        int cnt = 1;
        int pos = targets[0][1];
        for (int[] t : targets) {
            if (t[0] < pos) continue;
            pos = t[1];
            cnt++;
        }
        return cnt;
    }
}