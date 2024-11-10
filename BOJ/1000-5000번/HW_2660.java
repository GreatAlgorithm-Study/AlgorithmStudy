import java.io.*;
import java.util.*;

// 회장의 점수와 회장이 될 수 있는 모든 사람을 찾는 프로그램 작성

// 점수 : 특정 회원이 다른 모든 회원과 연결되기 위해 필요한 최단 거리 중에서 가장 먼 거리
// 회장 : 점수가 가장 낮은 사람(가장 가까운 거리에 있는 사람 = 최단거리) 구해야 함
// 조건 : 몇 사람을 통하면 모두가 서로알 수 있다 = 모든 회원(노드)가 연결되어 있음 -> 플로이드

// 시간복잡도 : O(V^3) (50^3 : 가능)
public class HW_2660 {
    static final int INF = Integer.MAX_VALUE >> 2;
    static int N;
    static int[][] graph;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        graph = new int[N+1][N+1];

        // 배열 초기화
        for(int i=1; i<=N; i++) {
            Arrays.fill(graph[i], INF);
            graph[i][i] = 0; // 자기 자신과의 거리 0
        }

        while(true) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(a==-1 && b==-1) break; // 종료 조건
            graph[a][b] = graph[b][a] = 1; // 양방향 그래프
        }

        // 플로이드 와샬
        for(int k=1; k<=N; k++) {
            for(int i=1; i<=N; i++) {
                for(int j=1; j<=N; j++) {
                    if(graph[i][j] > graph[i][k] + graph[k][j]) {
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }

        int min = INF;
        int[] scores = new int[N+1];

        // 각 회원의 점수를 계산
        for(int i=1; i<=N; i++) {
            int score = 0;
            for(int j=1; j<=N; j++) {
                if(graph[i][j] != INF) {
                    score = Math.max(score, graph[i][j]);
                }
            }
            scores[i] = score;
            min = Math.min(min, score); // 최소 점수 갱신
        }

        // 회장 찾기(최소 점수)
        ArrayList<Integer> candidates = new ArrayList<>();
        for(int i=1; i<=N; i++) {
            if(scores[i] == min) {
                candidates.add(i);
            }
        }

        System.out.println(min + " " + candidates.size());
        for(int i=0; i<candidates.size(); i++) {
            System.out.print(candidates.get(i) +" ");
        }
    }
}

