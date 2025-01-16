// 시간 복잡도 : distance <= 10^9, 이분 탐색으로 탐색 범위 줄여주기, O(logN)
// 바위 정렬 O(M log M)
// 각 바위 사이의 거리를 구하고, 이 사이 최솟값을 이분 탐색으로 찾기

// 바위 사이 거리의 최솟값 중 최댓값을 구하는 문제
// 바위 n개를 적절히 제거해서 가장 가까운 두 바위 사이의 거리가 최대한 멀어지도록 해야 함
// 매개변수 이분 탐색 : 어떤 값이 조건을 만족하는지 여부를 기준으로 범위를 좁혀가며 최적의 값을 찾음
// mid(바위 사이의 최소 거리)가 n(제거할 바위 수) 조건을 만족하는지 여부 기준으로 범위를 좁혀 나가며 거리의 최솟값 중 최댓값을 찾음
import java.util.*;

class HW_43236_2 {
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);

        int answer = 0;
        int left = 0; // 최소 거리
        int right = distance; // 최대 거리

        while(left <= right){
            // 바위 위치를 탐색 해줘야함
            int mid = (left+right)/2; // 바위 사이의 최소 거리 후보
            int removeCnt = 0; // 제거한 바위 수 cnt
            int prev = 0; // 이전 바위

            for(int i=0; i<rocks.length; i++){
                if(rocks[i]-prev<mid){ // 바위 사이의 거리를 구하고 mid보다 작을 경우
                    removeCnt++;   // 제거할 바위 수++
                } else{
                    prev = rocks[i]; // 바위 유지
                }
            }
            if(distance - prev < mid){ // 도착 지점까지의 거리 체크
                removeCnt++;
            }

            if(removeCnt<=n){ // 제거할 바위수가 넘지 않을 경우
                answer = mid;  // 정답값 갱신
                left = mid+1;
            } else{
                right = mid-1;
            }
        }
        return answer;
    }
}