import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *  22%에서 틀림 -> N = 1일 때 고려해주기
 */
public class JM_12784 {
	static class Node {
		int node;
		int dynamite;

		public Node(int node, int dynamite) {
			this.node = node;
			this.dynamite = dynamite;
		}
	}
	static int N;
	static int M;
	static ArrayList<Node>[] graph;
	static final int INF = 987654321;
	
	private static int dfs(int curr, boolean[] visited) {
		visited[curr] = true;
		
		int minD = 0;
		for(Node next: graph[curr]) {
			if (visited[next.node]) continue;
			minD += Math.min(next.dynamite, dfs(next.node, visited));
		}
		return (minD == 0) ? INF : minD;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		StringBuilder sb = new StringBuilder();
		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			graph = new ArrayList[N + 1];
			for (int i = 0; i <= N; i++) {
				graph[i] = new ArrayList<>();
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				graph[u].add(new Node(v, d));
				graph[v].add(new Node(u, d));
			}

			if(N == 1) sb.append("0").append("\n");
			else sb.append(dfs(1, new boolean[N + 1])).append("\n");
		}
		System.out.println(sb);
	}
}
