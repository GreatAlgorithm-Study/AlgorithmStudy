import java.io.*;
import java.util.*;

//2 ≤ n ≤ 10^5
public class YJ_9466 {
    static int[] graph;
    static boolean[] visited;
    static boolean[] isFinished;
    static boolean[] isGrouping;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        //테스트케이스 실행
        while(T-- > 0){
            //초기화
            int n = Integer.parseInt(br.readLine());
            graph = new int[n+1];
            isFinished = new boolean[n+1];
            visited = new boolean[n+1];
            isGrouping = new boolean[n+1];
            st = new StringTokenizer(br.readLine());
            for(int i=1; i<n+1; i++){
                graph[i] = Integer.parseInt(st.nextToken());
            }
            //dfs 탐색
            for(int i=1; i<n+1; i++){
                if(!visited[i]){
                    dfs(i);
                }
            }
            //프로젝트 팀에 속하지 못한 학생 수
            int count = 0;
            for(int i=1; i<n+1; i++){
                if(!isGrouping[i]){
                    count++;
                }
            }
            System.out.println(count);
        }

    }

    public static void dfs(int current){
        visited[current] = true;
        int next= graph[current];

        if(!visited[next]){
            dfs(next);
        } else if(!isFinished[next]) {    //★재방문이지만 이전에 사이클을 이미 만들었거나, 사이클 형성을 못한 경우
            //싸이클 형성 가능 또는 자기자신
            while(!isGrouping[next]){
                isGrouping[next] = true;
                next = graph[next];
            }
        }
        isFinished[current] = true;
    }

}