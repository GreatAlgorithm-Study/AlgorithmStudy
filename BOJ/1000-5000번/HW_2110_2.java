import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.*;

// 가장 인접한 두 공유기 사이의 최대 거리
class HW_2110_2{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 집의 개수
        int C = Integer.parseInt(st.nextToken()); // 공유기의 개수

        int[] arr = new int[N];
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        int s=1;
        int e = arr[N - 1] - arr[0];
        int ans = 0;

        while(s<=e){ // Upper Bound
            int start = arr[0];
            int cnt = 1; // 첫번째 집은 설치
            int mid = (s+e)/2; // 공유기 사이의 최소 거리
            for(int i=1; i<N; i++){
                if(mid <= arr[i]-start){
                    cnt++;
                    start = arr[i];
                }
            }
            if(C <= cnt){
                ans = mid;
                s = mid+1;
            } else e = mid-1;
        }
        System.out.println(ans);
    }
}
