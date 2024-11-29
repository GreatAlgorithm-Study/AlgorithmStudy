import java.io.*;
import java.util.*;

/*
 * 사회망 서비스(SNS)
 */

public class DH_2533 {
	static int N;
	static ArrayList<Integer>[] adj;
	static Queue<Integer> q;
	
	public static void main(String[] args) throws Exception {
		initInput();
		solution();
	}
	
	static void solution() {
		// 얼리 어답터가 아닌 사람은 자신의 모든 친구들이 얼리 어답터일 때만 아이디어를 받아들임
		// 리프노트 구해주기
		getLeafNode();
		System.out.println(getEarlyAdopter());
	}

	static int getEarlyAdopter() {
		int answer = 0;
		
		boolean[] v = new boolean[N + 1];

		while(!q.isEmpty()) {
			int current = q.poll();
			
			// 리프노드는 그냥 확인해줬다는 표시만 해주기
			if(v[current]) continue;
			v[current] = true;
			
			// 리프노드와 이어져 있는 노드(부모) 찾기
			for(int i = 0; i < adj[current].size(); i++) {
				int next = adj[current].get(i); // next: 부모노드
				
				if(v[next]) continue;
				// 리프노드의 부모 노드는 무조건 얼리어답터가 됨
				v[next] = true;
				answer += 1;
				
				// 부모노드(얼리어답터)에 이어져 있는 노드의 간선 모두 없애기 (부모 노드 기준 들어오는거 없애기)
				for(int j = 0; j < adj[next].size(); j++) {
					int check = adj[next].get(j);
					if(v[check]) continue;
					
					int removeIdx = adj[check].indexOf(next);
					adj[check].remove(removeIdx);
					
					// 부모 노드 기준 들어오는 간선의 시작점이 리프노드가 되었나면 리프노드 큐에 넣어주기
					if(adj[check].size() == 1) q.add(check);
				}
			}
		}
		
		return answer;
	}

	// 리프노트 큐에 넣어주기
	static void getLeafNode() {
		q = new ArrayDeque<Integer>();
		for(int i = 1; i < adj.length; i++) {
			if(adj[i].size() == 1) q.add(i);
		}
	}
	
	static void initInput() throws Exception {
		System.setIn(new FileInputStream("./input/BOJ2533.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		adj = new ArrayList[N + 1];
		for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<Integer>();

		for(int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			adj[a].add(b);
			adj[b].add(a);
		}
	}
}
