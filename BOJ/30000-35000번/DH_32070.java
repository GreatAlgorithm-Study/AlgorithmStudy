import java.io.*;
import java.util.*;

/*
 * 색깔 모으기
 */

public class DH_32070 {
    static ArrayList<Integer>[] adj;
    static boolean[] v;
    static boolean[] up;
    static int cnt;  // 사이클 안에 있는 노드 중 바닥에 있는 노드의 개수

    static long DFS(int node, int before) {
        if (v[node]) return 0;
        if (!up[node]) cnt++;
        v[node] = true;
        for (int x : adj[node]) {
            if(x == before) continue; // 이전으로 되돌아가지 못하도록 함
            return DFS(x, node) + 1;
        }
        return 0;
    }

    public static void main(String[] args) throws Exception {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;

    	int N = Integer.parseInt(br.readLine());
    	
    	v = new boolean[N + 1];
    	adj = new ArrayList[N + 1];
    	up = new boolean[N + 1];
    	
        for (int i = 0; i < N + 1; i++) adj[i] = new ArrayList<>();
        
        
        for (int i = 1; i <= N; i++) {
        	st = new StringTokenizer(br.readLine());
        	
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	
        	up[a] = true;
        	adj[a].add(b);
        	adj[b].add(a);
        }

        int ans = 0;
        for (int i = 1; i <= N; i++) {
        	
        	if(adj[i].get(0) == i) continue; //  이미 통에 잘 있는 경우
            if (v[i]) continue; // 확인한 노드인 경우
            
            // 왜인지 모르겠는데,, == 쓰면 틀림
            // 예시) N = 2, 1 2 / 2 1인 경우 (서로 뒤집혀 있을 때)
            if (adj[i].get(0).equals(adj[i].get(1))) {
                ans += 3;
                v[i] = true;
                v[adj[i].get(0)] = true;
                continue;
            }
            
            // depth의 깊이 + 처음 시작 노드가 움직인거
            ans += DFS(i, 0) + 1;
            
            // 사이클 내에서 바닥에 있는 노드의 개수가 2개 이상이라면 안됨
            // 중간 과정에서는 바닥에서 위로 못올라가기 때문
            if (cnt > 1) {
                System.out.println(-1);
                return;
            }
            cnt = 0;
        }
        System.out.println(ans);
    }
}
