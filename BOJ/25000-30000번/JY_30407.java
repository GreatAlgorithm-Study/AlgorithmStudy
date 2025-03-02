import java.io.*;
import java.util.*;
public class JY_30407 {

	static int N, H, D, K;
	static int[] drr;
	static int ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		drr = new int[N+1];
		for(int i=0; i<N; i++) {
			drr[i] = Integer.parseInt(br.readLine());
		}
		
		ans = 0;
		dfs(0, D, H, drr[0], false);
		if(ans <= 0) System.out.println(-1);
		else System.out.println(ans);
		
	}
	public static void dfs(int depth, int d, int h, int power, boolean isIgnore) {
		if(depth == N) {
			ans = Math.max(ans, h);
			return;
		}
		
		// 체력이 0보다 작으면 return;
		if(h <= 0) return;
		// ans보다 적으면 return;
		if(h <= ans) return;
		

		int np = 0;
		// 웅크리기
		np = calPower(power, d);
		dfs(depth+1, d, h-(np/2), drr[depth+1], isIgnore);
		
		// 네발로 걷기
		int nd = d + K;
		np = calPower(power, nd);
		dfs(depth+1, nd, h-np, drr[depth+1], isIgnore);
		
		
		// 깜짝놀라게하기
		if(!isIgnore) {
			np = calPower(power, d);			
			dfs(depth+1, d, h-np, 0, !isIgnore);
			
		}
		
	}
	public static int calPower(int r, int d) {
		return Math.max(0, r-d);
	}

}
