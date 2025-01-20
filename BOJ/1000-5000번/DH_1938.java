import java.io.*;
import java.util.*;

/*
 * 통나무 옮기기
 * 4 ≤ N ≤ 50
 */

public class DH_1938 {
	static final boolean VERTICAL = true; // VERTICAL: 수직선, HORIZONTAL: 수평선
	
	static class Tree {
		int r, c;
		boolean isVertical;
		
		public Tree(int r, int c, boolean isVertical) {
			this.r = r;
			this.c = c;
			this.isVertical = isVertical;
		}
	}
	
	static class Node {
		Tree t;
		int depth;
		public Node(Tree t, int depth) {
			this.t = t;
			this.depth = depth;
		}
	}
	
	static Tree start, end;
	static int N;
	static char[][] map;
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("./input/BOJ1938.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new char[N][N];
		
		for(int r = 0; r < N; r++) {
			String s = br.readLine();
			map[r] = s.toCharArray();
		}
		
		// 시작점과 끝 점 찾기
		findTree(map);
		
		System.out.println(bfs());
	}
	
	static int bfs() {
		Queue<Node> q = new ArrayDeque<Node>();
		
		boolean[][][] v = new boolean[2][N][N];
		
		// 세로로 이어져 있다면 0번 인덱스, 가로로 이어져 있다면 1번 인덱스
		q.add(new Node(start, 0));
		v[start.isVertical ? 0 : 1][start.r][start.c] = true;
		
		while(!q.isEmpty()) {
			Node current = q.poll();
			
			// 수직나무이면 0, 수평나무이면 1
			int currentIdx = current.t.isVertical ? 0 : 1;

			if(current.t.r == end.r && current.t.c == end.c && current.t.isVertical == end.isVertical) {
				return current.depth;
			}
			
			// 상, 하, 좌, 우로 이동
			for(int d = 0; d < 4; d++) {
				int nr = current.t.r + dr[d];
				int nc = current.t.c + dc[d];
				
				if(!check(nr, nc) || 
						!canPositTree(nr, nc, current.t.isVertical) || 
						v[currentIdx][nr][nc] || 
						map[nr][nc] == '1') continue;
				
				q.add(new Node(new Tree(nr, nc, current.t.isVertical), current.depth + 1));
				v[currentIdx][nr][nc] = true;
			}
			
			// 90도 회전
			if(v[1 ^ currentIdx][current.t.r][current.t.c] ||
					!canRotate(current.t.r, current.t.c)) continue;
			
			q.add(new Node(new Tree(current.t.r, current.t.c, !current.t.isVertical), current.depth + 1));
			v[1 ^ currentIdx][current.t.r][current.t.c] = true;
		}
		
		return 0;
	}
	
	static boolean canRotate(int r, int c) {
		
		// 90도 회전하는 과정에서 걸리는게 있는지 확인
		for(int sr = r - 1; sr < r + 2; sr++) {
			for(int sc = c - 1; sc < c + 2; sc++) {
				if(!check(sr, sc) || map[sr][sc] == '1') return false;
			}
		}
		
		return true;
	}

//	센터 양 옆으로 세울 수 있는지 확인
	static boolean canPositTree(int r, int c, boolean isVertical) {
		int cr1 = isVertical ? r - 1: r, cc1 = isVertical ? c : c - 1;
		int cr2 = isVertical ? r + 1: r, cc2 = isVertical ? c : c + 1;
		
		if(!check(cr1, cc1) || !check(cr2, cc2)) return false;
		if(map[cr1][cc1] == '1' || map[cr2][cc2] == '1') return false;
		return true;
	}
	
	static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
	
	
//	3개로 연속되어 있는 나무들 찾기
//	시작점과 끝 점 모두 찾기
	static void findTree(char[][] map) {

		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				
				if(map[r][c] == 'B' && start == null) {
					for(int i = 0; i < 2; i++) {
						start = getFindTree(r, c, i, map, map[r][c]);
						if(start != null) break;
					}
				}
				
				if(map[r][c] == 'E' && end == null) {
					for(int i = 0; i < 2; i++) {
						end = getFindTree(r, c, i, map, map[r][c]);
						if(end != null) break;
					}
				}
			}
		}
	}
	
	static Tree getFindTree(int r, int c, int i, char[][] map, char ch) {
		
		// i == 0 -> 세로
		// i == 1 -> 가로
		int cr1 = i == 0 ? r - 1: r, cc1 = i == 0 ? c : c - 1;
		int cr2 = i == 0 ? r + 1: r, cc2 = i == 0 ? c : c + 1;
		
		if(!check(cr1, cc1) || !check(cr2, cc2)) return null;
		
		if(map[cr1][cc1] != ch || map[cr2][cc2] != ch) return null;
		
		// 세로
		if(i == 0)return new Tree(r, c, VERTICAL);
		return new Tree(r, c, !VERTICAL);
	}
	
	static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
