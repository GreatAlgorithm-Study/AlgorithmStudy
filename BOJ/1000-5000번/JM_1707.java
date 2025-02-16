import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class JM_1707 {
    	static int V;
	static int E;
	static List<List<Integer>> graph;

	private static boolean dfs(int g, int curr, int[] groups) {
		groups[curr] = g;
		for(int next : graph.get(curr)) {
			if(groups[curr] == groups[next]) return false;
			if(groups[next] == 0 && !dfs(3 - g, next, groups)) return false;
		}
		return true;
	}

	private static boolean isBinaryGraph() {
		int[] groups = new int[V + 1];
		for (int i = 1; i <= V; i++) {
			if(groups[i] != 0) continue;
			if(!dfs(1, i, groups)) return false;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());

		StringBuilder sb = new StringBuilder();
		while (K-- > 0) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			graph = new ArrayList<>();
			for (int i = 0; i <= V; i++) {
				graph.add(new ArrayList<>());
			}
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				graph.get(u).add(v);
				graph.get(v).add(u);
			}
			sb.append(isBinaryGraph() ? "YES" : "NO").append("\n");
		}
		System.out.println(sb);
	}
}
