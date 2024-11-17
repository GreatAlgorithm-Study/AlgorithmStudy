import java.util.*;

public class YJ_181188 {
    public int solution(int[][] targets) {
        int answer = 0;
        Arrays.sort(targets, (o1, o2) -> o1[0] - o2[0]);

        int rocket = 0;
        for(int i=0; i<targets.length; i++){
            int s = targets[i][0];
            int e = targets[i][1];

            if(rocket <= s){
                answer++;
                rocket = e;
            }else{  //rocket > s
                rocket = Math.min(rocket,e);
            }
        }
        return answer;
    }
}