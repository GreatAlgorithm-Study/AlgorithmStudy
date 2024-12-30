import java.io.*;
import java.util.*;


public class JY_4803 {
    static List<Integer>[] g;
    static boolean[] visited;


    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int turn = 1;

        while(true) {
        	st = new StringTokenizer(br.readLine());
        	int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            if (N == 0 && M == 0) break;
            
            
            g = new ArrayList[N+1];
            for (int i=1; i<N+1; i++) {
                g[i] = new ArrayList<>();
            }
            
            visited = new boolean[N+1];
            int cnt = 0;

            for (int i=1; i<M+1; i++) {
            	st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                g[a].add(b);
                g[b].add(a);
            }

            for (int node = 1; node <N+1; node++) {
                if (!visited[node]) {
                    if (!findCycle(node)) {
                        cnt++;
                    }
                }
            }

            if (cnt == 0) {
                System.out.printf("Case %d: No trees.%n", turn);
            } else if (cnt == 1) {
                System.out.printf("Case %d: There is one tree.%n", turn);
            } else {
                System.out.printf("Case %d: A forest of %d trees.%n", turn, cnt);
            }

            turn++;
        }
    }
    public static boolean findCycle(int start) {
        boolean isCycle = false;
        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        while (!q.isEmpty()) {
            int cntNode = q.poll();
            if (visited[cntNode]) {
                isCycle = true;
            }

            visited[cntNode] = true;

            for (int adjNode : g[cntNode]) {
                if(!visited[adjNode]) {
                    q.add(adjNode);
                }
            }
        }
        return isCycle;
    }
}


