import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 시간 복잡도 : N <= 10^6, M<= 10^9 -> 이분 탐색 시간 복잡도를 줄여줘야함
// M미터의 나무를 집에 가져가기 위해서 절단기에 설정할 수 있는 높이의 최댓값을 구하기 (H)
public class HW_2805 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int arr[] = new int[N];
        int s=0;
        int e=0;
        st = new StringTokenizer(br.readLine());

        // 나무 높이 입력 받음
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            if(e < arr[i]){ // 나무들 중 최댓값을 구하면 max 갱신
                e = arr[i];
            }
        }

        while(s < e){
            int m = (s+e)/2;
            long sum = 0;
            for(int height : arr){
                if(height-m >0) sum += (height - m);// 나무 자르기
            }
            if(sum < M) e = m; // 잘린 길이가 M보다 작을 시 더 절단 높이 더 낮춤
            else s = m+1;
        }
        System.out.println(s-1);
    }
}