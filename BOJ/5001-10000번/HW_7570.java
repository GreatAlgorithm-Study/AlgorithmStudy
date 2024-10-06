import java.io.*;
import java.util.StringTokenizer;

// 시간복잡도 : O(N)
// N <= 10^6 -> O(N^2) 불가

// 어린이들 최소한 이동 후 오름차순 정렬 -> LIS(최장 증가 부분 수열)
// 이동해야하는 어린이 수 = 전체 어린이 수 - LIS
public class HW_7570{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[N+1];
        for(int i=0; i<N; i++){
            arr[Integer.parseInt(st.nextToken())] = i; // 배열에 각 번호 입력 받음
        }

        int LIS = 1;
        int cnt = 1;

        // 연속된 증가 수열의 길이 계산
        for (int i = 1; i <= N; i++) {
            if (arr[i] > arr[i - 1]) {  // 현재 위치의 값이 이전 위치의 값보다 크다면 (=연속된 수열)
                cnt++;  // 연속된 수열의 길이 증가
                LIS = Math.max(LIS, cnt);  // 최장 길이 LIS 갱신
            } else { // 연속되지 않으면
                cnt = 1;  // 길이 1로 초기화
            }
        }
        System.out.println(N - LIS); // 최소 이동해야하는 어린이 수
    }
}

