// 시간 복잡도 : s <= 10^8 O(N) 이하

// 최고의 집합을 구하는 문제
// 최고의 집합 조건1 : 각 원소의 합이 S
// 조건2 : 원소의 곱이 최대가 되는 집합
class HW_12938 {
    public int[] solution(int n, int s) {
        int[] answer = new int[n];

        // 최고집합이 존재하지 않는 경우
        if(n>s){
            return new int[] {-1}; // -1 출력
        }

        int r = s%n;
        for(int i=n-1; i>=0; i--){ // 큰값을 배열 뒤쪽에 채워줌
            answer[i] = s/n;
            if(r>0){
                answer[i]++;
                r--;
            }
        }
        return answer;
    }
}
