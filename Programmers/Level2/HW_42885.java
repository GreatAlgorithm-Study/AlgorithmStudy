// 시간 복잡도 : o(n logn)
import java.util.*;
import java.io.*;

// 문제 목표 : 모든 사람을 구출하기 위해 필요한 구명보트 개수의 최솟값
// 최선의 선택 : 가장 무거운 사람을 먼저 태움
class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people); // 오름차순

        int left = 0; // 가장 가벼운 사람
        for(int right = people.length -1; right>= left; right--){
            if(people[left] + people[right] <= limit){ // 2명 태울 수 있는 경우
                left++;
                answer++;
            }
            // 1명만 태울 수 있는 경우
            else{
                answer++;
            }
        }
        return answer;
    }
}