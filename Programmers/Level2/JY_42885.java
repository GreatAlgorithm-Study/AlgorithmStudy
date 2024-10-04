import java.util.*;
class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        int N = people.length;
        int f = 0;
        int b = N-1;
        
        Arrays.sort(people);    // 정렬: 가장 작은 무게와 가장 큰 무게를 함께 태워보기 위함
        
        int cnt = 0;            // 2명씩 태울 수 있는 방법의 수
        while(f < b) {
            // f번째와 같이 탈 수 있는 b가 있다면,
            // 2명 구명보트 수 증가 + 다음 구명보트 탐색
            if(people[f] + people[b] <= limit) {    
                f++;
                cnt++;
            }
            b--;
        }

        answer = N - cnt;
        return answer;
    }
}