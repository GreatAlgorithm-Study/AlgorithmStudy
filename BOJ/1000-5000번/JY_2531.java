import java.util.*;
import java.io.*;

public class JY_2531 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
//		System.out.println(Arrays.toString(arr));
		
		Map<Integer, Integer> cMap = new HashMap<>();
		// 초기화
		for(int i=0; i<K; i++) {
			if(cMap.getOrDefault(arr[i], 0) == 0) {				
				cMap.put(arr[i], 1);
			} else {
				cMap.put(arr[i], cMap.get(arr[i])+1);
			}
		}
		
		if(cMap.getOrDefault(C, 0) != 0) {
			cMap.put(C, cMap.get(C)+1);
		} else {
			cMap.put(C, 1);
		}
//		System.out.println(cMap);
		
		
		int ans = cMap.keySet().size();
		for(int i=0; i<N; i++) {
			// arr[i] 제거
			cMap.put(arr[i], cMap.get(arr[i])-1);
			if(cMap.get(arr[i]) == 0) {
				cMap.remove(arr[i]);
			}
			// arr[i+K] 추가
			int j = (i+K) % N;
			if(cMap.getOrDefault(arr[j], 0) == 0) {
				cMap.put(arr[j], 1);
			} else {
				cMap.put(arr[j], cMap.get(arr[j])+1);
			}
//			System.out.println(cMap);
			
			ans = Math.max(ans, cMap.keySet().size());
		}
		System.out.println(ans);

	}

}
