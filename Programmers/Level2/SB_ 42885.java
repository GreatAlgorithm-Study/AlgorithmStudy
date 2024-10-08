import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);

        int left = 0;
        int right = people.length-1;

        int cnt = 0;
        while (left <= right) {
            if (people[left]+people[right] <=limit){
                left++;
            }
            right--;
            cnt++;
        }
        return cnt;
    }
}