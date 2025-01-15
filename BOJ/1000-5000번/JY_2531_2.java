import java.io.*;
import java.util.*;

public class JY_2531_2 {
	
	static int N, D, K, C;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		int[] srr = new int[D+1];
		// 쿠폰 초밥
		srr[C] = 1;
		// 초기 연속 초밥
		for(int i=0; i<K; i++) {
			int s = arr[i];
			srr[s] += 1;
		}
		
		int ans = cal(srr);
		
		for(int i=0; i<N; i++) {
			int now = arr[i];
			int next = arr[(i+K)%N];
			srr[now]--;
			srr[next]++;
			ans = Math.max(ans, cal(srr));
		}
		
		System.out.println(ans);

	}
	public static int cal(int[] srr) {
		int cnt = 0;
		for(int i=1; i<D+1; i++) {
			if(srr[i] > 0) cnt++;
		}
		return cnt;
	}

}
