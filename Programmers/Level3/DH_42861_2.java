import java.util.*;

/*
 * 섬 연결하기
 * 크루스칼을 통한 MST 구성
 */

public class DH_42861_2 {
    static int[] p;
    
    public int solution(int n, int[][] costs) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));
        
        // 간선들을 모두 우선순위 큐에 넣어주기
        for(int[] cost: costs) pq.add(cost);
    
        p = new int[n + 1];
        for(int i = 0; i < p.length; i++) p[i] = i;
        
        int result = 0, cnt = 0;
        
        // 노드가 n개 일 때, 간선은 n - 1개
        while(cnt != n - 1) {
            int[] current = pq.poll();
            
            if(find(current[0]) == find(current[1])) continue;
            union(current[0], current[1]);
            
            result += current[2];    
            cnt += 1;
        }
        
        return result;
    }
    
    // find → 경로 압축!!ㅑ
    static int find(int a) {
        return p[a] = a == p[a] ? a: find(p[a]);
    }
    
    // union
    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        
        if(a != b) p[b] = a;
    }
    
}