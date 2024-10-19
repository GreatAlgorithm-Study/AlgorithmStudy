import java.util.*;

class Solution {
    
    static int[] parents;
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        parents = new int[n];
        for(int i=0; i<n; i++) {
            parents[i] = i;
        }
        
        // 비용 순으로 정렬
        Arrays.sort(costs, (o1, o2)->(o1[2]-o2[2]));
        
        for(int[] cost: costs) {
            if(find(cost[0]) != find(cost[1])) {
                union(cost[0], cost[1]);
                answer += cost[2];
            }
        }
        
        return answer;
    }
    public static int find(int x) {
        if(parents[x] != x) {
            parents[x] = find(parents[x]);
        }
        return parents[x];
    }
    public static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        
        if(pa != pb) {
            parents[pb] = pa;
        }
    }
}