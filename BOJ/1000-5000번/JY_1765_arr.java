import java.util.*;
import java.io.*;

public class JY_1765_arr {
	
	static int N, M;
	static int[] err;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		parent = new int[N+1];
		err = new int[N+1];
		for(int i=1; i<N+1; i++) {
			parent[i] = i;
		}
		
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		for(int m=0; m<M; m++) {
			st = new StringTokenizer(br.readLine());
			String type = st.nextToken();
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			// 1) 친구 => 부모 같게
			if(type.equals("F")) {
				if(find(a) != find(b)) {
					union(a, b);
				}
			} 
			// 2) 원수인 경우
			else {
				// 원수 찾기
				// a의 원수 찾기
				if(err[a] != 0) {
					union(err[a], b);
				}
				// b의 원수 찾기
				if(err[b] != 0) {
					union(err[b], a);
				}
				
				err[a] = b;
				err[b] = a;
			}			
		}
		
		
		// 부모 재정비
		for(int i=1; i<N+1; i++) {
			find(i);
		}
		
//		System.out.println(Arrays.toString(parent));
		
		// 팀 찾기
		Set<Integer> teams = new HashSet<>();
		for(int i=1; i<N+1; i++) {
			teams.add(parent[i]);
		}
		
		System.out.println(teams.size());

				

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
		if(pa == pb) return;
		
		parent[b] = pa;
	}

}
