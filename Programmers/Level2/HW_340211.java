import java.util.HashMap;
import java.util.Map;

class HW_340211 {
    Map<String, Integer> timePos;  // 시간, 위치별 충돌 횟수 관리
    Map<Integer, int[]> m; // 포인트 번호별 좌표 관리 (key 포인트 번호, value [r,c])
    int answer= 0;

    public int solution(int[][] points, int[][] routes) {
        m = new HashMap<>();
        timePos = new HashMap<>();

        for(int i = 0; i<points.length; i++){   // i+1로 포인트 번호를 매기고 해당 번호에 (r1,c1) 배열을 매핑
            m.put(i+1, new int[] {points[i][0], points[i][1]}); // [[r1, c1], [r2, c2]]
        }

        for(int i = 0; i<routes.length; i++){  // 각 route에 대해 위험도 계산
            simulation(routes[i]);
        }

        return answer;
    }
    public void simulation(int[] route){
        int time = 0;
        int[] last = {0,0};

        for(int i = 0; i<route.length - 1; i++){
            int[] startPos = m.get(route[i]);
            int[] targetPos = m.get(route[i + 1]);

            int startX = startPos[0];
            int startY = startPos[1];

            int targetX = targetPos[0];
            int targetY = targetPos[1];

            while(startX != targetX || startY != targetY){
                String key = time+"-"+startX+"-"+startY;
                timePos.put(key, timePos.getOrDefault(key, 0) + 1);

                if(timePos.get(key) == 2) {
                    answer++;
                }

                // 목표 지점에 가까워지는 방향으로 한 칸 이동
                if(startX < targetX)
                    startX++;
                else if(startX > targetX)
                    startX--;
                else if(startY < targetY)
                    startY++;
                else if(startY > targetY)
                    startY--;

                time++;
                last[0] = startX;
                last[1] = startY;
            }
        }
        String key = time+"-"+last[0]+"-"+last[1];
        timePos.put(key, timePos.getOrDefault(key, 0) + 1);

        if(timePos.get(key) == 2) { // 충돌 횟수++
            answer++;
        }
    }
}