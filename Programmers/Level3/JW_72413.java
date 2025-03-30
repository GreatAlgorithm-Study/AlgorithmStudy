import java.util.Arrays;

class JW_72413 {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int INF = 100_001 * n;
        int answer = INF;
        int[][] graph = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(graph[i], INF);
            graph[i][i] = 0;
        }
        for (int[] fare : fares) {
            graph[fare[0]][fare[1]] = fare[2];
            graph[fare[1]][fare[0]] = fare[2];
        }
        floydWarshall(graph, n);
        for(int i = 1; i <= n; i++) {
            int total = graph[s][i] + graph[i][a] + graph[i][b];
            if (total < answer) {
                answer = total;
            }
        }
        return answer;
    }
    
    public void floydWarshall(int[][] graph, int n) {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (graph[i][k] + graph[k][j] < graph[i][j]) {
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }
    }
}