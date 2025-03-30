import java.io.*;
import java.util.*;

public class JY_18513 {

    static int N, K;
    static int[] arr;
    static int[] dx = {-1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long ans = bfs();
        System.out.println(ans);
    }
    public static long bfs() {
        Queue<int[]> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        
        for(int a: arr) {
        	q.add(new int[] {a, 0});
        	visited.add(a);
        }
        
        long total = 0;
        int cnt = 0;
        while(!q.isEmpty()) {
        	int[] now = q.poll();
        	for(int i=0; i<2; i++) {
        		int nx = now[0] + dx[i];
        		
        		if(visited.contains(nx)) continue;
        		
        		total += (now[1]+1);
        		visited.add(nx);
        		q.add(new int[] {nx, now[1]+1});
        		cnt++;
        		
        		// K만큼 설치했으면 중단
        		if(cnt == K) return total;
        	}
        }

        return total;
    }
}
