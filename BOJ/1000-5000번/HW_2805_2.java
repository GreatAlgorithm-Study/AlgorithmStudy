import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 시간 복잡도 : N <= 10^6, M <=2*10^9, O(N logN)

class HW_2805_2{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        int s=0, e = 0;

        st = new StringTokenizer(br.readLine());
        for(int i=0;i <N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            e = Math.max(e, arr[i]);
        }

        Arrays.sort(arr);
        while(s<=e){
            int mid = (s+e)/2;
            long sum = 0;
            for(int h : arr){
                if(0 < h-mid) {
                    sum += h - mid;
                }
            }
            if(sum < M){
                e = mid-1;
            }
            else{
                s = mid + 1;
            }
        }
        System.out.println(e);
    }
}