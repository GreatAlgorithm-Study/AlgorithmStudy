import java.io.*;
import java.util.*;

/*
 * 회장뽑기
 */

public class DH_2660 {
	static int N, maxDepth;
	static ArrayList<Integer> adj[];
	static ArrayDeque<int []> q;
	static boolean[] v;
	static int[] depth; // 각 회원마다 끝까지 탐색했을 때 depth를 저장할 변수
	static TreeSet<Integer> set; // 회장이 될 수 있는 사람의 번호를 담음
	
	public static void main(String[] args) throws Exception {
		initInput();
		solution();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(maxDepth).append(" ").append(set.size()).append("\n");
		for(int i: set) sb.append(i).append(" ");
		System.out.println(sb);
	}
	
	static void solution() {
		for(int i = 1; i < adj.length; i++) {
			v = new boolean[N + 1];
			q.add(new int[] {i, 0}); // [0]: 학생 번호, [1]: depth 깊이
			v[i] = true;
			
			while(!q.isEmpty()) {
				int[] current = q.poll();
				depth[i] = current[1];
				
				for(int next: adj[current[0]]) {
					if(v[next]) continue;
					q.add(new int[] {next, current[1] + 1});
					v[next] = true;
				}
			}
			
			if(depth[i] < maxDepth) { // maxDepth 깊이가 작아야 점수가 작아지기 때문에
				maxDepth = depth[i];
				set.clear();
			}
			
			if(depth[i] == maxDepth) set.add(i);
		}
	}
	
	static void initInput() throws Exception {
		System.setIn(new FileInputStream("../AlgorithmStudy/input/BOJ2660.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		q = new ArrayDeque<>();
		set = new TreeSet<Integer>();

		maxDepth = Integer.MAX_VALUE;
		N = Integer.parseInt(br.readLine());
		adj = new ArrayList[N + 1];
		for(int i = 0; i < adj.length; i++) adj[i] = new ArrayList<Integer>();
		
		depth = new int[N + 1];
		
		String s;
		while(!(s = br.readLine()).equals("-1 -1")) {
			st = new StringTokenizer(s);
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			adj[a].add(b);
			adj[b].add(a);
		}
	}
}
