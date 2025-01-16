import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 개똥벌레가 파괴해야하는 장애물의 최솟값과 그러한 구간이 총 몇 개 있는지
// 시간 복잡도 : O(H logN)
public class HW_3020_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int input = 0;
        int[] bottom = new int[N/2]; // 석순
        int[] top = new int[N/2]; // 종유석 (아래로 내려오는거)

        for(int i=0; i<N; i++){
            input = Integer.parseInt(br.readLine());
            if(i%2==0){ // 짝수이면 석순
                bottom[i/2] = input;
            } else{
                top[i/2] = input;
            }
        }
        Arrays.sort(bottom);
        Arrays.sort(top);
        // 떠올린 것 : 이분 탐색으로 석순/종유석 (홀/짝) 나눠서 탐색
        // 이분탐색 : 장애물 충돌 횟수를 구하는 문제니까 장애물의 순서 중요x
        // - 특정 높이에서 석순과 종유석이 몇개 부딪히는지 탐색 범위 줄여서 빨리 계산하기 위함
        // 참고한 것 : 이분탐색 구현과 석순 종유석 겹치는것 구현
        // 석순이랑 종유석 따로 배열 구현하면 겹치는 것 cnt안될 줄 알았는데
        // 따로 충돌 횟수 구해서 더해주니까 상관없었음

        // 1) 각 높이에서 부딪히는 장애물 횟수를 이분 탐색으로 구하기
        int min = N; // 장애물에 다 부딪히는 경우로 초기화(최솟값 찾는 변수는 항상 가장 큰 값으로 초기화하기)
        int cnt = 0; // 구간의 수

        for(int i=1; i<=H; i++){
            int bottomCrash = bottom.length - crash(bottom, H-i+1); // 석순 충돌 횟수 (장애물 사이 지나가야 부딪히니까 +1)
            int topCrash = top.length - crash(top, i); // 종유석 충돌 횟수 = 종유석 길이 - (개똥벌레가 i 높이에서 날아가다감)
            int total = bottomCrash + topCrash;

            if(total < min){ // 최소 충돌 횟수 갱신
                min = total;
                cnt=1;
            } else if(total==min){
                cnt++; // 같은 최솟값이면 횟수 증가
            }
        }
        System.out.println(min + " " + cnt);
    }
    private static int crash(int[] arr, int target){
        int left=0, right=arr.length-1;
        int ans = arr.length; // 안부딪히면 배열 길이만큼
        while(left<=right){
            int mid=(left+right)/2;
            if(target <= arr[mid]){
                ans = mid;
                right = mid-1;
            } else{
                left = mid+1;
            }
        }
        return ans;
    }
}