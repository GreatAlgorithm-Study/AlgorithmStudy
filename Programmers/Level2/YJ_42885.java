import java.util.*;

/**
 * 알고리즘: 투포인터 O(NlogN)
 * 시간복잡도: 1 <= N <= 50,000
 * 최악의 경우 모든 지점을 다 거치면 O(N)
 * 정렬 필요 O(NlogN)
 * 아이디어:
 * 맨 앞 가벼운 사람 + 맨 뒤 무거운 사람이 동승 가능한지 체크
 */
public class YJ_42885 {
    public static void main(String[] args) {
        int[] people = {70, 80, 50};
        int limit = 100;
        System.out.println(getBoatCount(people, limit));
    }

    static int getBoatCount (int[] people, int limit) {
        int boat = 0;

        Arrays.sort(people);
        int start = 0;
        for(int end=people.length-1; start<=end; end--){
            //기준값보다 작거나 같을 경우
            if(people[start] + people[end] <= limit){
                start++;
            }
            boat++;
        }
        return boat;
    }

}
