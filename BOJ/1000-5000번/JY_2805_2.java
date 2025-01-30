import java.io.*;
import java.util.*;
public class JY_2805_2 {

	static int N, M;
	static int[] trr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		trr = new int[N];
		int maxH = Integer.MIN_VALUE;
		for(int i=0; i<N; i++) {
			trr[i] = Integer.parseInt(st.nextToken());
			maxH = Math.max(maxH, trr[i]);
		}
		
		// 구해야 하는 것: 자르는 최대 높이
		int H = findH(maxH);
		
		System.out.println(H);
	}
	public static int findH(int mh) {
		int s = 0;
		int e = mh;
		
		int ans = 0;
		while(s <= e) {
			int mid = (s + e) / 2;
			long total = 0;
			for(int i=0; i<N; i++) {
				if(mid < trr[i]) total +=(trr[i]-mid);
			}
			
			// 자르는 높이를 더 늘려서 총 길이를 줄여야 함
			if(total >= M) {
				ans = mid;
				s = mid + 1;
			} 
			// 자르는 높이를 더 줄여서 총 길이를 늘려야 함
			else {
				e = mid - 1;
			}
		}
		
		return ans;
	}

}
