import java.util.Arrays;
import java.util.Comparator;

public class YJ_42884 {
    public int solution(int[][] routes) {
        Arrays.sort(routes, Comparator.comparingInt(o -> o[0]));

        int current = routes[0][1];
        int answer = 1;
        for(int i=1; i<routes.length; i++){
            if(current < routes[i][0]){
                answer++;
                current = routes[i][1];
            }else{  //current >= routes[i][0]
                current = Math.min(current, routes[i][1]);
            }
        }

        return answer;
    }
}
