// 모든 차량이 한번은 단속용 카메라를 만나도록하는 최소 카메라 설치 개수
import java.util.*;
class HW_42884 {
    public int solution(int[][] routes) {
        Arrays.sort(routes, (a,b) -> Integer.compare(a[1], b[1])); // 나간 지점 기준 정렬

        // 단속카메라 탐색
        int cnt = 0; // 카메라 개수
        int endCamera= Integer.MIN_VALUE; // 처음엔 카메라 설치X

        for(int i=0; i<routes.length; i++){
            int s = routes[i][0]; // 진입 지점
            int e = routes[i][1]; // 진출 지점(나감)

            if(endCamera<s){ // 설치되지 않은 지점
                cnt++; // 카메라 설치
                endCamera = e; // 나가는 지점에 카메라 설치!
            }
        }
        return cnt;
    }
}