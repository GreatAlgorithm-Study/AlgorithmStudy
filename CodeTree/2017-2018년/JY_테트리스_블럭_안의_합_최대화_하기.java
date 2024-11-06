import java.util.*;
import java.io.*;

public class JY_테트리스_블럭_안의_합_최대화_하기 {
	
	static int N, M;
	static int[][] g;
	static boolean[][] visited;
	static int ans;
	// 현재 그래프 값 중 가장 큰 것
	static int maxValue;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		g = new int[N][M];
		maxValue = Integer.MIN_VALUE;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				g[i][j] = Integer.parseInt(st.nextToken());
				maxValue = Math.max(maxValue, g[i][j]);
			}
		}
		
		visited = new boolean[N][M];
		ans = Integer.MIN_VALUE;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				visited[i][j] = true;
				dfs(i, j, 1, g[i][j]);
				visited[i][j] = false;
			}
		}
		
		System.out.println(ans);

	}
	public static boolean inRange(int x, int y) {
		return x>=0 && x<N && y>=0 && y<M;
	}
	public static void dfs(int x, int y, int depth, int total) {
		// 4개의 블록 모두 탐색
		if(depth == 4) {
			ans = Math.max(ans, total);
			return;
		}
		// 가지치기
		// 현재까지 탐색한 결과에 앞으로 최댓값만 추가한다고 해도 ans값보다 작으면 탐색X
		if(ans >= total+maxValue*(4-depth)) return;
		
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(!inRange(nx, ny)) continue;
			if(visited[nx][ny]) continue;
			// 2번째 블럭인 경우, ㅏ ㅓ ㅗ ㅜ 처럼 2번째 블록에서 연결된 2개의 블록을 찾아야한다.
			if(depth == 2) {
				visited[nx][ny] = true;
				dfs(x, y, depth+1, total+g[nx][ny]);
				visited[nx][ny] = false;
			}
			
			// 2번쨰 블록 이외에는 일반탐색
			visited[nx][ny] = true;
			dfs(nx, ny, depth+1, total+g[nx][ny]);
			visited[nx][ny] = false;
			
		}
	}

}
