import java.io.*;
import java.util.*;

public class JY_9372 {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
    	
        int t = Integer.parseInt(st.nextToken());
    	
    	for (int i = 0; i < t; i++) {
    		st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            List<int[]> edges = new ArrayList<>();
            int[] parent = new int[n + 1];
            int ans = 0;
            for (int j = 1; j <= n; j++) {
                parent[j] = j;
            }
            for (int j = 0; j < m; j++) {
            	st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                edges.add(new int[]{a, b});
            }

            for (int[] edge : edges) {
                int a = edge[0];
                int b = edge[1];
                if (findParent(parent, a) != findParent(parent, b)) {
                    unionParent(parent, a, b);
                    ans++;
                }
            }
            System.out.println(ans);
        }
    }
    public static int findParent(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = findParent(parent, parent[x]);
        }
        return parent[x];
    }

    public static void unionParent(int[] parent, int a, int b) {
        int aRoot = findParent(parent, a);
        int bRoot = findParent(parent, b);
        if (aRoot < bRoot) {
            parent[bRoot] = aRoot;
        } else {
            parent[aRoot] = bRoot;
        }
    }
}

