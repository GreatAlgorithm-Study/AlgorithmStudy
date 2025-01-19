import java.io.*;
import java.util.StringTokenizer;
/*
시간 복잡도 : 어린이 수(N) <= 10^6, O(N^2) 시간 초과
O(N) 이하로 풀기

LIS : 부분 수열의 순서를 유지한 채로 뽑은 수들이 증가하도록 하고 싶을 때 -> 맨 앞, 맨 뒤로만 이동하기 때문에 제약O
이미 오름차순 번호로 정렬된 아이들은 유지하면서, 나머지 옮기기

구하는 것 : 번호순서대로 줄을 세울 때 맨 앞이나 맨 뒤로 보내는 어린이 수의 최솟값 찾기
어떻게 해야 최소로 옮길 수 있을까?
이미 오름차순 정렬된 번호 애들은 유지 , 1~N 연속된 증가 수열 찾기
dp[k] : k 번호일 때까지 연속 증가 수열의 개수
점화식 : dp[k] = dp[k-1] + 1
이전 숫자(k-1)가 존재하면 수열 길이에 1을 더해줌
존재하지 않으면 새로 시작 -> 1로 초기화
전체 어린이 수 - 가장 긴 연속 증가 부분 수열의 길이 (N - max(dp[i))

dp를 왜 사용하는지, dp 점화식
작은 문제 반복 : 현재 숫자(k)와 이전 숫자(k-1)이 있는지 확인
이전 숫자의 결과를 이용하면 반복을 줄일 수 있음
이전 숫자(k-1) 존재 유무를 확인해야 연속 증가 수열을 만들 수 있음
+1을 하는 이유 : 이전 수열에 현재 숫자 k를 추가하니까 길이 1증가
안하면 k 추가해도 길이가 늘어나지 않음 -> 계산 불가
 */
class HW_7570_2{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 어린이의 수

        int[] dp = new int[N+1]; // 고정크기니까 배열로
        int max = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){ // 처음에 줄서있는 어린이들의 번호를 입력 받음
            int k = Integer.parseInt(st.nextToken());
            dp[k] = dp[k-1] + 1;
            max = Math.max(max, dp[k]);
        }
        System.out.println(N-max);
    }
}


