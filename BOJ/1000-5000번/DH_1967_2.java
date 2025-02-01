import java.io.*;
import java.util.*;

/*
 * 트리의 지름
 */

public class DH_1967_2 {
	static class Node {
		int e, w;
		public Node(int e, int w) {
			this.e = e;
			this.w = w;
		}
	}
	static ArrayList<Node> adj[];
	static int maxIdx, maxDis;
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("./input/BOJ1967.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		adj = new ArrayList[n + 1];
		for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<Node>();

		StringTokenizer st;
		for(int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			adj[s].add(new Node(e, w));
			adj[e].add(new Node(s, w));
		}
		
		boolean[] v = new boolean[n + 1]; // 방문배열
		v[1] = true; // 임의의 시작점
		dfs(1, 0, v); // 임의의 시작점에서부터 dfs 시작
		
		v = new boolean[n + 1]; // 방문배열 다시 초기화
		v[maxIdx] = true; // max: 처음 시작점(1)에서부터 가장 먼 지점
		dfs(maxIdx, 0, v); // 처음 시작점(1)에서부터 가장 먼 지점을 기준으로 가장 먼 지점 구하기
		
		System.out.println(maxDis);
	}
	
	static void dfs(int start, int dis, boolean[] v) {
		if(dis > maxDis) { // dis: start노드까지 가기 위한 가중치
			maxIdx = start;
			maxDis = dis;
		}
		
		for(Node next: adj[start]) {
			if(v[next.e]) continue;
			
			v[next.e] = true;
			dfs(next.e, dis + next.w, v);
			v[next.e] = false;
		}
	}
}
