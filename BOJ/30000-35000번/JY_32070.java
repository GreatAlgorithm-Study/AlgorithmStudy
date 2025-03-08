import java.io.*;
import java.util.*;

public class JY_32070 {
    static int N;
    // cnt[i] 그룹의 공의 개수 == 그룹의 크기
    // rank[i] 트리의 깊이
    // toParis[] 한 그룹에서 상단에 위치한 공의 개수
    static int[] parent, cnt, topPairs;
    static boolean[] solved;
    static List<Integer>[] ball;
    
    static int find(int u) {
        if (parent[u] == u) return u;
        return parent[u] = find(parent[u]);
    }

    static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        
        if(pa == pb) return;
        if(pa < pb) {
        	parent[pb] = pa;
        	cnt[pa] += cnt[pb];
        } else {
        	parent[pa] = pb;
        	cnt[pb] += cnt[pa];
        }

    }

    // 해당 집합의 크기 반환
    static int getCnt(int u) {
        return cnt[find(u)];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        ball = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            ball[i] = new ArrayList<>();
        }
        
        parent = new int[N + 1];
//        rank = new int[N + 1];
        cnt = new int[N + 1];
        topPairs = new int[N + 1];
        solved = new boolean[N + 1];

        // 초기화 == 각 상자가 그룹장
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
            cnt[i] = 1;
        }

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            // 색깔이 a인 공의 절대적인 상자 위치
            ball[a].add(i * 2);
            // 색깔이 b인 공의 절대적인 상자 위치
            ball[b].add(i * 2 + 1);
        }
        
        System.out.println(Arrays.toString(ball));
        
        // 같은 색 공이 있는 상자들을 병합
        for (int i = 1; i <= N; i++) {
            union(ball[i].get(0) / 2, ball[i].get(1) / 2);
        }
        
        System.out.println(">> parent: "+Arrays.toString(parent));
        System.out.println(">> cnt: "+Arrays.toString(cnt));
        // cnt[i] : i번째 상자가 속한 사이클의 상자의 개수
        
        // 루트 노드의 topPairs 개수 증가
        // 짝수 : 상자의 상단에 있음
        // 홀수 : 상자의 하단에 있음
        for (int i = 1; i <= N; i++) {
        	// 색깔이 i인 공이 모두 각 상자의 상단에 있음
            if (ball[i].get(0) % 2 == 0 && ball[i].get(1) % 2 == 0) {
                int rootIdx = find(ball[i].get(0) / 2);
                System.out.println("i:"+i+" root:"+rootIdx);
                topPairs[rootIdx]++;
            }
        }
        
        System.out.println("top: "+Arrays.toString(topPairs));
        
        // 상자 그룹이 2개 이상이면 불가능 (-1 출력)
        for (int i = 1; i <= N; i++) {
            int rootIdx = find(ball[i].get(0) / 2);
            if (topPairs[rootIdx] >= 2) {
                System.out.println("-1");
                return;
            }
        }
        
        int ans = 0;
        
        // 최소 이동 횟수 계산
        for (int i = 1; i <= N; i++) {
            int rootIdx = find(ball[i].get(0) / 2);
            
            if (solved[rootIdx]) continue;
            
            solved[rootIdx] = true;
            int x = cnt[rootIdx];
            
            if (x >= 2) ans += x + 1;
        }
        
        System.out.println(ans);
    }
}
