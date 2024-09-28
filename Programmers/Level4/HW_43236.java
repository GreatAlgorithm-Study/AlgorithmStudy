// 시간 복잡도 : N=100,000, O(N^2) 불가
// O(100,000 log100,000) = O(M logN)
// 사용할 자료구조 : 이분 탐색

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class HW_43236 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] nArr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            nArr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nArr);// 오름차순 정렬 O(nlog n)

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++){
            int target = Integer.parseInt(st.nextToken());
            int start = 0;
            int end = N -1;
            boolean find = false;
            while(start <= end){
                int mid = (start + end) / 2;
                int midIndex = nArr[mid];
                if(midIndex > target){
                    end = mid -1;
                }
                else if(midIndex < target){
                    start = mid + 1;
                }
                else{
                    find = true;
                    break;
                }
            }
            if(find){
                System.out.println(1);
            }
            else System.out.println(0);
        }

        // low, high, target을 mArr 원소로
    }
}

