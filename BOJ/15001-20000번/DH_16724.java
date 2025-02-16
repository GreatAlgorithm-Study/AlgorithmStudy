import java.io.*;
import java.util.*;

/*
 * 피리 부는 사나이
 */

public class DH_16724 {
	static int N, M, cnt;
	static char[][] map;
	static int[] p;
	static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		initInput();
		initParent();
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				
				int dir = dir(map[r][c]);
				int nr = r + dr[dir], nc = c + dc[dir];
				
				int pos = r * M + c;
				int npos = nr * M + nc;
				
				if(p[pos] == p[npos]) continue;
				union(pos, npos);
			}
		}
		
		HashSet<Integer> parentSet = new HashSet<Integer>();
		
		for(int p: p) parentSet.add(find(p));

		System.out.println(parentSet.size());
	}
	
	static void initParent() {
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				int pos = r * M + c;
				p[pos] = pos;
			}
		}
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a != b) p[b] = a;
	}
	
	static int find(int a) {
		return p[a] = p[a] == a ? a : find(p[a]);
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
		p = new int[N * M];
		
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