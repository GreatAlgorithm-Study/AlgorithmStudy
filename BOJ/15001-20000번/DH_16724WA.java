import java.io.*;
import java.util.*;

/*
 * 피리 부는 사나이
 * 스택 오버플로우 발생하는데 통과되는 코드
 */

public class DH_16724WA {
	static int N, M, cnt;
	static char[][] map;
	static int[][] idx;
	static HashMap<Integer, Integer> p = new HashMap<Integer, Integer>();
	
	static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		initInput();
		
		idx = new int[N][M];
		
		int cnt = 1;
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				if(idx[r][c] != 0) continue;
				
				p.put(cnt, cnt);
				
				dfs(r, c, cnt);
				cnt += 1;
			}
		}
		
		HashSet<Integer> parentSet = new HashSet<Integer>();
		for(int key: p.keySet()) {
			parentSet.add(find(p.get(key)));
		}
		System.out.println(parentSet.size());
	}
	
	static void dfs(int r, int c, int cnt) {
		
		idx[r][c] = cnt;
		
		int dir = dir(map[r][c]);
		int nr = r + dr[dir];
		int nc = c + dc[dir];
		
		if(idx[nr][nc] != 0) {
			if(idx[nr][nc] == cnt) return;
			else union(cnt, idx[nr][nc]);
		} else dfs(nr, nc, cnt);
	}

	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a != b) p.put(b, a);
	}
	
	static int find(int a) {
		
		if(a == p.get(a)) return a;
		else {
			p.put(a, find(p.get(a)));
			return p.get(a);
		}
	}

	static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
	static void initInput() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		
		for(int r = 0; r < N; r++) {
			String s = br.readLine();
			map[r] = s.toCharArray();
		}
	}
	
	static int dir(char c) {
		if(c == 'U') return 0;
		if(c == 'D') return 1;
		if(c == 'L') return 2;
		else return 3;
	}
}