public class JM_72413 {
	static final int INF = Integer.MAX_VALUE;
	static int[][] edge;

	public static int floydWarshall(int n, int s, int a, int b) {
		for(int k = 1; k <= n; k++) {
			for(int i = 1; i <= n; i++) {
				if(edge[i][k] == INF) continue;
				for(int j = 1; j <= n; j++) {
					if(edge[k][j] == INF) continue;
					if(edge[i][j] > edge[i][k] + edge[k][j]) {
						edge[i][j] = edge[i][k] + edge[k][j];
					}
				}
			}
		}

		int answer = Math.min(INF, edge[s][a] + edge[s][b]);
		for(int i = 1; i <= n; i++) {
			if(i == s) continue;
			int cost = Math.min(INF, edge[s][i] + edge[i][a] + edge[i][b]);
			answer = Math.min(answer, cost);
		}

		return answer;
	}

	public static int solution(int n, int s, int a, int b, int[][] fares) {
		edge = new int[n + 1][n + 1];

		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				if(i != j) edge[i][j] = INF;
			}
		}

		for(int[] fare : fares) {
			edge[fare[0]][fare[1]] = fare[2];
			edge[fare[1]][fare[0]] = fare[2];
		}

		return floydWarshall(n, s, a, b);
	}

	public static void main(String[] args) {
		System.out.println(solution(6, 4, 6, 2, new int[][] {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}}));
		System.out.println(solution(7, 3, 4, 1, new int[][] {{5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}}));
		System.out.println(solution(6, 4, 5, 6, new int[][] {{2,6,6}, {6,3,7}, {4,6,7}, {6,5,11}, {2,5,12}, {5,3,20}, {2,4,8}, {4,3,9}}));
	}
}
