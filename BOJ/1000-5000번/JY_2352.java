import java.io.*;
import java.util.*;

public class JY_2352 {
	
	static int N;
	static List<Integer> lines;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		lines = new ArrayList<>();
		lines.add(arr[0]);
		
		for(int i=1; i<N; i++) {
			if(lines.get(lines.size()-1) < arr[i]) {
				lines.add(arr[i]);
			} else {
				// 이분 탐색으로 적절한 위치 찾고 삽입
				int idx = findPos(arr[i]);
				lines.set(idx, arr[i]);
			}
		}
		
		int ans = lines.size();
		System.out.println(ans);

	}
	public static int findPos(int num) {
		int s = 0;
		int e = lines.size()-1;
		int ans = 0;
		while(s <= e) {
			int mid = (s + e) / 2;
			if(lines.get(mid) >= num) {
				ans = mid;
				e = mid - 1;
			} else {
				s = mid + 1;
			}
		}
		
		return ans;
	}
	

}
