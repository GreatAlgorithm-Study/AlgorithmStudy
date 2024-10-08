import java.util.Arrays;
class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people); // 투 포인터를 위한 정렬
        // 그리디한 방식으로 몸무게가 작은 사람 + 큰 사람을
        // 한 보트에 태울 수 있는지 투 포인터로 구현
        int l = 0, r = people.length - 1;
        while (l <= r) {
            // 작은 사람도 같이 태울 수 있는 지 확인
            if (people[l] + people[r] <= limit) {
                l++;
            }
            r--;
            answer++;
        }
        return answer;
    }
}