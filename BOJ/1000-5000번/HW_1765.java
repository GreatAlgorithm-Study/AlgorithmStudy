import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// Union-Find : 전체 집합이 있을 때 구성 원소 들이 겹치지 않도록 분할 할 때 자주 사용(연결성 확인 or 그룹 관리)

public class HW_1765{
    static int[] parent;
    static List<Integer>[] enemy;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine()); // 학생 수
        int m = Integer.parseInt(br.readLine()); // 관계 수

        parent = new int[n+1];
        enemy = new ArrayList[n+1];

        for(int i=1; i<=n; i++){
            parent[i] = i;
            enemy[i] = new ArrayList<>();
        }
        while(m-->0){
            st = new StringTokenizer(br.readLine());
            String team = st.nextToken();// E, F 구분
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            if(team.equals("E")){ // 원수 관계
                // 내 원수의 원수는 같은 집합으로 설정 (원수의 원수 = 친구)
                if(!enemy[p].isEmpty()){ // p의 기존 원수와 q를 친구로 묶기
                    union(enemy[p].get(0), q);
                }
                if(!enemy[q].isEmpty()){ // q의 기존 원수와 p를 친구로 묶기
                    union(enemy[q].get(0), p);
                }
                enemy[p].add(q); // 원수 목록에 추가
                enemy[q].add(p);
            } else if(team.equals("F")){ // 친구 관계
                // Union-Find로 같은 집합에 속하는 원소 관리
                union(p, q);
            }
        }
        Set<Integer> Teams = new HashSet<>(); // 팀 개수 계산하기 위한 부모 갱신 및 고유 루트(중복 방지)
        for(int i=1; i<=n; i++){
            Teams.add(find(i)); // 각 학생의 최상위 부모를 Set에 추가
        }
        System.out.println(Teams.size()); // 팀 개수
    }
    static int find(int x) { // 루트 노드 확인
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    static void union(int x, int y){
        int rootX = find(x); // x 루트 찾기
        int rootY = find(y); // y 루트 찾기
        if(rootX != rootY){
            parent[rootY] = rootX; // 두 집합의 루트가 다르면 병합
        }
    }
}