import java.util.Arrays;
class JW_42884 {
    public int solution(int[][] routes) {
        int answer = 0;
        Arrays.sort(routes, (o1, o2) -> o1[1] - o2[1]);
        int camera = Integer.MIN_VALUE;
        for (int i = 0; i < routes.length; i++) {
            if (camera < routes[i][0]) {
                answer++;
                camera = routes[i][1];
            }
        }
        return answer;
    }
}