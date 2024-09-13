import java.util.HashSet;

/**
 * 알고리즘: HashSet
 * 시간복잡도: O(n)
 * 아이디어:
 * 전체 토핑 수를 기준으로 철수와 동생이 각각 토핑을 가져간 수를 카운팅했을 때
 * 철수(왼쪽)   [1,2,2,3,3,4,4,4]
 * 동생(오른쪽)  [4,4,4,4,3,3,2,1]
 * 철수가 왼쪽에서 토핑 하나를 가져갔을 때 동생은 오른쪽을 기준으로 +1 개까지만 토핑을 가져갈 수 있다
 * (동생의 토핑수를 담은 배열 정렬은 뒤에서 부터 자름)
 * 따라서 n(철수) == n+1(동생) 이 같을 때 평등하게 토핑을 나눌 수 있다
 */
public class YJ_롤케이크_자르기 {
    public static void main(String[] args) {
        int[] topping = {1, 2, 1, 3, 1, 4, 1, 2};
        System.out.println(solution(topping));
    }

    static int solution(int[] topping) {
        int n = topping.length;
        HashSet<Integer> toppingSet = new HashSet<>();  //HashSet<토핑갯수> 토핑 중복제거

        int[] left = new int[n];
        for(int i=0; i<n; i++){ //O(n)
            toppingSet.add(topping[i]);
            left[i] = toppingSet.size();
        }

        toppingSet.clear();
        int[] right = new int[n];
        for(int i=left.length-1; i>=0; i--){    //O(n)
            toppingSet.add(topping[i]);
            right[i] = toppingSet.size();
        }

        int answer = 0;
        for(int i=0; i<n-1; i++){   //O(n)
            if(left[i] == right[i+1]){
                answer++;
            }
        }
        return answer;
    }
}
