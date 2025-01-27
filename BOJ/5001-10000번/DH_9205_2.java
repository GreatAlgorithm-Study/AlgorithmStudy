import java.io.*;
import java.util.*;

/*
 * 맥주 마시면서 걸어가기
 */

public class DH_9205_2 {
	static class Point {
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static class Node {
		int e, w;
		public Node(int e, int w) {
			this.e = e;
			this.w = w;
		}
	}
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("./input/BOJ9205.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine()); // 테스트케이스 개수
		StringBuilder sb = new StringBuilder();
		
		for(int tc = 0; tc < t; tc++) {
			int n = Integer.parseInt(br.readLine()); // 맥주를 파는 편의점의 개수
			
			// 집, 편의점, 락 페스티벌 좌표 입력받기
			Point[] points = new Point[n + 2];
			for(int i = 0; i < n + 2; i++) {
				st = new StringTokenizer(br.readLine());
				
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				points[i] = new Point(x, y);
			}
			
			// 인접 리스트 만들어주기
			ArrayList<ArrayList<Node>> adj = new ArrayList<ArrayList<Node>>();
			for(int i = 0; i < n + 2; i++) adj.add(new ArrayList<Node>());
				
			for(int i = 0; i < n + 2; i++) {
				for(int j = 0; j < n + 2; j++) {
					if(i == j) continue;
					
					int dis = Math.abs(points[i].x - points[j].x) + Math.abs(points[i].y - points[j].y);
					
					// 애초에 갈 수 없는 거리는 인접 리스트 생성할 때부터 제외
					if(dis > 20 * 50) continue;
					
					adj.get(i).add(new Node(j, dis));
					adj.get(j).add(new Node(i, dis));
				}
			}
			
			if(bfs(adj)) sb.append("happy").append("\n");
			else sb.append("sad").append("\n");
		}
		
		System.out.println(sb);
	}
	
	// bfs 탐색
	static boolean bfs(ArrayList<ArrayList<Node>> adj) {
		boolean[] v = new boolean[adj.size()];
		
		ArrayDeque<Integer> q = new ArrayDeque<Integer>();
		q.add(0); // 상근이네 집
		v[0] = true;
		
		while(!q.isEmpty()) {
			int current = q.poll();
			
			if(current == adj.size() - 1) return true;
			
			for(Node next:  adj.get(current)) {
				if(v[next.e]) continue;
				q.add(next.e);
				v[next.e] = true;
			}
		}
		
		return false;
	}
}
