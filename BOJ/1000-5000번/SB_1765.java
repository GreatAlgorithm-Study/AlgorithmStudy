import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class SB_1765 {
    static int N, M;
    static int[] team, enemy;

    private static int find(int x) {
        if (team[x]!=x) team[x] = find(team[x]);
        return team[x];
    }

    private static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa < pb) team[pb] = pa;
        else if (pb < pa) team[pa] = pb;
    }

    private static void makeRelation(char op, int p, int q) {
        int pp = find(p);
        int pq = find(q);

        if (pp == pq) return;

        if (op=='F'){       // 친구일 경우 팀 병합
            union(pp, pq);

            // 팀 병합에 따른 원수 관계 병합
            if (enemy[pp] != 0 && enemy[pq] != 0) union(enemy[pp], enemy[pq]);     // 두 팀 모두 원수가 있으면 각 원수들끼리 친구 시키기

            // 한 쪽만 원수 있으면 같이 원수 만들기
            else if (enemy[pp]!=0) enemy[pq] = find(enemy[pp]);
            else if(enemy[pq]!=0) enemy[pp] = find(enemy[pq]);
        }
        else {              // 원수일 경우, 각 팀의 원수를 상대랑 친구시키기
            if (enemy[pp]==0) enemy[pp] = pq;        // pp의 원수가 없으면 pp의 원수는 바로 pq의 원수가 됨
            else union(enemy[pp], pq);             // pp의 원수가 있으면 pp의 원수랑 pq랑 친구가 됨

            if (enemy[pq]==0) enemy[pq] = pp;        // 원수가 없으면 상대의 원수를 같이 원수로 받아드리고
            else union(enemy[pq], pp);             // 원수가 있으면 나의 원수와 상대가 친구가됨
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        // 팀 초기화
        team = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            team[i] = i;
        }
        enemy = new int[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            char op = st.nextToken().charAt(0);
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());

            makeRelation(op, p, q);
        }


        // 부모 재정비
        for (int i = 1; i <= N; i++) find(i);

        Set<Integer> ans = new HashSet<>();
        for (int i = 1; i < N + 1; i++) {
            ans.add(find(i));
        }
        System.out.println(ans.size());
    }
}
