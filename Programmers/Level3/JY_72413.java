import java.util.*;

public class JY_72413 {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 100001 * n;
        int INF = 100001 * n;       // 모든 간선을 지날 때 최댓값
        int[][] g = new int[n + 1][n + 1];

        for (int[] row : g) {
            Arrays.fill(row, INF);
        }

        // 그래프 초기화
        for (int[] fare : fares) {
            int n1 = fare[0];
            int n2 = fare[1];
            g[n1][n2] = fare[2];
            g[n2][n1] = fare[2];
        }

        for (int i = 1; i <= n; i++) {
            g[i][i] = 0;
        }

        // 1. 플로이드 와샬 : 모든 정점까지의 최소거리 구하기
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (g[i][j] > g[i][k] + g[k][j]) {
                        g[i][j] = g[i][k] + g[k][j];
                    }
                }
            }
        }

        // 2. 중간 지점을 거쳤을 경우도 포함
        for (int i = 1; i <= n; i++) {
            answer = Math.min(answer, g[i][s] + g[i][a] + g[i][b]);
        }

        return answer;
    }
}