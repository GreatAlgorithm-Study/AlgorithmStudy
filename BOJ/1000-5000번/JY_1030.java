import java.io.*;
import java.util.*;

public class JY_1030 {

	static int N, K;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int s = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int r1 = Integer.parseInt(st.nextToken());
		int r2 = Integer.parseInt(st.nextToken());
		int c1 = Integer.parseInt(st.nextToken());
		int c2 = Integer.parseInt(st.nextToken());
		
		for(int i=r1; i<=r2; i++) {
			for(int j=c1; j<=c2; j++) {
				System.out.print(find(s, 0, N, K, i, j));
			}
			System.out.println();
		}
		
	}
	public static boolean inRange(int n, int k, int x, int y) {
		int s = (n - k) / 2;
		int e = s + k;
		return x>=s && x<e && y>=s && y<e;
	}
	public static int find(int s, int depth, int n, int k, int x, int y) {
		int c = 0;	// 색깔 변수
		
		// 검정색 범위에 속함
		if(n != 0 && inRange(n, k, x % n, y % n)) {
			c = 1;
		} else {
			// 아직 시간이 남아있고 흰색이라면 더 탐색해보기
			if(s > depth) c = find(s, depth+1, N*n, N*k, x, y);
		}
		return c;
	}

}
