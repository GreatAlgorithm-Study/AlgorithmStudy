import java.io.*;
import java.util.*;

public class JY_1939 {

	static int N, M;
	static List<int[]>[] g;
	static boolean[] visited;
	static int ea, eb;
	static boolean isOk;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		g = new ArrayList[N+1]; 	
		for(int i=1; i<N+1; i++) {
			g[i] = new ArrayList<>();
		}
		
		int mc = 0;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			g[a].add(new int[] {b, c});
			g[b].add(new int[] {a, c});
			
			if(mc < c) {
				mc = c;
			}
		}
		
		st = new StringTokenizer(br.readLine());
		ea = Integer.parseInt(st.nextToken());
		eb = Integer.parseInt(st.nextToken());
		
		
		// 0~최대 중량 제한 중 최대값 찾기
		int s = 0;
		int e = mc;
		int ans = 0;
		while(s <= e) {
			int mid = (s + e) / 2;
			
			visited = new boolean[N+1];
			isOk = false;
			
			// 현제 중량제한 값(mid)으로 ea->eb로 갈 수 있는지 탐색
			canGo(ea, mid);
			if(isOk) {
				ans = mid;
				s = mid + 1;
			} else {
				e = mid - 1;
			}
			
		}
		
		System.out.println(ans);
	}
	public static void canGo(int now, int cost) {
		if(now == eb) {
			isOk = true;
			return;
		}

		visited[now] = true;
		
		for(int[] next : g[now]) {
			if(visited[next[0]]) continue;
			if(next[1] < cost) continue;
			
			canGo(next[0], cost);
		}
		
	}

}
