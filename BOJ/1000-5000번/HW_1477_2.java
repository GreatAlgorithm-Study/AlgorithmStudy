import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/*
휴게소가 없는 구간의 길이(mid)의 최댓값을 최소로
구간의 최대 길이가 mid 이하가 되도록 휴게소 설치
구간의 최대 길이(mid)를 최소화 해야함
각 구간의 길이가 mid 이하가 되도록, mid 기준으로 휴게소를 M개 이하로 설치 가능한지 확인

*/
public class HW_1477_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int[] arr = new int[N+2];
        arr[0] = 0;
        arr[N+1] = L;
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        int left = 1; // by zero
        int right = L;

        while(left<=right){
            int mid = (left+right)/2; // 구간의 최대 길이
            int cnt = 0;
            for(int i=1; i<arr.length; i++) {
                int temp = arr[i] - arr[i - 1]; // 두 휴게소 사이의 거리
                cnt += (temp-1) / mid; // if(temp<mid)로 설정해서 휴게소 설치 개수 고려하지 못했음
                // 중간에 휴게소를 추가해서 mid 이하가 되도록 쪼갬
                // -1 : 휴게소 양끝에 존재, 중간에 추가로 설치해야 할 개수 cnt
            }
            if(M<cnt){
                left = mid+1;
            } else{
                right = mid-1;
            }
        }
        System.out.println(left);
    }
}
