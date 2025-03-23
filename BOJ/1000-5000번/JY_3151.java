import java.io.*;
import java.util.*;
public class JY_3151 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
//		System.out.println(Arrays.toString(arr));
		
        long ans = 0;
        
        // 3명으로 구성된 경우만 출전 가능
        for (int i = 0; i < N - 2; i++) {
            // 투 포인터 알고리즘으로 합이 -arr[i] 인 요소들을 구함
            int start = i + 1;
            int end = N - 1;
            while (start < end) {
                int s = arr[start] + arr[end];
                // 세 수의 합이 0이됨
                if (s == -arr[i]) {
                	// start와 end가 같으면 두 수 사이에 있는 조합을 구함
                    if (arr[start] == arr[end]) {
                        ans += (end - start);
                        start++;
                    } 
                    // start와 end가 같지 않으면 각각 이동 시켜 가능한 값 구함
                    else {
                        int a = start, b = end;
                        // start와 같은 최대 값을 찾기 위해 a증가
                        while (a < end && arr[a] == arr[start]) {
                            a++;
                        }
                        // end와 같은 최소 값을 찾기 위해 b감소
                        while (b > start && arr[b] == arr[end]) {
                            b--;
                        }
                        // 경우의 수 == (a ~ start의 개수) * (end ~ b의 개수)
                        ans += (a - start) * (end - b);
                        start = a;
                        end = b;
                    }
                } 
                // 두 수의 합이 -(첫번쨰 수)보다 작음 : 합 증가해야 함
                else if (s < -arr[i]) {
                    start++;
                } 
                // 두 수의 합이 -(첫번째 수)보다 큼 : 합을 감소해야 함
                else {
                    end--;
                }
            }
        }
        
        System.out.println(ans);
		
	}

}
