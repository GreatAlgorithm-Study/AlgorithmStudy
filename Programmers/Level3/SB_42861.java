import java.util.Arrays;

class SB_42861 {
    static int[] parents;

    private static int find(int x) {
        if (parents[x] != x) {
            return find(parents[x]);
        }
        return parents[x];
    }

    private static void union(int a, int b) {
        if (a > b) parents[b] = a;
        else parents[a] = b;
    }
    public static int solution(int n, int[][] costs) {
        Arrays.sort(costs, (o1, o2)->{
            return o1[2]-o2[2];
        });

        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }

        int cnt = 1;
        int ans = 0;
        for (int[] cur : costs) {
            if (cnt==n) break;
            int p_a = find(cur[0]);
            int p_b = find(cur[1]);

            if (p_a != p_b) {
                union(p_a, p_b);
                ans+=cur[2];
                cnt++;
            }
        }
        return ans;
    }
}