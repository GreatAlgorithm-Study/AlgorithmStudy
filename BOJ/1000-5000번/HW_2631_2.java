import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


// 나머지 길의 유지비의 합을 최소로
// 시간 복잡도 : 2<=N<=200,
// 번호 순서대로 줄을 세우는데 옮겨지는 아이들의 최소 수
// 어떻게해야 최소로 옮길 수 있을까?
// LIS 떠올리지 못함 ㅠㅠ
// LIS -> DP -> 시간 복잡도 O(N^2)
// 부분 수열의 순서를 유지한 채로 뽑은 수들이 증가하도록 하고 싶을 때 가장 많이 뽑을 수 있는 수의 개수 구하기
// 이미 오름차순 번호로 정렬된 아이들은 유지하면서, 나머지를 옮기면됨
// LIS에 속한 아이들은 옮길 필요가 없음
// 옮겨야 할 최소 아이 수 = 전체 아이들 - LIS에 속한 아이들
public class HW_2631_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 아이들의 수
        int[] arr = new int[N];
        int[] dp = new int[N]; // dp[i] : arr[i] i번째 원소를 마지막으로 하는 LIS 수열의 길이
        for(int i=0; i<N; i++){ // 아이 번호 1번~N번
            arr[i] = Integer.parseInt(br.readLine());
            dp[i] = 1; // LIST : 초기값 1
        }
        for(int i=0; i<N; i++){
            for(int j=0; j<i; j++){ // j: i이전 위치
                if(arr[j] < arr[i]){ // 이전 위치 < 현재 위치, i보다 앞에 있는 원소 탐색
                    dp[i] = Math.max(dp[i], dp[j]+1); // 가장 긴 증가 부분 수열의 길이를 갱신 [3, 5, 6]
                } // (dp에서 가장 큰 값 = LIS길이)
            }
        }
        int LIS = 0; // LIS
        for(int i=0; i<N; i++){
            LIS = Math.max(LIS, dp[i]);
        }
        System.out.println(N-LIS);
    }
}