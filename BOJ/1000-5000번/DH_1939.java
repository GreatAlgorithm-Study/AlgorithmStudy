import java.io.*;
import java.util.*;

/*
 * 중량제한
 */

public class DH_1939 {
	static class Node {
		int e, w;
		
		public Node(int e, int w) {
			this.e = e;
			this.w = w;
		}
	}
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/BOJ1939.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 섬의 개수
		int M = Integer.parseInt(st.nextToken()); // 다리에 대한 정보
		
		ArrayList<ArrayList<Node>> adj = new ArrayList<>();
		for(int i = 0; i < N + 1; i++) adj.add(new ArrayList<>());
		
		// 인접리스트 만들기
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			adj.get(a).add(new Node(b, w));
			adj.get(b).add(new Node(a, w));
		}
		
		st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		
		int s = 0, e = 1_000_000_000;
		
		// 최댓값 구하기 → upper bound 구하기 
		while(s <= e) {
			int m = (s + e) / 2;
			
			// 최대 하중이 m 일 때, s에서 e까지 갈 수 있는지 확인
			if(isValid(a, b, m, adj)) s = m + 1;
			// 갈 수 없다면 최대하중 늘리기
			else e = m - 1;
		}
		
		System.out.println(e);
	}
	
	// 시작 지점에서 끝 지점까지 갈 수 있는지 확인
	static boolean isValid(int s, int e, int m, ArrayList<ArrayList<Node>> adj) {
		boolean flag = false;
		
		ArrayDeque<Integer> q = new ArrayDeque<Integer>();
		q.add(s);
		
		boolean[] v = new boolean[adj.size()];
		
		// 갈 수 있는 간선은 모두 가 봐야 되기 때문에, poll할 때 방문체크해야됨!
		while(!q.isEmpty()) {
			int current = q.poll();
			
			if(v[current]) continue;
			v[current] = true;
			
			if(current == e) {
				flag = true;
				break;
			}
			
			for(Node next: adj.get(current)) {
				if(next.w >= m) q.add(next.e);
			}
		}
		return flag;
	}
}
