// 시간복잡도 : N<=100,000 완전 탐색 불가
// 트럭 하나로 모든 배달과 수거를 마치고 물류창고까지 돌아올 수 있는 최소 이동 거리
// 모든 배달과 수거 -> 각 집 방문 -> 전체거리 최소화 -> 멀리있는 집부터 방문해서 돌아오기
class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int delivery = 0;
        int pickup = 0;
        for(int i=n-1; i>=0; i--){
            delivery += deliveries[i];
            pickup += pickups[i];
            while(delivery>0 || pickup>0){
                delivery-= cap;
                pickup -= cap;
                answer += (i+1) * 2;
            }
        }
        return answer;
    }
}