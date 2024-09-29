import java.util.Arrays;

/***
 * 알고리즘: 이분탐색
 * 시간복잡도: 1 ≤ distance ≤ 10^9 크기로 O(logN)
 * 아이디어:
 * 구간의 최소 거리를 이분탐색의 타겟으로 지정하고 탐색할 때, 시작점-끝점의 중간지점을 기준으로 최소거리를 구하는 이유?
 * 중간값은 최소 거리의 후보로 이분탐색을 위해 이 값을 기준으로 가능한 최소 거리인지 확인
 * 제거해야 하는 바위 수를 세는 이유?
 * 돌을 제거하지 않으면, 어떤 돌 사이의 거리가 너무 짧아 최소 거리를 크게 만들 수 없음
 */
public class YJ_43236 {
    public static void main(String[] args) {
        int distance = 25;
        int[] rocks = {2, 14, 11, 21, 17};
        int n = 2;
        System.out.println(solution(distance,rocks,n));
    }

    static int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        Arrays.sort(rocks);

        //중간값을 어떤걸로 정할것인가
        //어떤 기준에서 left 또는 right를 움직일 것인가?
        int left= 0;
        int right=distance;
        while(left<=right){
            int mid = (left+right)/2;

            int remove =0;
            int current=0;
            for(int i=0; i<rocks.length; i++){
                //다음 바위-현재바위가 현재 최소구간보다 작을 경우 최소값 중 큰값 후보에서 제외
                //제외 == 제거해야 하는 바위
                if(mid > rocks[i]-current){
                    remove++;
                    continue;
                }
                current = rocks[i];
            }

            //도착구간-마지막 바위
            if(mid > distance - current){
                remove++;
            }

            if(remove <= n){
                answer= Math.max(answer,mid);
                left = mid+1;   //더 큰값을 탐색해서 최소거리 최대화
            }else{
                right = mid-1;
            }
        }

        return answer;
    }

}
