import java.io.*;
import java.util.*;
public class JY_16724 {
	
	static int N, M;
	static char[][] g;
	static int[] parent;
	// 상 하 좌 우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static Map<Character, Integer> cMap;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		g = new char[N][M];
		
		parent = new int[N*M];
		for(int i=0; i<N; i++) {
			String line = br.readLine();
			for(int j=0; j<M; j++) {
				g[i][j] = line.charAt(j);
				parent[i*M+j] = i*M+j;
			}
		}
		
		cMap = new HashMap<>();
		cMap.put('U', 0);
		cMap.put('D', 1);
		cMap.put('L', 2);
		cMap.put('R', 3);
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				int dir = cMap.get(g[i][j]);
				int nx = i + dx[dir];
				int ny = j + dy[dir];
				if(find(i*M+j) != find(nx*M+ny)) {
					union(i*M+j, nx*M+ny);
				}
			}
		} 
		
		Set<Integer> cSet = new HashSet<>();
		for(int i=0; i<N*M; i++) {
			// a <- b <- c 관계에서 모두 parent 값은 a인데
			// a의 parent 값이 변경되면 b, c는 변경되지 않으므로 경로압축을 통한 최종 부모를 찾아줘야 함
			// ** 최종 부모를 찾아주기 위해서 find()후 삽입
			cSet.add(find(parent[i]));
		}
		System.out.println(cSet.size());
		

	}
	public static int find(int x) {
		if(x != parent[x]) {
			parent[x] = find(parent[x]);
		}
		return parent[x];
	}
	public static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		
		if(pa < pb) {
			parent[pb] = pa;
		} else {
			parent[pa] = pb;
		}
	}

}
