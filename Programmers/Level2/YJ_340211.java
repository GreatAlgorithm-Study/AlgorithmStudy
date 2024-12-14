import java.util.*;
// 구현 문제
// FIXME: 테스트케이스 전체는 통과 못해서 오류 수정 중입니다...
public class YJ_340211 {
    class Robot {
        List<Pos> route;
        int start;
        int end;

        public Robot(int start, int end){
            this.route = new ArrayList<>();
            this.start = start;
            this.end = end;
        }
    }

    class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj == null || getClass() != obj.getClass()){
                return false;
            }
            if(this == obj){
                return true;
            }
            Pos pos = (Pos) obj;
            return this.x == pos.x && this.y == pos.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x,y);
        }
    }

    List<Robot> robotList = new ArrayList<>();
    public int solution(int[][] points, int[][] routes) {
        //초기화
        for (int i = 0; i < routes.length; i++) {
            int start = routes[i][0] - 1;
            int end = routes[i][1] - 1;
            robotList.add(new Robot(start, end));
            //로봇의 시작위치 저장
            int[] point = points[start];
            robotList.get(i).route.add(new Pos(point[0], point[1]));
        }
        //각 로봇들의 모든 이동경로 저장
        int longestRoute = 0;
        for(Robot robot : robotList){
            saveRoute(points, robot);
            longestRoute = Math.max(longestRoute, robot.route.size());
        }
        //경로 충돌 찾기
        return findCollision(longestRoute);
    }

    //모든 로봇의 초 마다 경로를 저장
    void saveRoute(int[][] points, Robot robot){
        int[] startPoint = points[robot.start];
        int[] endPoint = points[robot.end];

        //x 좌표 먼저 이동
        int x = startPoint[0];
        while(x != endPoint[0]){
            x = x < endPoint[0] ? x+1 : x-1;
            robot.route.add(new Pos(x,startPoint[1]));
        }
        //y 좌표 이동
        int y = startPoint[1];
        while(y != endPoint[1]){
            y = y < endPoint[1] ? y+1 : y-1;
            robot.route.add(new Pos(x,y));
        }
    }

    int findCollision(int longestRoute){
        int collision = 0;

        for(int i=0; i<longestRoute; i++){
            Map<Pos, Integer> map = new HashMap<>();
            //i 초동안 움직였을 때 모든 로봇들의 충돌 카운팅
            for(Robot robot : robotList){
                Pos pos = robot.route.size() > i ? robot.route.get(i) : null;
                if(Objects.isNull(pos)){
                    continue;
                }
                map.put(pos, map.getOrDefault(pos, 0) + 1);
            }

            for(Integer count : map.values()){
                if(count > 1){
                    collision++;
                }
            }
        }

        return collision;
    }
}
